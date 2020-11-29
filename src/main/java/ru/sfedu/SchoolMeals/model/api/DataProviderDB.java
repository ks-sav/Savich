package ru.sfedu.SchoolMeals.model.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.bean.*;
import ru.sfedu.SchoolMeals.utils.ConfigurationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static ru.sfedu.SchoolMeals.model.constants.Constants.*;
import static ru.sfedu.SchoolMeals.utils.ConfigurationUtil.getConfigurationEntry;

public class DataProviderDB extends IDataProvider{

    private static final String DB_DRIVER="db_driver";
    private static final String DB_USER="db_user";
    private static final String DB_PASS="db_pass";
    private static final String DB_URL="db_url";
    private static final String DB_CREATE="src/main/resources/create.sql";
    private static final String DB_INSERT="INSERT INTO \"%s\" (%s) VALUES (%s)";
    private static final String DB_SELECT="SELECT * FROM \"%s\" WHERE id=%d";
    private static Logger log = LogManager.getLogger(DataProviderDB.class);
    private Connection connection;


    @Override
    public void initDataSource() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            log.fatal("Driver not found " + DB_DRIVER);
            System.exit(1);
        }

        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            String createDb = new String(Files.readAllBytes(Paths.get(DB_CREATE)));
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
                rs.getLong(0),
                rs.getString(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getBoolean(4)
            );
        /*
        else if (aClass == ComboMeals.class)
            return new ComboMeals(
                    rs.getLong(0),
                    rs.getTimestamp(1),
                    rs.getString(2),//тут вообзе то должен быть List
                    rs.getDouble(3)(
            );
                  */
        else if (aClass == Staff.class)
            return new Staff(
                    rs.getLong(0),
                    rs.getString(1),
                    rs.getBoolean(2)
            );
        else if (aClass == Order.class)
            return new Order(
                    rs.getLong(0),
                    rs.getLong(1),
                    rs.getTimestamp(2),
                    rs.getString(3),
                    rs.getDouble(4)
                    );
        else if (aClass == FoodItem.class)
            return new FoodItem(
                    rs.getLong(0),
                    rs.getString(1),
                    rs.getDouble(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getBoolean(5)
            );
        else if (aClass == FoodCategory.class)
            return new FoodCategory(
                    rs.getLong(0),
                    rs.getString(1)
            );
        else {
            log.fatal("Unknown class");
            System.exit(1);
            return null;
        }
    }
    @Override
    protected <T extends WithId> List<T> getAll(Class<T> tClass) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + tClass.getSimpleName());
            List ans  = new ArrayList<>();
            while (rs.next()) {
                ans.add(dataFromResultSet(rs, tClass));
            }
            return ans;
        } catch (Exception e) {
            log.fatal("Database error");
            log.fatal(e);
            System.exit(1);
        }
        return new ArrayList<>();
    }

    private void writeFoodCategory(List<FoodCategory> foodCategory) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into FoodCategory(id,categoryName) values(?, ?)");
        for (FoodCategory a : foodCategory) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getCategoryName());
            pst.execute();
        }
    }
    private void writeFoodItem(List<FoodItem> foodItem) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into FoodItem(id,itemName,price,description,category_id,inStock) values(?, ?,?,?,?,?)");
        for (FoodItem a : foodItem) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getItemName_());
            pst.setObject(2, a.getPrice());
            pst.setObject(2, a.getDescription());
            pst.setObject(2, a.getCategory());
            pst.setObject(2, a.getInStock());
            pst.execute();
        }
    }
    private void writeOrder(List<Order> order) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into Order(id,customerId,date,status,totalCost) values(?, ?, ?, ?, ?)");
        for (Order a : order) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getPupilId());
            pst.setObject(2, a.getDate());
            pst.setObject(2, a.getStatus());
            pst.setObject(2, a.getTotalCost());
            pst.execute();
        }
    }
    //А ШТО ДЕЛАЦ ЕСЛИ ID заказчика = или школьник или стафф...непонятно...
    private void writePuiple(List<Puiple> puiple) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into Puiple(id,name,nClass,freeMeals) values(?, ?, ?, ?)");
        for (Puiple a : puiple) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getName());
            pst.setObject(2, a.getNClass());
            pst.setObject(2, a.getFreeMeals());
            pst.execute();
        }
    }
    private void writeStaff(List<Staff> staff) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into Staff(id,name,unionMember) values(?, ?,?)");
        for (Staff a : staff) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getName());
            pst.setObject(2, a.getUnionMember());
            pst.execute();
        }
    }

    @Override
    protected <T extends WithId> void writeAll(Class<T> tClass, List<T> data) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from " + tClass.getSimpleName());
            if (tClass == FoodCategory.class)
                writeFoodCategory((List) data);
            /*
            else if (tClass == ComboMeals.class)
                writeComboMeals((List) data);
                //+нет функции. все еще проблема с листом
             */
            else if (tClass == FoodItem.class)
                writeFoodItem((List) data);
            else if (tClass == Order.class)
                writeOrder((List) data);
            else if (tClass == Puiple.class)
                writePuiple((List) data);
            else if (tClass == Staff.class)
                writeStaff((List) data);
            else {
                log.fatal("Unknown class");
                System.exit(1);
            }

        } catch (Exception e) {
            log.fatal("Database error");
            log.fatal(e);
            System.exit(1);
        }
    }

}
