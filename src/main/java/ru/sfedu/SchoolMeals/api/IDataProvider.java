package ru.sfedu.SchoolMeals.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.*;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IDataProvider {
    Logger log = LogManager.getLogger(IDataProvider.class);
    void initDataSource() throws IOException;
    abstract <T> List<T> getAll(Class<T> tClass) throws IOException;
    abstract <T> void writeAll(Class<T> tClass, List<T> data) throws IOException;
    public Map<Long, Order> allOrders = new HashMap<Long, Order>();

    //----------------------- CRUD for ComboMeals-----------------------------
    /**
     * Adding a position to the Combo menu
     * @param data Combo menu data to save
     * @return True if save completed successfully, False if there are errors
     */

    default boolean saveComboMeals(ComboMeals data) throws IOException {
        Class<ComboMeals> NClass = ComboMeals.class;
        ComboMeals c = (ComboMeals) data;
        if (getFoodItemById(c.getFoodId()) == null) {
            log.error("Bad ComboMeals: it's FoodItem doesn't exist");
            return false;
        }
        List<ComboMeals> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != data.getId())
                .collect(Collectors.toList());
        updated.add(data);
        writeAll(NClass, updated);
        log.info("Write ComboMeals");
        return true;
    }

    /**
     * Removing a position from the Combo menu
     * @param id id of the item to delete
     * @return True if removing completed successfully, False if there are errors
     */

    default boolean deleteComboMeals(long id) throws IOException {
        Class<ComboMeals> NClass = ComboMeals.class;
        List<ComboMeals> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());
        writeAll(NClass, updated);
        log.info("Delete ComboMeals ");
        return true;
    };
    /**
     * Getting a position from the Combo menu by id
     * @param id id of the item
     * @return Class instance ComboMeals
     */
    default ComboMeals getComboMealsById(long id) throws IOException {
        Class<ComboMeals> NClass = ComboMeals.class;
        List<ComboMeals> data = getAll(NClass);
        log.info("Read ComboMeals");
        Optional<ComboMeals> opt = data.stream().filter(t -> t.getId() == id).findFirst();
        if (opt.isPresent())
            return opt.get();
        else
            log.error("ComboMeals with id = " + id + " not found");
        return null;
    }

    /**
     * Getting all positions ComboMeals
     * @return List ComboMeals
     */
    default List<ComboMeals> getAllComboMeals() throws IOException {return getAll(ComboMeals.class);}

    //------------------------ CRUD for FoodCategory---------------------
    /**
     * Adding a position to the Food category
     * @param data Food category data to save
     * @return True if save completed successfully, False if there are errors
     */

    default boolean saveFoodCategory(FoodCategory data) throws IOException {
        Class<FoodCategory> NClass = FoodCategory.class;
        List<FoodCategory> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != data.getId())
                .collect(Collectors.toList());
        updated.add(data);
        writeAll(NClass, updated);
        log.info("Write FoodCategory");
        return true;
    }

    /**
     * Removing a position from the Food category
     * @param id id of the item to delete
     * @return True if removing completed successfully, False if there are errors
     */

    default boolean deleteFoodCategory(long id) throws IOException {
        Class<FoodCategory> NClass = FoodCategory.class;
        FoodCategory data = getFoodCategoryById(id);
        if (getAllFoodItems().stream().anyMatch(e -> e.getCategory() == data.getId())) {
            log.error("Unable to delete FoodCategory,you need delete FoodItems first");
            return false;
        }
        List<FoodCategory> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());
        writeAll(NClass, updated);
        log.info("Delete FoodCategory ");
        return true;
    };
    /**
     * Getting a position from the Food category by id
     * @param id id of the item
     * @return Class instance FoodCategory
     */
    default FoodCategory getFoodCategoryById(long id) throws IOException {
        Class<FoodCategory> NClass = FoodCategory.class;
        List<FoodCategory> data = getAll(NClass);
        log.info("Read FoodCategory");
        Optional<FoodCategory> opt = data.stream().filter(t -> t.getId() == id).findFirst();
        if (opt.isPresent())
            return opt.get();
        else
            log.error("FoodCategory with id = " + id + " not found");
        return null;}

    /**
     * Getting all positions FoodCategory
     * @return List FoodCategory
     */
    default List<FoodCategory> getAllFoodCategory() throws IOException {return getAll(FoodCategory.class);}

