package ru.sfedu.SchoolMeals.model.api;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import ru.sfedu.SchoolMeals.TestBase;
import ru.sfedu.SchoolMeals.model.bean.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderDBTest extends TestBase {

    @Test
    void insertCustomerSuccess() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("insertCustomerSuccess");
        DataProviderDB instance=new DataProviderDB();
        Customer customer1=createCustomer(1,"Name1");
        instance.insertCustomer(customer1);
        assertEquals(customer1,instance.getCustomerById(1));
    }

    @Test
    void insertCustomerFail() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("insertCustomerFail");
        DataProviderDB instance=new DataProviderDB();
        Customer customer1=createCustomer(1,"Name1");
        instance.insertCustomer(customer1);
        assertNull(instance.getCustomerById(45));
    }

}