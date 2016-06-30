package com.smart4j.chapter2.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by 温涛 on 2016/5/17.
 */
public class CollectionUtil
{
    public static boolean isEmpty(Collection<?> collection)
    {
        return CollectionUtils.isEmpty(collection);
    }
    public static boolean isNotEmpty(Collection<?> collection)
    {
        return !isEmpty(collection);
    }
    public static boolean isNotEmpty(Map<?,?> map)
    {
        return !isNotEmpty(map);
    }
}
