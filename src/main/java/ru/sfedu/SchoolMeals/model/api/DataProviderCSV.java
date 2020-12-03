package ru.sfedu.SchoolMeals.model.api;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.api.ConverterCSV.*;
import ru.sfedu.SchoolMeals.model.bean.*;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DataProviderCSV extends IDataProvider {
    //private final String PATH = "csv_path";
    //private final String FILE_EXTENSION = "csv";
    private static Logger log = LogManager.getLogger(DataProviderCSV.class);

    private <T> List<T> readFile(String file, Converter<T> converter) {
        Reader reader = null;
        try {
            reader = new FileReader(file);
            CSVReader csvReader = new CSVReader(reader);
            List<T> ans = csvReader.readAll()
                    .stream()
                    .map(converter::fromCsv)
                    .collect(Collectors.toList());
            reader.close();
            return ans;
        } catch (Exception e) {
            log.error("Error reading file " + file);
            return new ArrayList<>();
        }
    }

    private <T> void writeFile(String file, List<T> data, Converter<T> converter) {
        try {
            Writer writer = new FileWriter(file, false);
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeAll(data.stream().map(converter::toCsv).collect(Collectors.toList()));
            writer.close();
        } catch (IOException e) {
            log.error("Error writing file " + file);
        }
    }

    private String getFileName(Class<?> aClass){
        //String propertyName = "src\\main\\resources\\data\\csv\\"+aClass.getSimpleName()+".csv";
        //String fileName = System.getProperty(propertyName);
        String fileName = "src\\main\\resources\\data\\csv\\"+aClass.getSimpleName()+".csv";
        if (fileName == null) {
            log.fatal("Unable to initialize, no property: " + fileName);
            System.exit(1);
        }
        return fileName;
    }

    public Converter getConverter(Class tClass){
        if (tClass == Order.class) {
            return new OrderConverterCSV();
        } else if (tClass == Puiple.class) {
            return new PuipleConverterCSV();
        }else if (tClass == Staff.class) {
                return new StaffConverterCSV();
        } else if (tClass == FoodCategory.class) {
            return new FoodCategoryConverterCSV();
        } else if (tClass == FoodItem.class) {
            return new FoodItemConverterCSV();
        } else if (tClass == ComboMeals.class) {
            return new ComboMealsConverterCSV();
        } else {
            log.fatal("Unknown class"+tClass.getSimpleName());
            System.exit(1);
            return null;
        }
    }

    @Override
    public void initDataSource() {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(Order.class);
        classes.add(Staff.class);
        classes.add(Puiple.class);
        classes.add(FoodCategory.class);
        classes.add(FoodItem.class);
        classes.add(ComboMeals.class);

        classes.forEach(aClass -> {
            String fileName = getFileName(aClass);
            File f = new File(fileName);
            try {
                if (f.createNewFile()) log.info("File " + f + " created");
            } catch (IOException e) {
                log.fatal("Cannot create file " + fileName);
                System.exit(1);
            }
        });
    }

    @Override
    protected <T extends WithId> List<T> getAll(Class<T> tClass) {
        String fileName = getFileName(tClass);
        Converter<T> converter = getConverter(tClass);
        return readFile(fileName, converter);
    }

    @Override
    protected <T extends WithId> void writeAll(Class<T> tClass, List<T> data) {
        String fileName = getFileName(tClass);
        Converter<T> converter = getConverter(tClass);
        writeFile(fileName, data, converter);
    }
}
