package org.smart4j.chapter2.test;

import com.smart4j.chapter2.model.Customer;
import com.smart4j.chapter2.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 温涛 on 2016/5/12.
 */
public class CustomerServiceTest
{
    private final CustomerService customerService;
    public CustomerServiceTest()
    {
        customerService = new CustomerService();
    }
    @Before
    public void init()
    {

    }
    @Test
    public void getCustomerListTest() throws Exception
    {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(5,customerList.size());
    }
    @Test
    public void getCustomerTest() throws Exception
    {
        long id = 1;
        Customer customer  = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }
    @Test
    public void createCustomerTest() throws Exception
    {
        Map<String,Object> fieldMap = new HashMap<String,Object>();
        fieldMap.put("name","customer100");
        fieldMap.put("contact","John");
        fieldMap.put("telephone","18614046401");
        boolean result  = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }
    @Test
    public void updateCustomerTest() throws Exception
    {
        long id = 1;
        Map<String,Object> fieldMap = new HashMap<String,Object>();
        fieldMap.put("contact","Eric");
        boolean result = customerService.updateCustomer(id,fieldMap);
        Assert.assertTrue(result);
    }
    @Test
    public void deleteCustomerTest() throws Exception
    {
        long id  = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
