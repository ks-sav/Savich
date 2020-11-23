package ru.sfedu.SchoolMeals.model.api;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import ru.sfedu.SchoolMeals.TestBase;
import ru.sfedu.SchoolMeals.model.bean.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderXMLTest extends TestBase {

    @Test
    void insertCustomerSuccess() throws Exception {
        System.out.println("insertCustomerSuccess");
        List<Customer> listCustomer = new ArrayList<>();
        DataProviderXML instance=new DataProviderXML();
        Customer user1=createCustomer(1,"Name1");
        Customer user2=createCustomer(2,"Name 2");
        Customer user3=createCustomer(3,"Name3");
        listCustomer.add(user1);
        listCustomer.add(user2);
        listCustomer.add(user3);
        instance.insertCustomer(listCustomer);
        assertEquals(user1,instance.getCustomerById(1));
    }
    @Test
    void insertCustomerFail() throws Exception {
        System.out.println("insertCustomerFail");
        List<Customer> listCustomer = new ArrayList<>();
        DataProviderXML instance=new DataProviderXML();
        Customer user1=createCustomer(1,"Name1");
        Customer user2=createCustomer(2,"Name 2");
        Customer user3=createCustomer(3,"Name3");
        listCustomer.add(user1);
        listCustomer.add(user2);
        listCustomer.add(user3);
        instance.insertCustomer(listCustomer);
        assertNull(instance.getCustomerById(4));
    }

    @Test
    public void testGetByIdCustomer() throws Exception {
        System.out.println("testGetByIdCustomer");
        DataProviderXML instance=new DataProviderXML();;
        System.out.println(instance.getCustomerById(2));
    }

    @Test
    void select() {
    }
}