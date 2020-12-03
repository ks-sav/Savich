
package ru.sfedu.SchoolMeals.model.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlDataProviderTest extends AbstractApiTest {

    @Override
    public IDataProvider getiDataProvider() {
        return new DataProviderXML();
    }

    @Override
    public void setUpProperties() {
        System.setProperty("ShoolMeals.Staff_xml", "src/main/resources/data/xml/Staff.xml");
        System.setProperty("ShoolMeals.Puiple_xml", "src/main/resources/data/xml/Puiple.xml");
        System.setProperty("ShoolMeals.Order_xml", "src/main/resources/data/xml/Order.xml");
        System.setProperty("ShoolMeals.FoolItem_xml", "src/main/resources/data/xml/FoolItem.xml");
        System.setProperty("ShoolMeals.FoodCategory_xml", "src/main/resources/data/xml/FoodCategory.xml");
    }

    @Override
    public void cleanBeforeRun() {
        List<String> filesToClean = new ArrayList<>();
        filesToClean.add("src/main/resources/data/xml/Staff.xml");
        filesToClean.add("src/main/resources/data/xml/Puiple.xml");
        filesToClean.add("src/main/resources/data/xml/Order.xml");
        filesToClean.add("src/main/resources/data/xml/FoolItem.xml");
        filesToClean.add("src/main/resources/data/xml/FoodCategory.xml");
        filesToClean.forEach(s -> {
            try {
                new File(s).delete();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
