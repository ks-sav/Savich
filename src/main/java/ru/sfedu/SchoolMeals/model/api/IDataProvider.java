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
            FoodItem e = (FoodItem) data;
            if (getFoodItemTypeById(e.getType_id()) == null) {
                log.error("Bad FoodItem: it's FoodCategory doesn't exist");
                return false;
            }
            return true;
        } else if (tClass == Order.class) {
            Order o = (Order) data;
            if (getCustomerById(o.getOrder_id()) == null) {
                log.error("Bad Session: it's Order doesn't exist");
                return false;
            }
            if (getFilmById(o.getFilm_id()) == null) {
                log.error("Bad Session: it's Film doesn't exist");
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

        if (tClass == FoodItem.class)
            return true;
        else if (tClass == Session.class)
            return true;
        else if (tClass == Film.class) {
            if (getAllSessions().stream().anyMatch(session -> session.getFilm_id() == data.getId())) {
                log.error("Unable to delete Film, delete it's Sessions first");
                return false;
            }
            return true;
        } else if (tClass == FoodItemType.class) {
            if (getAllFoodItems().stream().anyMatch(e -> e.getType_id() == data.getId())) {
                log.error("Unable to delete FoodItemType, delete it's FoodItems first");
                return false;
            }
            return true;
        } else if (tClass == Order.class) {
            if (getAllSessions().stream().anyMatch(session -> session.getOrder_id() == data.getId())) {
                log.error("Unable to delete Order, delete it's Sessions first");
                return false;
            } else if (getAllFoodItems().stream().anyMatch(employee -> employee.getOrder_id() == data.getId())) {
                log.error("Unable to delete Order, delete it's FoodItems first");
                return false;
            }
            return true;
        } else {
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

    public void saveFoodItemType(FoodItemType employeeType) {save(FoodItemType.class, employeeType);}
    public void deleteFoodItemType(long id) {delete(FoodItemType.class, id);}
    public FoodItemType getFoodItemTypeById(long id) {return getById(FoodItemType.class, id);}
    public List<FoodItemType> getAllFoodItemTypes() {return getAll(FoodItemType.class);}

    public void saveFoodItem(FoodItem employee) {save(FoodItem.class, employee);}
    public void deleteFoodItem(long id) {delete(FoodItem.class, id);}
    public FoodItem getFoodItemById(long id) {return getById(FoodItem.class, id);}
    public List<FoodItem> getAllFoodItems() {return getAll(FoodItem.class);}

    public void saveOrder(Order hall) {save(Order.class, hall);}
    public void deleteOrder(long id) {delete(Order.class, id);}
    public Order getOrderById(long id) {return getById(Order.class, id);}
    public List<Order> getAllOrders() {return getAll(Order.class);}

    public void saveCustomer(Film film) {save(Film.class, film);}
    public void deleteCustomer(long id) {delete(Film.class, id);}
    public Film getCustomerById(long id) {return getById(Film.class, id);}
    public List<Film> getAllFilms() {return getAll(Film.class);}

    public void saveSession(Session session) {save(Session.class, session);}
    public void deleteSession(long id) {delete(Session.class, id);}
    public Session getSessionById(long id) {return getById(Session.class, id);}
    public List<Session> getAllSessions() {return getAll(Session.class);}

}