//------------------------ CRUD for FoodItem---------------------
    /**
     * Adding a position to the Food
     * @param data Food data to save
     * @return True if save completed successfully, False if there are errors
     */

    default boolean saveFoodItem(FoodItem data) throws IOException {
        Class<FoodItem> NClass = FoodItem.class;
        FoodItem f = (FoodItem) data; //разобраться, как хранить типы
        if (getFoodCategoryById(f.getCategory()) == null) {
            log.error("Bad FoodItem: it's FoodCategory doesn't exist");
            return false;
        }
        List<FoodItem> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != data.getId())
                .collect(Collectors.toList());
        updated.add(data);
        writeAll(NClass, updated);
        log.info("Write FoodItem");
        return true;
    }

    /**
     * Removing a position from the Food
     * @param id id of the item to delete
     * @return True if removing completed successfully, False if there are errors
     */

    default boolean deleteFoodItem(long id) throws IOException {
        Class<FoodItem> NClass = FoodItem.class;
        if (getAllComboMeals().stream().anyMatch(comboMeals -> comboMeals.getFoodId() == id)) {
            log.error("Unable to delete FoodItem, delete it's comboMeals first");
            return false;
        }
        List<FoodItem> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());
        writeAll(NClass, updated);
        log.info("Delete FoodItem ");
        return true;
    };
    /**
     * Getting a position from the Food by id
     * @param id id of the item
     * @return Class instance FoodItem
     */
    default FoodItem getFoodItemById(long id) throws IOException {
        Class<FoodItem> NClass = FoodItem.class;
        List<FoodItem> data = getAll(NClass);
        log.info("Read FoodItem");
        Optional<FoodItem> opt = data.stream().filter(t -> t.getId() == id).findFirst();
        if (opt.isPresent())
            return opt.get();
        else
            log.error("FoodItem with id = " + id + " not found");
        return null;}

    /**
     * Getting all positions FoodItem
     * @return List FoodItem
     */
    default List<FoodItem> getAllFoodItems() throws IOException {return getAll(FoodItem.class);}

//------------------------ CRUD for Order---------------------

    /**
     * Adding a position to the Order
     * @param data Order data to save
     * @return True if save completed successfully, False if there are errors
     */

    default boolean saveOrder(Order data) throws IOException {//save order into updatedList but this list is unreacheable
        Class<Order> NClass = Order.class;
        List<Order> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != data.getId())
                .collect(Collectors.toList());
        updated.add(data);
        writeAll(NClass, updated);
        log.info("Write Order");
        return true;
    }

    /**
     * Removing a position from the Order
     * @param id id of the item to delete
     * @return True if removing completed successfully, False if there are errors
     */

    default boolean deleteOrder(long id) throws IOException {
        Class<Order> NClass = Order.class;
        List<Order> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());
        writeAll(NClass, updated);
        log.info("Delete Order ");
        return true;
    };
    /**
     * Getting a position from the Order by id
     * @param id id of the item
     * @return Class instance Order
     */
    default Order getOrderById(long id) throws IOException {
        Class<Order> NClass = Order.class;
        List<Order> data = getAll(NClass);
        log.info("Read Order");
        Optional<Order> opt = data.stream().filter(t -> t.getId() == id).findFirst();
        if (opt.isPresent())
            return opt.get();
        else
            log.error("Order with id = " + id + " not found");
        return null;
    }

    /**
     * Getting all positions Order
     * @return List Order
     */
    default List<Order> getAllOrders() throws IOException {return getAll(Order.class);}
//------------------------ CRUD for Staff---------------------
    /**
     * Adding a position to the Staff
     * @param data Staff data to save
     * @return True if save completed successfully, False if there are errors
     */

    default boolean saveStaff(Staff data) throws IOException {
        Class<Staff> NClass = Staff.class;
        List<Staff> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != data.getId())
                .collect(Collectors.toList());
        updated.add(data);
        writeAll(NClass, updated);
        log.info("Write Staff");
        return true;
    }

    /**
     * Removing a position from the Staff
     * @param id id of the item to delete
     * @return True if removing completed successfully, False if there are errors
     */

    default boolean deleteStaff(long id) throws IOException {
        Class<Staff> NClass = Staff.class;
        List<Staff> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());
        writeAll(NClass, updated);
        log.info("Delete Staff ");
        return true;
    };
    /**
     * Getting a position from the Staff by id
     * @param id id of the item
     * @return Class instance Staff
     */
    default Staff getStaffById(long id) throws IOException {
        Class<Staff> NClass = Staff.class;
        List<Staff> data = getAll(NClass);
        log.info("Read Staff");
        Optional<Staff> opt = data.stream().filter(t -> t.getId() == id).findFirst();
        if (opt.isPresent())
            return opt.get();
        else
            log.error("Staff with id = " + id + " not found");
        return null;}

    /**
     * Getting all positions Staff
     * @return List Staff
     */
    default List<Staff> getAllStaff() throws IOException {return getAll(Staff.class);}

