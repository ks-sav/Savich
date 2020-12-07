package ru.sfedu.SchoolMeals.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderCSVTest extends TestBase {
    private final Logger log = LogManager.getLogger();
    @Override
    public IDataProvider getiDataProvider() {
        return new DataProviderCSV();
    }
        private static final String PATH="csv_path";
    private static final String EXT="csv";

    @Override
    public void cleanBeforeRun() throws IOException {
        List<String> filesToClean = new ArrayList<>();
        filesToClean.add(ConfigurationUtil.getConfigurationEntry(PATH) + "Staff" + (ConfigurationUtil.getConfigurationEntry(EXT)));
        filesToClean.add(ConfigurationUtil.getConfigurationEntry(PATH) + "Puiple" + (ConfigurationUtil.getConfigurationEntry(EXT)));
        filesToClean.add(ConfigurationUtil.getConfigurationEntry(PATH) +"Order" + (ConfigurationUtil.getConfigurationEntry(EXT)));
        filesToClean.add(ConfigurationUtil.getConfigurationEntry(PATH) +"FoodItem" + (ConfigurationUtil.getConfigurationEntry(EXT)));
        filesToClean.add(ConfigurationUtil.getConfigurationEntry(PATH) +"FoodCategory" + (ConfigurationUtil.getConfigurationEntry(EXT)));
        filesToClean.add(ConfigurationUtil.getConfigurationEntry(PATH) +"ComboMeals" + (ConfigurationUtil.getConfigurationEntry(EXT)));
        filesToClean.forEach(s -> {
            try {
                new File(s).delete();
                log.debug("File" + s + " is delete");
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}