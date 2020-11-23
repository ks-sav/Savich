/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.SchoolMeals.model.api;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.SchoolMeals.model.bean.Customer;
import ru.sfedu.SchoolMeals.model.constants.WrapperXML;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

/**
 *
 * @author sp2
 */
public class DataProviderXML {
    
    private final String PATH="xml_path";
    
    private final String FILE_EXTENTION="xml";
    
    private static Logger log = LogManager.getLogger(DataProviderXML.class);
    
    public void insertCustomer(List<Customer> customerList) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, Exception{
        try{
            //Проверяем, создан ли файл? Если нет, то создаём
            (new File(this.getFilePath(Customer.class))).createNewFile();
            //Подключаемся к потоку записи файла
            FileWriter writer = new FileWriter(this.getFilePath(Customer.class), false);
            //Определяем сериалайзер
            Serializer serializer = new Persister();
            
            //Определяем контейнер, в котором будут находиться все объекты
            WrapperXML<Customer> xml = new WrapperXML<Customer>();
            //Записываем список объектов в котнейнер
            xml.setList(customerList);
            
            //Записываем в файл
            serializer.write(xml, writer);
        }catch(IndexOutOfBoundsException e){
            log.error(e);
        }
    }
    
    public Customer getCustomerById(long id) throws IOException, Exception{
        List<Customer> list = this.select(Customer.class);
        try{
            Customer customer=list.stream()
                    .filter(el->el.getId()==id)
                    .limit(1)
                    .findFirst().get();
            return customer;
        }catch(NoSuchElementException e){
            log.error(e);
            return null;
        }
    }
    
    public <T> List<T> select(Class cl) throws IOException, Exception{
        //Подключаемся к считывающему потоку из файла
        FileReader fileReader = new FileReader(this.getFilePath(cl));
        //Определяем сериалайзер
        Serializer serializer = new Persister();
        //Определяем контейнер и записываем в него объекты
        WrapperXML xml = serializer.read(WrapperXML.class, fileReader);
        //Если список null, то делаем его пустым списком
        if (xml.getList() == null) xml.setList(new ArrayList<T>());
        //Возвращаем список объектов
        return xml.getList();
    }
    
    /**
     * Получаем путь к файлу
     * @param cl
     * @return
     * @throws IOException 
     */
    private String getFilePath(Class cl) throws IOException{
        return ConfigurationUtil.getConfigurationEntry(PATH)+cl.getSimpleName().toString().toLowerCase()+ ConfigurationUtil.getConfigurationEntry(FILE_EXTENTION);
    }
}