//------------------------ CRUD for Puiple---------------------
    /**
     * Adding a position to the Puiple
     * @param data Puiple data to save
     * @return True if save completed successfully, False if there are errors
     */

    default boolean savePuiple(Puiple data) throws IOException {
        Class<Puiple> NClass = Puiple.class;
        List<Puiple> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != data.getId())
                .collect(Collectors.toList());
        updated.add(data);
        writeAll(NClass, updated);
        log.info("Write Puiple");
        return true;
    }

    /**
     * Removing a position from the Puiple
     * @param id id of the item to delete
     * @return True if removing completed successfully, False if there are errors
     */

    default boolean deletePuiple(long id) throws IOException {
        Class<Puiple> NClass = Puiple.class;
        List<Puiple> updated = getAll(NClass).stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());
        writeAll(NClass, updated);
        log.info("Delete Puiple ");
        return true;
    }
    /**
     * Getting a position from the Puiple by id
     * @param id id of the item
     * @return Class instance Puiple
     */

    default Puiple getPuipleById(long id) throws IOException {
        Class<Puiple> NClass = Puiple.class;
        List<Puiple> data = getAll(NClass);
        log.info("Read Puiple");
        Optional<Puiple> opt = data.stream().filter(t -> t.getId() == id).findAny();
        if (opt.isPresent())
            return opt.get();
        else
            log.error("Puiple with id = " + id + " not found");
        return null;}

    /**
     * Getting all positions Puiple
     * @return List Puiple
     */
    default List<Puiple> getAllPuiple() throws IOException {return getAll(Puiple.class);}

    /**
     * Get a Timestamp date from String
     * @return Timestamp
     */
    default Timestamp StringToTimestamp(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse(date);
        return new Timestamp(parsedDate.getTime());
    }
//--------------------------------Create Order------------------------------
    /**
     * Create order
     * @return Order order
     */
    default Order createOrder(Integer customerId, String date,  long[] itemsId, boolean selectcombo) throws IOException, ParseException {
        List<FoodItem> orderedMeals;

        Order order = new Order(customerId, customerId, StringToTimestamp(date), OrderStatus.PRE, 0.0);
        if(!selectcombo)
            orderedMeals =  pickMeals(itemsId);
        else
            orderedMeals = selectCombo(itemsId[0]);
        order.setMeals(orderedMeals);
        order.setCombo(selectcombo);
        saveOrder(order);
        allOrders.put(order.getId(), order);
        log.info("Order created ");
        return  order;
    }



//--------------------------------Pick meals------------------------------
    /**
     * pick the desired meals from the global list
     * @return List<FoodItem> pickedMeals
     */
    default List<FoodItem> pickMeals(long[] itemsId) throws IOException {
        List<FoodItem> pickedMeals = new ArrayList<>();
        FoodItem item;
        for (long l : itemsId) {//check if exist in stock
            item = getFoodItemById(l);
            if (item == null)
                log.error("Item was not added to order, item don't exist");
            else if (!item.getInStock()){
                log.error("Item was not added to order, is not in stock");
            }
            else {
                pickedMeals.add(item);
                log.info("Item " + item.getItemName_() + "added to order succesfully.");
            }
        }
        return pickedMeals;
    }
//--------------------------------Select combo------------------------------
    /**
     * pick the combo meals
     * @return List of meals
     */
    default List<FoodItem> selectCombo(long idCombo) throws IOException {//need configure the combo with edit combo
        ComboMeals combo = getComboMealsById(idCombo);
        if(combo == null){
            log.info("Combo with Id :"  + idCombo + "does not exist");
        }
        List<FoodItem> items = pickMeals(getComboMealsById(idCombo).getItemsId());
        if (items == null)
        {
            log.error("No Food Items in combo " + combo.getName());
        }
        return items;
    }
//--------------------------------Make changes to Order------------------------------
    /**
     * Change an order
     * @return Order with updated list of meals
     */
    default Order makeChangesToOrder(Order order, long[] itemsId, boolean add) throws IOException {//true add, false delete
        List<FoodItem> pickedMeals = order.getMeals();
        FoodItem item;
        for (long l : itemsId) {//check if exist in stock
            item = getFoodItemById(l);
            if (item == null)
                log.error("Item with Id: " + l + " doesn't exist");
            else if (!item.getInStock()){
                log.error("Item with Id: " + l + " is not in stock");
            }
            else if (add){
                pickedMeals.add(item);
                log.info("Item " + item.getItemName_() + "added to order succesfully.");
            }
            else{
                pickedMeals.remove(item);
                log.info("Item " + item.getItemName_() + "removed from order succesfully.");
            }
        }
        order.setMeals(pickedMeals);
        return order;
    }
