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
                rs.getLong(3),
                rs.getBoolean(4)
            );

        else if (aClass == ComboMeals.class)
            return new ComboMeals(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),//тут вообзе то должен быть List
                    rs.getLong(4)
            );
        else if (aClass == Staff.class)
            return new Staff(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getBoolean(3)
            );

        else if (aClass == Order.class)
            return new Order(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getTimestamp(3),
                    OrderStatus.valueOf(rs.getString(4)),
                    rs.getDouble(5)
                    );
                    //разобраться как поулчить енум
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
    public <T extends WithId> List<T> getAll(Class<T> tClass) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from \"" + tClass.getSimpleName()+"\"");
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
        PreparedStatement pst = connection.prepareStatement("insert into \"FoodCategory\" (id,categoryName) values(?, ?)");
        for (FoodCategory a : foodCategory) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getCategoryName());
            pst.execute();
        }
    }
    private void writeComboMeals(List<ComboMeals> comboMeals) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into \"ComboMeals\" (id,comboId,name,foodId) values(?, ?,?,?)");
        for (ComboMeals a : comboMeals) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getComboId());
            pst.setObject(3, a.getName());
            pst.setObject(4, a.getFoodId());
            pst.execute();
        }
    }
    private void writeFoodItem(List<FoodItem> foodItem) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into \"FoodItem\" (id,itemName,price,description,category_id,inStock) values(?, ?,?,?,?,?)");
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
        PreparedStatement pst = connection.prepareStatement("insert into \"Order\" (id,customerId,date,status,totalCost) values(?, ?, ?, ?, ?)");
        for (Order a : order) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getPupilId());
            pst.setObject(3, a.getDate());
            pst.setObject(4, a.getStatus().name());
            pst.setObject(5, a.getTotalCost());
            pst.execute();
        }
    }
    //А ШТО ДЕЛАЦ ЕСЛИ ID заказчика = или школьник или стафф...непонятно...
    private void writePuiple(List<Puiple> puiple) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into \"Puiple\" (id,name,nClass,freeMeals) values(?, ?, ?, ?)");
        for (Puiple a : puiple) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getName());
            pst.setObject(3, a.getNClass());
            pst.setObject(4, a.getFreeMeals());
            pst.execute();
        }
    }
    private void writeStaff(List<Staff> staff) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("insert into \"Staff\" (id,name,unionMember) values(?, ?,?)");
        for (Staff a : staff) {
            pst.setObject(1, a.getId());
            pst.setObject(2, a.getName());
            pst.setObject(3, a.getUnionMember());
            pst.execute();
        }
    }

    @Override
    public <T extends WithId> void writeAll(Class<T> tClass, List<T> data) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from \"" + tClass.getSimpleName()+ "\"");
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
