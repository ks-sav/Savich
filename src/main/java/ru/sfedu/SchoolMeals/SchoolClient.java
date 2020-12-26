package ru.sfedu.SchoolMeals;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.api.DataProviderCSV;
import ru.sfedu.SchoolMeals.api.IDataProvider;
import ru.sfedu.SchoolMeals.model.ComboMeals;
import ru.sfedu.SchoolMeals.model.Customer;
import ru.sfedu.SchoolMeals.model.FoodItem;

import java.io.Console;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import static ru.sfedu.SchoolMeals.Constants.ENV_CONST;
import static ru.sfedu.SchoolMeals.Constants.INT_CONST;
import static ru.sfedu.SchoolMeals.utils.ConfigurationUtil.getConfigurationEntry;

//Этот класс будет являться API для вызовов основных методов вашего приложения
public class SchoolClient {
    private static  Logger log = LogManager.getLogger(SchoolClient.class);


    public SchoolClient(){
        log.debug("<ConstructorName>[0]: starting application.........");
    }

    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        new SchoolClient();
        log.debug("<ConstructorName>[0]: starting application.........");
        IDataProvider dataProvider = new DataProviderCSV();
        dataProvider.initDataSource();
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);

        //dataProvider.createOrder(1, date, dataProvider.getAll(FoodItem.class));

        logBasicSystemInfo();
        }
    public static void logBasicSystemInfo() throws IOException {
        log.error("Launching the application...");
        log.debug("Operating System: " + System.getProperty("os.name") + " " +
                System.getProperty("os.version"));
        log.info("JRE: " + System.getProperty("java.version"));
        log.info("Java Launched From: " + System.getProperty("java.home"));
        log.info("Class Path: " + System.getProperty("java.class.path"));
        log.info("Library Path: " + System.getProperty("java.library.path"));
        log.info("User Home Directory: " + System.getProperty("user.home"));
        log.info("User Working Directory: " + System.getProperty("user.dir"));
        log.fatal("Test INFO logging.");
        log.fatal(getConfigurationEntry(ENV_CONST));
        log.info(String.format("The value of %h", ENV_CONST));
        log.info(String.format("%d", INT_CONST));
    }



}
