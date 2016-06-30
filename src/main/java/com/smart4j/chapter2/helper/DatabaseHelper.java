package com.smart4j.chapter2.helper;

import com.smart4j.chapter2.util.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by 温涛 on 2016/5/18.
 * 数据库连接工具：包括加载数据库驱动，连接数据库，关闭数据库
 */
public final class DatabaseHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static Connection conn;

    //静态代码块，通过property文件加载数据库连接需要的参数
    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            LOGGER.error("不能加载jdbc的驱动",e);
        }
    }

    //静态方法，连接数据库
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
    public static Connection getConnection()
    {
        conn = CONNECTION_HOLDER.get();
        if(conn == null)
        {
            try {
                conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("数据库连接失败",e);
                throw new RuntimeException(e);
            }finally
            {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    //关闭数据库
    public static void closeConnection(Connection conn)
    {
        conn = CONNECTION_HOLDER.get();
        if(conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("数据库关闭失败",e);
                throw new RuntimeException(e);
            }finally
            {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    //查询实体集
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object...params)
    {
        List<T> entityList;
        try {
            conn = getConnection();
            entityList = QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("查询实体集失败",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection(conn);
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> entityClass,String sql,Object...params)
    {
        T entity;
        try {
            conn = getConnection();
            entity = QUERY_RUNNER.query(conn,sql,new BeanHandler<T>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("查询失败",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection(conn);
        }
        return entity;
    }

}
