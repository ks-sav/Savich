package ru.sfedu.SchoolMeals.model.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.bean.Customer;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

import java.io.IOException;
import java.sql.*;
import java.util.NoSuchElementException;

import static ru.sfedu.SchoolMeals.model.constants.Constants.*;
import static ru.sfedu.SchoolMeals.utils.ConfigurationUtil.getConfigurationEntry;

public class DataProviderDB {
    private static final String DB_DRIVER="db_driver";
    private static final String DB_USER="db_user";
    private static final String DB_PASS="db_pass";
    private static final String DB_URL="db_url";
    private static final String DB_INSERT="INSERT INTO \"%s\" (%s) VALUES (%s)";
    private static final String DB_SELECT="SELECT * FROM \"%s\" WHERE id=%d";
    private static Logger log = LogManager.getLogger(DataProviderDB.class);

    public void insertCustomer(Customer customer) throws SQLException, IOException, ClassNotFoundException {
        this.execute(String.format(DB_INSERT,customer.getClass().getSimpleName().toLowerCase(),CUSTOMER_FIELDS,"'"+customer.getName()+"'"));
    }

        public Customer getCustomerById(long id) throws IOException, SQLException, ClassNotFoundException {
           ResultSet set = getResultSet(String.format(DB_SELECT,Customer.class.getSimpleName().toLowerCase(),id));
            try {
                Customer customer = new Customer();
                customer.setId(set.getLong(CUSTOMER_ID));
                customer.setName(set.getString(CUSTOMER_NAME));
                return customer;
            }catch (NoSuchElementException e){
                log.error(e);
                return null;
        }
    }

    private Connection connection() throws IOException, SQLException, ClassNotFoundException {
        Class.forName(getConfigurationEntry(DB_DRIVER));
        return DriverManager.getConnection(
                getConfigurationEntry(DB_URL),
                getConfigurationEntry(DB_USER),
                getConfigurationEntry(DB_PASS)
        );
    }


    private void execute(String sql) throws SQLException, IOException, ClassNotFoundException {
        log.info(sql);
        PreparedStatement statement = connection().prepareStatement(sql);
        statement.execute();
        statement.close();
    }

    private ResultSet getResultSet(String sql) throws SQLException, IOException, ClassNotFoundException {
        log.info(sql);
        PreparedStatement statement = connection().prepareStatement(sql);
        ResultSet set = statement.executeQuery();
        statement.close();
        return set;
    }

}
