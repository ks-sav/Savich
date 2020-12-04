package ru.sfedu.SchoolMeals.model.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CsvDataProviderTest extends AbstractApiTest {
    private final Logger log = LogManager.getLogger();
    @Override
    public IDataProvider getiDataProvider() {
        return new DataProviderCSV();
    }

    @Override
    public void setUpProperties() {
        System.setProperty("ShoolMeals.Staff_csv", "src/main/resources/data/csv/Staff.csv");
        System.setProperty("ShoolMeals.Puiple_csv", "src/main/resources/data/csv/Puiple.csv");
        System.setProperty("ShoolMeals.Order_csv", "src/main/resources/data/csv/Order.csv");
        System.setProperty("ShoolMeals.FoolItem_csv", "src/main/resources/data/csv/FoodItem.csv");
        System.setProperty("ShoolMeals.ComboMeals_csv", "src/main/resources/data/csv/ComboMeals.csv");
        System.setProperty("ShoolMeals.FoodCategory_csv", "src/main/resources/data/csv/FoodCategory.csv");
    }

    @Override
    public void cleanBeforeRun() {
        List<String> filesToClean = new ArrayList<>();
        filesToClean.add("src/main/resources/data/csv/Staff.csv");
        filesToClean.add("src/main/resources/data/csv/Puiple.csv");
        filesToClean.add("src/main/resources/data/csv/Order.csv");
        filesToClean.add("src/main/resources/data/csv/FoodItem.csv");
        filesToClean.add("src/main/resources/data/csv/FoodCategory.csv");
        filesToClean.add("src/main/resources/data/csv/ComboMeals.csv");
        filesToClean.forEach(s -> {
            try {
                new File(s).delete();
                log.debug("File" + s+" is delete");
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}