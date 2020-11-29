package ru.sfedu.SchoolMeals.model.api;

import ru.sfedu.SchoolMeals.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.bean.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class IDataProvider {
    private final Logger log = LogManager.getLogger(IDataProvider.class);

    public abstract void initDataSource();
    protected abstract <T extends WithId> List<T> getAll(Class<T> tClass);
    protected abstract <T extends WithId> void writeAll(Class<T> tClass, List<T> data);

    protected <T extends WithId> boolean validate(Class<T> tClass, T data) {
        if (data == null) return false;
        if (tClass == FoodCategory.class)
            return true;
        else if (tClass == Order.class)
            return true;
        else if (tClass == Puiple.class)
            return true;
        else if (tClass == Customer.class)
            return true;
        else if (tClass == FoodItem.class) {
            FoodItem e = (FoodItem) data; //разораться, как хранить типы
            if (getFoodCategoryById(e.getCategory()) == null) {
                log.error("Bad FoodItem: it's FoodCategory doesn't exist");
                return false;
            }
            return true;}
        else if (tClass == Order.class) {
            Order o = (Order) data;
            if (getPuipleById(o.getPupilId()) == null) {
                log.error("Bad Order: it's Customer doesn't exist");
                return false;
            }
            return true;
        } else {
            log.fatal("Unknown type");
            System.exit(1);
            return false;
        }
    }

    protected <T extends WithId> boolean possibleToDelete(Class<T> tClass, long id) {
        T data = getById(tClass, id);
        if (data == null) return true;
        if (tClass == ComboMeals.class)
            return true;
        if (tClass == Order.class)
            return true;
        else if (tClass == Staff.class)
            return true;
        else if (tClass == Puiple.class)
            return true;
        /*
        else if (tClass == Customer.class) {
            if (getAllSessions().stream().anyMatch(order -> order.getPuipleId() == data.getId())) {
                log.error("Unable to delete Customer, you need delete Orders first");
                return false;
            }
            return true;
        } */
        else if (tClass == FoodCategory.class) {
            if (getAllFoodItems().stream().anyMatch(e -> e.getCategory() == data.getId())) {
                log.error("Unable to delete FoodCategory,you need delete FoodItems first");
                return false;
            }
            return true;
        }/*
        else if (tClass == FoodItem.class) {
            if (getAllSessions().stream().anyMatch(comboMeals -> comboMeals.getList() == data.getId())) {
                log.error("Unable to delete FoodItem, delete it's comboMeals first");
                return false;
            } else if (getAllFoodItems().stream().anyMatch(order -> order.getId() == data.getId())) {
                log.error("Unable to delete FoodItem, delete it's Order first");
                return false;
            }
            return true;
            // разобраться с листом } */
         else {
            log.fatal("Unknown type");
            System.exit(1);
            return false;
        }
    }

    protected <T extends WithId> T getById(Class<T> tClass, long id){
        List<T> data = getAll(tClass);
        log.info("Read: " + tClass.getSimpleName());
        Optional<T> opt = data.stream().filter(t -> t.getId() == id).findFirst();
        if (opt.isPresent())
            return opt.get();
        else
            log.error(tClass.getSimpleName() + " with id = " + id + " not found");
        return null;
    }

    protected <T extends WithId> void save(Class<T> tClass, T data){
        if (validate(tClass, data)) {
            List<T> updated = getAll(tClass).stream()
                    .filter(t -> t.getId() != data.getId())
                    .collect(Collectors.toList());
            updated.add(data);
            writeAll(tClass, updated);
            log.info("Write: " + tClass.getSimpleName());
        }
    }

    protected <T extends WithId> void delete(Class<T> tClass, long id){
        if (possibleToDelete(tClass, id)) {
            List<T> updated = getAll(tClass).stream()
                    .filter(t -> t.getId() != id)
                    .collect(Collectors.toList());
            writeAll(tClass, updated);
            log.info("Delete: " + tClass.getSimpleName());
        }
    }

    public void saveComboMeals(ComboMeals comboMeals) {save(ComboMeals.class, comboMeals);}
    public void deleteComboMeals(long id) {delete(ComboMeals.class, id);}
    public ComboMeals getComboMeals(long id) {return getById(ComboMeals.class, id);}
    public List<ComboMeals> getAllComboMeals() {return getAll(ComboMeals.class);}

    public void saveFoodCategory(FoodCategory foodCategory) {save(FoodCategory.class, foodCategory);}
    public void deleteFoodCategory(long id) {delete(FoodCategory.class, id);}
    public FoodCategory getFoodCategoryById(long id) {return getById(FoodCategory.class, id);}
    public List<FoodCategory> getAllFoodItemTypes() {return getAll(FoodCategory.class);}

    public void saveFoodItem(FoodItem employee) {save(FoodItem.class, employee);}
    public void deleteFoodItem(long id) {delete(FoodItem.class, id);}
    public FoodItem getFoodItemById(long id) {return getById(FoodItem.class, id);}
    public List<FoodItem> getAllFoodItems() {return getAll(FoodItem.class);}

    public void saveOrder(Order order) {save(Order.class,order);}
    public void deleteOrder(long id) {delete(Order.class, id);}
    public Order getOrderById(long id) {return getById(Order.class, id);}
    public List<Order> getAllOrders() {return getAll(Order.class);}

    public void saveStaff(Staff staff) {save(Staff.class, staff);}
    public void deleteStaff(long id) {delete(Staff.class, id);}
    public Staff getStaffById(long id) {return getById(Staff.class, id);}
    public List<Staff> getAllStaff() {return getAll(Staff.class);}

    public void savePuiple(Puiple puiple) {save(Puiple.class, puiple);}
    public void deletePuiple(long id) {delete(Puiple.class, id);}
    public Puiple getPuipleById(long id) {return getById(Puiple.class, id);}
    public List<Puiple> getAllPuiple() {return getAll(Puiple.class);}

}