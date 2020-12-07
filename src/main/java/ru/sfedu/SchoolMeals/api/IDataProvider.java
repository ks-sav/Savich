package ru.sfedu.SchoolMeals.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.SchoolMeals.model.*;
import ru.sfedu.SchoolMeals.model.bean.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class IDataProvider {
    private final Logger log = LogManager.getLogger(IDataProvider.class);

    public abstract void initDataSource();
    protected abstract <T extends WithId> List<T> getAll(Class<T> tClass) throws IOException;
    protected abstract <T extends WithId> void writeAll(Class<T> tClass, List<T> data) throws IOException;

    protected <T extends WithId> boolean validate(Class<T> tClass, T data) throws IOException {
        if (data == null) return false;
        if (tClass == FoodCategory.class)
            return true;
        else if (tClass == Puiple.class)
            return true;
        else if (tClass == Staff.class)
            return true;
        else if (tClass == Customer.class)
            return true;
        else if (tClass == FoodItem.class) {
            FoodItem f = (FoodItem) data; //разобраться, как хранить типы
            if (getFoodCategoryById(f.getCategory()) == null) {
                log.error("Bad FoodItem: it's FoodCategory doesn't exist");
                return false;
            }
            return true;}
        else if (tClass == Order.class) {
            Order o = (Order) data;
            /*if (getPuipleById(o.getPupilId()) == null) {
                log.error("Bad Order: it's Customer doesn't exist");
                return false;
            }*/
            return true;}
        else if (tClass == ComboMeals.class) {
            ComboMeals c = (ComboMeals) data;
                if (getFoodItemById(c.getFoodId()) == null) {
                    log.error("Bad ComboMeals: it's FoodItem doesn't exist");
                    return false;
                }
                return true;
        } else {
            log.fatal("Unknown type "+ tClass.getSimpleName());
            System.exit(1);
            return false;
        }

    }

    protected <T extends WithId> boolean possibleToDelete(Class<T> tClass, long id) throws IOException {
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
        }
        else if (tClass == FoodItem.class) {
            if (getAllComboMeals().stream().anyMatch(comboMeals -> comboMeals.getFoodId() == data.getId())) {
                log.error("Unable to delete FoodItem, delete it's comboMeals first");
                return false;
            }
            return true;
        }
         else {
            log.fatal("Unknown type " + tClass.getSimpleName());
            System.exit(1);
            return false;
        }
    }

    protected <T extends WithId> T getById(Class<T> tClass, long id) throws IOException {
        List<T> data = getAll(tClass);
        log.info("Read: " + tClass.getSimpleName());
        Optional<T> opt = data.stream().filter(t -> t.getId() == id).findFirst();
        if (opt.isPresent())
            return opt.get();
        else
            log.error(tClass.getSimpleName() + " with id = " + id + " not found");
        return null;
    }

    protected <T extends WithId> void save(Class<T> tClass, T data) throws IOException {
        if (validate(tClass, data)) {
            List<T> updated = getAll(tClass).stream()
                    .filter(t -> t.getId() != data.getId())
                    .collect(Collectors.toList());
            updated.add(data);
            writeAll(tClass, updated);
            log.info("Write: " + tClass.getSimpleName());
        }
    }

    protected <T extends WithId> void delete(Class<T> tClass, long id) throws IOException {
        if (possibleToDelete(tClass, id)) {
            List<T> updated = getAll(tClass).stream()
                    .filter(t -> t.getId() != id)
                    .collect(Collectors.toList());
            writeAll(tClass, updated);
            log.info("Delete: " + tClass.getSimpleName());
        }
    }

    public void saveComboMeals(ComboMeals comboMeals) throws IOException {save(ComboMeals.class, comboMeals);}
    public void deleteComboMeals(long id) throws IOException {delete(ComboMeals.class, id);}
    public ComboMeals getComboMealsById(long id) throws IOException {return getById(ComboMeals.class, id);}
    public List<ComboMeals> getAllComboMeals() throws IOException {return getAll(ComboMeals.class);}

    public void saveFoodCategory(FoodCategory foodCategory) throws IOException {save(FoodCategory.class, foodCategory);}
    public void deleteFoodCategory(long id) throws IOException {delete(FoodCategory.class, id);}
    public FoodCategory getFoodCategoryById(long id) throws IOException {return getById(FoodCategory.class, id);}
    public List<FoodCategory> getAllFoodCategory() throws IOException {return getAll(FoodCategory.class);}

    public void saveFoodItem(FoodItem employee) throws IOException {save(FoodItem.class, employee);}
    public void deleteFoodItem(long id) throws IOException {delete(FoodItem.class, id);}
    public FoodItem getFoodItemById(long id) throws IOException {return getById(FoodItem.class, id);}
    public List<FoodItem> getAllFoodItems() throws IOException {return getAll(FoodItem.class);}

    public void saveOrder(Order order) throws IOException {save(Order.class,order);}
    public void deleteOrder(long id) throws IOException {delete(Order.class, id);}
    public Order getOrderById(long id) throws IOException {return getById(Order.class, id);}
    public List<Order> getAllOrders() throws IOException {return getAll(Order.class);}

    public void saveStaff(Staff staff) throws IOException {save(Staff.class, staff);}
    public void deleteStaff(long id) throws IOException {delete(Staff.class, id);}
    public Staff getStaffById(long id) throws IOException {return getById(Staff.class, id);}
    public List<Staff> getAllStaff() throws IOException {return getAll(Staff.class);}

    public void savePuiple(Puiple puiple) throws IOException {save(Puiple.class, puiple);}
    public void deletePuiple(long id) throws IOException {delete(Puiple.class, id);}
    public Puiple getPuipleById(long id) throws IOException {return getById(Puiple.class, id);}
    public List<Puiple> getAllPuiple() throws IOException {return getAll(Puiple.class);}

}