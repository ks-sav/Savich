package ru.sfedu.SchoolMeals.api;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataProviderDBTest extends TestBase {
    private static final String DB_DRIVER="db_driver";
    private static final String DB_USER="db_user";
    private static final String DB_PASS="db_pass";
    private static final String DB_URL="db_url";
    private static final String DB_CREATE="db_create";
    private static Logger log = LogManager.getLogger(DataProviderDB.class);
    private Connection connection;

    @Override
    public IDataProvider getiDataProvider() {
        return new DataProviderDB();
    }

    @Override
    public void cleanBeforeRun() {}
    /*
    @Override
    public IDataProvider getiDataProvider() {
        return new DataProviderDB();
    }

    @Override
    public void cleanBeforeRun() throws IOException {
        try {
            Class.forName(ConfigurationUtil.getConfigurationEntry(DB_DRIVER));
        } catch (ClassNotFoundException | IOException e) {
            log.fatal("Driver not found " + (ConfigurationUtil.getConfigurationEntry(DB_DRIVER)));
            System.exit(1);
        }

        try {
            connection = DriverManager.getConnection(ConfigurationUtil.getConfigurationEntry(DB_URL),ConfigurationUtil.getConfigurationEntry(DB_USER),ConfigurationUtil.getConfigurationEntry(DB_PASS));
            connection.createStatement().execute("DROP TABLE if exists \"Staff\",\"Puiple\",\"Order\",\"FoodItem\",\"FoodCategory\",\"ComboMeals\";");
        } catch (SQLException | IOException throwables) {
            log.fatal("Unable to connect to database");
            log.fatal(throwables);
            System.exit(1);
        }
            }*/
}