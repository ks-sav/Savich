/*
package ru.sfedu.SchoolMeals.model.api;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import ru.sfedu.SchoolMeals.TestBase;
import ru.sfedu.SchoolMeals.model.bean.Customer;
import ru.sfedu.SchoolMeals.model.api.DataProviderCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderCSVTest extends TestBase {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    void insertCustomerSuccess() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        System.out.println("insertCustomerSuccess");
        List<Customer> listCustomer = new ArrayList<>();
        DataProviderCSV instance=new DataProviderCSV();
        Customer customer1=createCustomer(1,"Name1");
        Customer customer2=createCustomer(2,"Name 2");
        Customer customer3=createCustomer(3,"Name3");
        listCustomer.add(customer1);
        listCustomer.add(customer2);
        listCustomer.add(customer3);
        instance.insertCustomer(listCustomer);
        assertEquals(customer1,instance.getCustomerById(1));
    }
    @Test
    void insertCustomerFail() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        System.out.println("insertCustomerFail");
        List<Customer> listCustomer = new ArrayList<>();
        DataProviderCSV instance=new DataProviderCSV();
        Customer customer1=createCustomer(1,"Name1");
        Customer customer2=createCustomer(2,"Name 2");
        Customer customer3=createCustomer(3,"Name3");
        listCustomer.add(customer1);
        listCustomer.add(customer2);
        listCustomer.add(customer3);
        instance.insertCustomer(listCustomer);
        assertNull(instance.getCustomerById(4));
    }

    @Test
    public void testGetByIdCustomer() throws IOException {
        System.out.println("testGetByIdCustomer");
        DataProviderCSV instance = new DataProviderCSV();
        System.out.println(instance.getCustomerById(2));
    }
}
 */