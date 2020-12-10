package ru.sfedu.SchoolMeals.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.*;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.sfedu.SchoolMeals.Constants.*;

public class DataProviderDB implements IDataProvider{

    private static final String DB_DRIVER="db_driver";
    private static final String DB_USER="db_user";
    private static final String DB_PASS="db_pass";
    private static final String DB_URL="db_url";
    private static final String DB_CREATE="db_create";
    private static Logger log = LogManager.getLogger(DataProviderDB.class);
    private Connection connection;


    @Override
    public void initDataSource() throws IOException {
        try {
            Class.forName(ConfigurationUtil.getConfigurationEntry(DB_DRIVER));
        } catch (ClassNotFoundException | IOException e) {
            log.fatal("Driver not found " + (ConfigurationUtil.getConfigurationEntry(DB_DRIVER)));
            System.exit(1);
        }

        try {
            connection = DriverManager.getConnection(ConfigurationUtil.getConfigurationEntry(DB_URL),ConfigurationUtil.getConfigurationEntry(DB_USER),ConfigurationUtil.getConfigurationEntry(DB_PASS));
            String createDb = new String(Files.readAllBytes(Paths.get(ConfigurationUtil.getConfigurationEntry(DB_CREATE))));
            connection.createStatement().execute(createDb);
        } catch (SQLException | IOException throwables) {
            log.fatal("Unable to connect to database");
            log.fatal(throwables);
            System.exit(1);
        }
    }

    private Object dataFromResultSet(ResultSet rs, Class aClass) throws SQLException {
        if (aClass == Puiple.class)
                return new Puiple(
                rs.getLong(1),
                rs.getString(2),
                 CustomerType.valueOf(rs.getString(3)),
                rs.getLong(4),
                rs.getBoolean(5)
            );

        else if (aClass == ComboMeals.class)
            return new ComboMeals(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),
                    rs.getLong(4)
            );
        else if (aClass == Staff.class)
            return new Staff(
                    rs.getLong(1),
                    rs.getString(2),
                    CustomerType.valueOf(rs.getString(3)),
                    rs.getBoolean(4)
            );

        else if (aClass == Order.class)
            return new Order(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getTimestamp(3),
                    OrderStatus.valueOf(rs.getString(4)),
                    rs.getDouble(5)
                    );

        else if (aClass == FoodItem.class)
            return new FoodItem(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getLong(5),
                    rs.getBoolean(6)
            );
        else if (aClass == FoodCategory.class)
            return new FoodCategory(
                    rs.getLong(1),
                    rs.getString(2)
            );
        else {
            log.fatal("Unknown class");
            System.exit(1);
            return null;
        }
    }

    @Override
    public <T> List<T> getAll(Class<T> tClass) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT + tClass.getSimpleName()+END);
            List ans  = new ArrayList<>();
            while (rs.next()) {
                ans.add(dataFromResultSet(rs, tClass));
            }
            return ans;
        } catch (Exception e) {
            log.fatal("Select is not possible. Database error");
            log.fatal(e);
            System.exit(1);
        }
        return new ArrayList<>();
    }

    private void writeFoodCategory(List<FoodCategory> foodCategory) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INS_FOODCATEGORY);
        for (FoodCategory a : foodCategory) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getCategoryName());
            pst.execute();
        }
    }
    private void writeComboMeals(List<ComboMeals> comboMeals) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INS_COMBOMEALS);
        for (ComboMeals a : comboMeals) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getComboId());
            pst.setObject(3, a.getName());
            pst.setObject(4, a.getFoodId());
            pst.execute();
        }
    }
    private void writeFoodItem(List<FoodItem> foodItem) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INS_FOODITEM);
        for (FoodItem a : foodItem) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getItemName_());
            pst.setObject(3, a.getPrice());
            pst.setObject(4, a.getDescription());
            pst.setObject(5, a.getCategory());
            pst.setObject(6, a.getInStock());
            pst.execute();
        }
    }
    private void writeOrder(List<Order> order) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INS_ORDER);
        for (Order a : order) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getPupilId());
            pst.setObject(3, a.getDate());
            pst.setObject(4, a.getStatus().name());
            pst.setObject(5, a.getTotalCost());
            pst.execute();
        }
    }
    //Что делать:  ID заказчика = или школьник или стафф.
    private void writePuiple(List<Puiple> puiple) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INS_PUIPLE);
        for (Puiple a : puiple) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getName());
            pst.setObject(3, a.getCustomerType().name());
            pst.setObject(4, a.getNClass());
            pst.setObject(5, a.getFreeMeals());
            pst.execute();
        }
    }
    private void writeStaff(List<Staff> staff) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INS_STAFF);
        for (Staff a : staff) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getName());
            pst.setObject(3, a.getCustomerType().name());
            pst.setObject(4, a.getUnionMember());
            pst.execute();
        }
    }

    @Override
    public <T> void writeAll(Class<T> tClass, List<T> data) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(DELETE+ tClass.getSimpleName()+ END);
            if (tClass == FoodCategory.class)
                writeFoodCategory((List) data);
            else if (tClass == ComboMeals.class)
                writeComboMeals((List) data);
            else if (tClass == FoodItem.class)
                writeFoodItem((List) data);
            else if (tClass == Order.class){
                writeOrder((List) data);}
            else if (tClass == Puiple.class)
                writePuiple((List) data);
            else if (tClass == Staff.class)
                writeStaff((List) data);
            else {
                log.fatal("Unknown class");
                System.exit(1);
            }

        } catch (Exception e) {
            log.fatal("Writing is not possible. Database error");
            log.fatal(e);
            System.exit(1);
        }
    }

}