//--------------------------------Cancel Order--------------------------------------
    /**
     * Cancel an order
     * @return true if succesfully canceled
     */
    default boolean cancelOrder(Order order) throws IOException {
        boolean isCanceled = false;
        if(order.getStatus() == OrderStatus.PRE)
        {
            deleteOrder(order.getId());
            log.info("Order with Id: " + order.getId() + " canceled succesfully.");
            isCanceled = true;
            return isCanceled;
        }
        log.error("Order with Id: " + order.getId() + " was not canceled.");
        return isCanceled;
    }

//--------------------------------View order history---------------------------------
    /**
     * view all order with this custormerId
     * @return StringBuffer with all orders with customerId
     */
    default StringBuffer viewOrderHistory(Integer customerId) throws IOException {
        Class<Order> NClass = Order.class;
        List<Order> data = getAll(NClass);
        log.info("View order history");
        StringBuffer buf = new StringBuffer();
        for(Order element : data)
        {
            if(element.getPupilId() == customerId){
                buf.append(element.toString());
            }
        }
        log.info("Order history created");
        return buf;
    }

//--------------------------------Edit Combo-----------------------------------------
    /**
     * Update the food items of a combo
     * @return updated combo
     */
    default ComboMeals editCombo(ComboMeals comboMeals, long[] itemsId) throws IOException {
        boolean isUpdated = false;
        if (comboMeals!= null && itemsId != null){
            comboMeals.setItemsId(itemsId);
            log.info("Combo " + comboMeals.getName() + " succesfully updated");
            isUpdated = true;
        }
        else
            log.info("Unable to update this combo");
        return comboMeals;
    }
//--------------------------------Create Report--------------------------------------
    /**
     * Create report of all order in an specific period
     * @return StringBuffer with report information
     */
    default StringBuffer createReport(String startPeriod, String endPeriod, OrderStatus status) throws IOException, ParseException {
        Class<Order> NClass = Order.class;
        List<Order> data = getAll(NClass);
        Timestamp start = StringToTimestamp(startPeriod);
        Timestamp end = StringToTimestamp(endPeriod);
        StringBuffer buf = new StringBuffer();
        int i = 0;
        for(Order order: data) {
            Timestamp orderData = StringToTimestamp(order.getDate());
            if(order.getStatus() == status && orderData.after(start) && orderData.before(end)){
                buf.append(order.toString()).append('\n');
                i++;
            }
        }
        if(i == 0){
            log.error("There are not orders in this time period");
            return null;
        }
        log.info("Report created");
        return buf;
    }
    
//--------------------------------Approv Order---------------------------------------
    /**
     * Set the total order cost and change the order to approved status
     * @return Order with new changes
     */
    default Order approvOrder(Order order) throws IOException {//true add, false delte

    order.setTotalCost((long)calculateTotalCost(order));
    if(order.getStatus() == OrderStatus.PRE)
    {
        order.setStatus(OrderStatus.APPROV);
        log.info("Order with Id: " + order.getId() + " succesfully Approved");
    }
    else
        log.error("Order with Id: " + order.getId() + "  is alreay aprroved");
    //deleteOrder(order.getId());
    saveOrder(order);
    return order;
    }

//--------------------------------Calculate Total Cost---------------------------------
    /**
     * Calculate total cost cosidering possible discounts
     * For every repeated element in all orders, exist a 1% discount to the original cost
     * Discounts are not available for FoodItems of combo, and also the FoodItems from combo
     * are not considered to calculate discount
     * @return Total price of the order
     */

    default double calculateTotalCost(Order order) throws IOException {
        Class<Order> NClass = Order.class;
        List<Order> data = getAll(NClass);
        List<FoodItem> allItemsForDisccount = new ArrayList<>();
        Map<Long, Order> map = allOrders;
        for(Order ord : data){
            ord = allOrders.get(ord.getId());
            if(!ord.getCombo()){//TODO TODAYS DATA
                allItemsForDisccount.addAll(ord.getMeals());
            }
        }
        double cost = 0;
        for(FoodItem meal : order.getMeals())
        {
            if(!order.getCombo())
                meal.setPrice(meal.getPrice() - meal.getPrice()*(Collections.frequency(allItemsForDisccount, meal)-1)/100);
            cost = cost + meal.getPrice();
        }
        log.info("Cost for order with Id: " + order.getId() + " succesfully calculated.");
        return cost;
    }
}