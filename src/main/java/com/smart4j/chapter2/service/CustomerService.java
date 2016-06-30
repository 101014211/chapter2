package com.smart4j.chapter2.service;

import com.smart4j.chapter2.helper.DatabaseHelper;
import com.smart4j.chapter2.model.Customer;
import com.smart4j.chapter2.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 温涛 on 2016/5/12.
 */
public class CustomerService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    public List<Customer> getCustomerList()
    {
//        Connection connection = null;
//        try{
//            List<Customer> customerList = new ArrayList<Customer>();
//            String sql = "SELECT * FROM CUSTOMER";
//            connection = DatabaseHelper.getConnection();
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next())
//            {
//                Customer customer = new Customer();
//                customer.setId(rs.getLong("id"));
//                customer.setName(rs.getString("name"));
//                customer.setContact(rs.getString("contact"));
//                customer.setTelephone(rs.getString("telephone"));
//                customer.setEmail(rs.getString("email"));
//                customer.setRemark(rs.getString("remark"));
//                customerList.add(customer);
//            }
//            return customerList;
//        }catch(SQLException e)
//        {
//            LOGGER.error("execute sql failure",e);
//            return null;
//        }finally
//        {
//            DatabaseHelper.closeConnection(connection);
//        }
        String sql = "SELECT * FROM CUSTOMER";
        return DatabaseHelper.queryEntityList(Customer.class,sql);
    }
    public Customer getCustomer(long id)
    {
        return null;
    }
    public boolean createCustomer(Map<String,Object> fieldMap)
    {
        return false;
    }
    public boolean updateCustomer(long id,Map<String,Object> fieldMap)
    {
        return false;
    }
    public boolean deleteCustomer(long id)
    {
        return false;
    }
}
