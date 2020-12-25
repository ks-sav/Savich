package ru.sfedu.SchoolMeals.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.*;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public interface IDataProvider {
    Logger log = LogManager.getLogger(IDataProvider.class);
    void initDataSource() throws IOException;
    abstract <T> List<T> getAll(Class<T> tClass) throws IOException;
    abstract <T> void writeAll(Class<T> tClass, List<T> data) throws IOException;

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


//--------------------------------Create  Order------------------------------
    /**
     * Create order
     * @return Order order
     */
    default Order createOrder(Integer customerId, Sting date,  long[] itemsId, boolean selectcombo) throws IOException {
        List<FoodItem> orderedMeals;
        if(!selectcombo)
            orderedMeals =  pickMeals(itemsId);
        else
            orderedMeals = selectCombo(itemsId[0]);
        Order order = new Order(customerId, date, orderedMeals);
        if(order != null){
            log.info("Order created ");
            order.setStatus(OrderStatus.PRE);
        }
        else
            log.error("Order was not created, check for errors");
        return  order;
    }



    Random rand = new Random();
    int customerId = rand.nextInt(100);
    Sting date = new Sting(new Timestamp(System.currentTimeMillis()));

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
    default List<FoodItem> selectCombo(long idCombo) throws IOException {//Our combo is now a FoodItem
        //ComboMeals combo= getComboMealsById(idCombo);
        Class<FoodItem> NClass = FoodItem.class;
        List<FoodItem> meals = getAll(NClass);
        List<FoodItem> items = new ArrayList<>();
        if (idCombo == 0){
            items.add(meals.get(2));
            items.add(meals.get(5));
            items.add(meals.get(6));
            items.add(meals.get(7));
        }
        if (idCombo == 2){
            items.add(meals.get(3));
            items.add(meals.get(2));
            items.add(meals.get(1));
            items.add(meals.get(0));
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
                log.error("Item with Id: " + l + " don't exist");
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
/*
* Просмотр истории заказов ученика и суммы заказов за прошлый месяц, может использоваться для оплаты питания
Заказ включает в себя номер заказа, дату, список блюд и стоимость.  Включаются только заказы, где OrderStatus= APPROVED

Входные параметры: integer customerId
Возвращаемое значение:
Возвращаемое значение:
StringBuffer orderHistory
либо
NullPointerException e, e.getMessage()

when we create the order, where is the pupilID?
* */
    default StringBuffer viewOrderHistory(Integer id) throws IOException {
        Class<Order> NClass = Order.class;
        List<Order> data = getAll(NClass);
        log.info("View order history");
        Optional<Order> opt = data.stream().filter(t -> t.getPupilId() == id).findFirst();//TODO find all
        if (opt.isPresent())
            return new StringBuffer(opt.toString());//TODO check!!!
        else
            log.error("History with id = " + id + " not found");
        return null;
    }

//--------------------------------Edit Combo-----------------------------------------
    /*
    * Изменить комбо-меню (т.е. список блюд) на конкретной дате
Входные данные:
ComboMeals comboMeals
Возвращаемое значение:
boolean isUpdated
    * */
    default ComboMeals editCombo(ComboMeals comboMeals){
        Boolean isUpdated = false;
        //Change the elements of combo

        return isUpdated;
    }
//--------------------------------Create Report--------------------------------------
/*
* Просмотр списка заказов за определённый период 
Входные данные:
Double startPeriod, Double endPeriod,  OrderStatus status
Возвращаемое значение:
Возвращаемое значение: 
StringBuffer report 
либо
NullPointerException e, e.getMessage()
* */
    default StringBuffer createReport(double startPeriod, double endPeriod, OrderStatus status) throws IOException {
        Class<Order> NClass = Order.class;
        List<Order> data = getAll(NClass);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        log.info("Read Order");
        Optional<Order> opt = data.stream().filter(t -> t.getDate() == timestamp).findFirst();
        if (opt.isPresent())
            return new StringBuffer(opt.toString());
        else
            log.error("Wrong time interval");
        return null;
    }
    
//--------------------------------Approv Order---------------------------------------
/*
* Подтверждение заказа. Подтверждаются только заказы со статусом status=PRE.
 В прецендент включен прецендент calculate Total Cost, где  происходит подсчет итоговой стоимости (totalCost), и статус обновляется до APPROVED

Входные данные:
Order order, Double totalCost
* */
    default Order approvOrder(Order order){//true add, false delte

    order.setTotalCost((long)calculateTotalCost(order));
    //order.setPupilId();
    if(order.getStatus() == OrderStatus.PRE)
        order.setStatus(OrderStatus.APPROV);
    return order;
    }

//--------------------------------Calculate Total Cost---------------------------------
    /*
    * Подсчет итоговой стоимости блюд в зависимости от количества этого блюда в заказах и наличия дополнительных скидок (бесплатное питание у школьников, скидки от профсоюза у работников)

Входные параметры: Order order
Возвращаемое значение:
Double orderCost
    * */

    default double calculateTotalCost() throws IOException {
        Class<Order> NClass = Order.class;
        List<Order> data = getAll(NClass);
        double orderCost = 0;
        for(int i = 0; i < order.getMeals().)
            //TODO dont count combo elements
        return orderCost;
    }
}