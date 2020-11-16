package ru.sfedu.SchoolMeals.api;

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
import ru.sfedu.SchoolMeals.bean.Customer;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class DataProviderCSV {
    private final String PATH = "csv_path";
    private final String FILE_EXTENSION = "csv";
    private static Logger log = LogManager.getLogger(DataProviderCSV.class);

    public void insertCustomer(List<Customer> listCustomer) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try {
            FileWriter writer = new FileWriter(ConfigurationUtil.getConfigurationEntry(PATH) + listCustomer.get(0).getClass().getSimpleName().toLowerCase() + ConfigurationUtil.getConfigurationEntry(FILE_EXTENSION));
            CSVWriter csvWriter = new CSVWriter(writer);
            StatefulBeanToCsv<Customer> beanToCsv = new StatefulBeanToCsvBuilder<Customer>(csvWriter)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(listCustomer);
            csvWriter.close();
        } catch (IndexOutOfBoundsException e) {
            log.error(e);
        }
    }

        public Customer getCustomerById(long id) throws IOException {
            List<Customer> customerList = select(Customer.class);
            try {
                Customer customer = customerList.stream()
                        .filter(el->el.getId()==id)
                        .findFirst().get();
                return customer;
            }catch (NoSuchElementException e){
                log.error(e);
                return null;
        }
    }
    public <T> List<T> select (Class c1) throws IOException {
        FileReader fileReader = new FileReader(ConfigurationUtil.getConfigurationEntry(PATH) + c1.getSimpleName().toLowerCase() + ConfigurationUtil.getConfigurationEntry(FILE_EXTENSION));
        CSVReader csvReader = new CSVReader(fileReader);
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<Customer>(csvReader)
                .withType(c1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<T> list = csvToBean.parse();
        return list;
    }
}
