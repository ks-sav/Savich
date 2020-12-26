package ru.sfedu.SchoolMeals;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.api.DataProviderCSV;
import ru.sfedu.SchoolMeals.api.DataProviderDB;
import ru.sfedu.SchoolMeals.api.DataProviderXML;
import ru.sfedu.SchoolMeals.api.IDataProvider;
import ru.sfedu.SchoolMeals.model.*;

import java.io.Console;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public static void main(String[] args) throws IOException, ParseException {
        new SchoolClient();
        log.debug("<ConstructorName>[0]: starting application.........");
        IDataProvider dataProvider = null;

        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        List<Long> itemsId = new ArrayList<>();
        Random rand = new Random();
        logBasicSystemInfo();

        for(int i = 0; i < args.length; i++){

            if(args[i].equals("DPCSV")){
                dataProvider = new DataProviderCSV();
            }
            else if(args[i].equals("DPCDB")){
                dataProvider = new DataProviderDB();
            }
            else if(args[i].equals("DPXML")){
                dataProvider = new DataProviderXML();
            }
            if(dataProvider != null)
                dataProvider.initDataSource();
            if(args[i].equals("createOrder")){
                for(int j = i; j < args.length; j++)
                {
                    itemsId.add((long)Integer.parseInt(args[j]));
                }
                long[] itemsIds = itemsId.stream().mapToLong(k->k).toArray();
                Order order = dataProvider.createOrder(rand.nextInt(), timestamp.toString(), itemsIds,false);
                System.out.println(order.toString());
            }
            else if(args[i].equals("approvOrder")){
                for(int j = i; j < args.length; j++)
                {
                    itemsId.add((long)Integer.parseInt(args[j]));
                }
                long[] itemsIds = itemsId.stream().mapToLong(k->k).toArray();
                Order order = dataProvider.createOrder(rand.nextInt(), timestamp.toString(), itemsIds,false);
                dataProvider.approvOrder(order);
                System.out.println(order.toString());
            }
            else if(args[i].equals("createReport")){
                    OrderStatus status = OrderStatus.PRE;
                    if (args[i + 3].equals("APPROV"))
                    {
                        status = OrderStatus.APPROV;
                    }
                    StringBuffer buf = dataProvider.createReport(args[i+1], args[i+2], status);
                    System.out.println(buf);
                 }
            }
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
