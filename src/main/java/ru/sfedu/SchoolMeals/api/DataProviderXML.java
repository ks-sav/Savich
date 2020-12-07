/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.SchoolMeals.api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.SchoolMeals.api.WrapperXML.*;
import ru.sfedu.SchoolMeals.model.*;
import ru.sfedu.SchoolMeals.model.api.WrapperXML.*;
import ru.sfedu.SchoolMeals.model.bean.*;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;



public class DataProviderXML extends IDataProvider{
    
    private static Logger log = LogManager.getLogger(DataProviderXML.class);
    //Определяем сериалайзер
    private final static Persister persister = new Persister();
    private static final String PATH="xml_path";
    private static final String EXT="xml";

    private String getFileName(Class<?> aClass) throws IOException {
        String fileName = ConfigurationUtil.getConfigurationEntry(PATH) + aClass.getSimpleName() + ConfigurationUtil.getConfigurationEntry(EXT);
        if (fileName == null) {
            log.fatal("Unable to initialize, no property: " + fileName);
            System.exit(1);
        }
        return fileName;
    }

    private Class getXmlList(Class<?> aClass){
        if (aClass == Order.class)
            return OrderList.class;
        else if (aClass == FoodCategory.class)
            return FoodCategoryList.class;
        else if (aClass == Staff.class)
            return StaffList.class;
        else if (aClass == Puiple.class)
            return PuipleList.class;
        else if (aClass == FoodItem.class)
            return FoodItemList.class;
        else if (aClass == ComboMeals.class)
            return ComboMealsList.class;
        else {
            log.fatal("Unknown class");
            System.exit(1);
            return null;
        }
    }

    @Override
    public void initDataSource() {
        List<Class> classes = new ArrayList<>();
        classes.add(FoodCategory.class);
        classes.add(FoodItem.class);
        classes.add(Order.class);
        classes.add(ComboMeals.class);
        classes.add(Puiple.class);
        classes.add(Staff.class);
        classes.forEach(aClass -> {
            String fileName = null;
            try {
                fileName = getFileName(aClass);
            } catch (IOException e) {
                log.fatal("Error ni fileName");
                e.printStackTrace();
            }
            File f = new File(fileName);
            try {
                if (f.createNewFile()) {
                    log.info("File " + fileName + " created");
                    writeAll(aClass, new ArrayList());
                }
            } catch (IOException e) {
                log.fatal("Cannot create file " + fileName);
                System.exit(1);
            }
        });
    }

    @Override
    protected <T extends WithId> List<T> getAll(Class<T> tClass) throws IOException {
        String fileName = getFileName(tClass);
        Reader reader = null;
        try {
            reader = new FileReader(fileName);
            List ans;
            if (tClass == FoodCategory.class)
                ans = persister.read(FoodCategoryList.class, reader).foodCategory;
            else if (tClass == FoodItem.class)
                ans = persister.read(FoodItemList.class, reader).foodItem;
            else if (tClass == Order.class)
                ans = persister.read(OrderList.class, reader).order;
            else if (tClass == ComboMeals.class)
                ans = persister.read(ComboMealsList.class, reader).comboMeals;
            else if (tClass == Staff.class)
                ans = persister.read(StaffList.class, reader).staff;
            else if (tClass == Puiple.class)
                ans = persister.read(PuipleList.class, reader).puiple;
            else {
                log.fatal("Unknown class");
                System.exit(1);
                return null;
            }
            reader.close();
            return ans;
        } catch (Exception e) {
            log.error("Error reading file " + fileName);
            log.error(e);
        }
        return new ArrayList<>();
    }

    @Override
    protected <T extends WithId> void writeAll(Class<T> tClass, List<T> data) throws IOException {
        String fileName = getFileName(tClass);
        try {
            //Подключаемся к потоку записи файла
            Writer writer = new FileWriter(fileName);
            if (tClass == FoodCategory.class)
                //Записываем в файл
                persister.write(new FoodCategoryList((List) data), writer);
            else if (tClass == FoodItem.class)
                persister.write(new FoodItemList((List) data), writer);
            else if (tClass == Order.class)
                persister.write(new OrderList((List) data), writer);
            else if (tClass == ComboMeals.class)
                persister.write(new ComboMealsList((List) data), writer);
            else if (tClass == Staff.class)
                persister.write(new StaffList((List) data), writer);
            else if (tClass == Puiple.class)
                persister.write(new PuipleList((List) data), writer);
            else {
                log.fatal("Unknown class");
                writer.close();
                System.exit(1);
            }
            writer.close();
        } catch (Exception e) {
            log.error("Error writing file " + fileName);
            log.error(e);
        }
    }
   }
