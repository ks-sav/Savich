package ru.sfedu.SchoolMeals.model.api;

import org.junit.Before;
import org.junit.Test;
import ru.sfedu.SchoolMeals.model.bean.*;

import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;
public abstract class AbstractApiTest {
    public  IDataProvider dp;

    public abstract IDataProvider getiDataProvider();
    public abstract void setUpProperties();
    public abstract void cleanBeforeRun();

    @Before
    public void init(){
        cleanBeforeRun();
        setUpProperties();
        dp = getiDataProvider();
        dp.initDataSource();
    }

    private final Order order0 = new Order(0,0 , new Timestamp(1), "PRE",123);
    private final Order order1 = new Order(1,2, new Timestamp(1), "PRE",321.3);
    private final Order order11 = new Order(1,2, new Timestamp(1), "PRE",12.50);

    private final Staff staff0 = new Staff(0,"Amina Antonovna",Boolean.TRUE);
    private final Staff staff1 = new Staff(1,"Anna Arcadievna", Boolean.FALSE);
    private final Staff staff2 = new Staff(2,"Sergey Sergeevich",Boolean.FALSE);

    private final FoodCategory foodCategory0 = new FoodCategory(0,"Meat");
    private final FoodCategory foodCategory00 = new FoodCategory(0,"Soup");
    private final FoodCategory foodCategory1 = new FoodCategory(1,"Sweet");

    private final Puiple puiple0 = new Puiple(0,"Oleg",5, Boolean.TRUE);
    private final Puiple puiple00 = new Puiple(0,"Oleg",6, Boolean.TRUE);
    private final Puiple puiple1 = new Puiple(1,"Kirill",5,Boolean.FALSE);
    private final Puiple puiple11 = new Puiple(1,"Kirill",10 ,Boolean.FALSE);

    private final FoodItem foodItem1 = new FoodItem(1, "Borsh", 100, "Nice", 0,Boolean.TRUE);
    private final FoodItem foodItem2 = new FoodItem(2,"Kompot", 20, "Good", 1,Boolean.TRUE);
    private final FoodItem foodItem3 = new FoodItem(3,"Limonad", 10.80 ,"Super",1,Boolean.TRUE);
    private final FoodItem foodItem4 = new FoodItem(4,"Pureshka", 112, "Bomba!",1,Boolean.TRUE);


    @Test
    public void writeAndUpdateOrderTest(){
        dp.saveOrder(order0);
        assertEquals(1, dp.getAllOrders().size());
        dp.saveOrder(order1);
        assertEquals(order0, dp.getOrderById(0));
        assertEquals(order1, dp.getOrderById(1));
        assertEquals(2, dp.getAllOrders().size());
        dp.saveOrder(order0);
        assertEquals(order0, dp.getOrderById(0));
        assertEquals(2, dp.getAllOrders().size());
    }

    @Test
    public void deleteOrderTest(){
        dp.saveOrder(order0);
        dp.saveOrder(order1);
        assertEquals(2, dp.getAllOrders().size());
        dp.deleteOrder(0);
        assertEquals(1, dp.getAllOrders().size());
        dp.deleteOrder(3);
        assertEquals(1, dp.getAllOrders().size());
    }

    @Test
    public void writeAndUpdateStaffTest(){
        dp.saveStaff(staff0);
        assertEquals(1, dp.getAllStaff().size());
        dp.saveStaff(staff1);
        assertEquals(staff0, dp.getStaffById(0));
        assertEquals(staff1, dp.getStaffById(1));
        assertEquals(2, dp.getAllStaff().size());
        dp.saveStaff(staff0);
        assertEquals(staff0, dp.getStaffById(0));
        assertEquals(2, dp.getAllStaff().size());
    }

    @Test
    public void deleteStaffTest(){
        dp.saveStaff(staff0);
        dp.saveStaff(staff1);
        assertEquals(2, dp.getAllStaff().size());
        dp.deleteStaff(0);
        assertEquals(1, dp.getAllStaff().size());
        dp.deleteStaff(3);
        assertEquals(1, dp.getAllStaff().size());
    }

    public void writeAndUpdateFoodCategoryTest(){
        dp.saveFoodCategory(foodCategory0);
        assertEquals(1, dp.getAllFoodCategory().size());
        dp.saveFoodCategory(foodCategory1);
        assertEquals(foodCategory0, dp.getFoodCategoryById(0));
        assertEquals(foodCategory1, dp.getFoodCategoryById(1));
        assertEquals(2, dp.getAllFoodCategory().size());
        dp.saveFoodCategory(foodCategory0);
        assertEquals(foodCategory0, dp.getFoodCategoryById(0));
        assertEquals(2, dp.getAllFoodCategory().size());
    }

    @Test
    public void deleteFoodCategoryTest(){
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        assertEquals(2, dp.getAllFoodCategory().size());
        dp.deleteFoodCategory(0);
        assertEquals(1, dp.getAllFoodCategory().size());
        dp.deleteFoodCategory(3);
        assertEquals(1, dp.getAllFoodCategory().size());
    }

    @Test
    public void writeAndUpdateFoodItemTest(){
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        dp.saveStaff(staff0);
        dp.saveStaff(staff1);
        dp.saveFoodItem(foodItem1);
        assertEquals(1, dp.getAllFoodItems().size());
        dp.saveFoodItem(foodItem1);
        assertEquals(foodItem1, dp.getFoodItemById(1));
        assertEquals(foodItem2, dp.getFoodItemById(2));
        assertEquals(2, dp.getAllFoodItems().size());
        dp.saveFoodItem(foodItem1);
        assertEquals(foodItem1, dp.getFoodItemById(1));
        assertEquals(2, dp.getAllFoodItems().size());
    }

    @Test
    public void deleteFoodItemTest(){
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        dp.saveStaff(staff0);
        dp.saveStaff(staff1);
        dp.saveFoodItem(foodItem1);
        dp.saveFoodItem(foodItem2);
        assertEquals(2, dp.getAllFoodItems().size());
        dp.deleteFoodItem(0);
        assertEquals(1, dp.getAllFoodItems().size());
        dp.deleteFoodItem(3);
        assertEquals(1, dp.getAllFoodItems().size());
    }

    @Test
    public void writeAndUpdatePuipleTest(){
        dp.saveOrder(order0);
        dp.saveOrder(order1);
        dp.saveStaff(staff0);
        dp.saveStaff(staff1);
        dp.savePuiple(puiple0);
        assertEquals(1, dp.getAllPuiple().size());
        dp.savePuiple(puiple1);
        assertEquals(puiple00, dp.getPuipleById(0));
        assertEquals(puiple11, dp.getPuipleById(1));
        assertEquals(2, dp.getAllPuiple().size());
        dp.savePuiple(puiple00);
        assertEquals(puiple00, dp.getPuipleById(0));
        assertEquals(2, dp.getAllPuiple().size());
    }

    @Test
    public void deletePuipleTest(){
        dp.saveOrder(order0);
        dp.saveOrder(order1);
        dp.saveStaff(staff0);
        dp.saveStaff(staff1);
        dp.savePuiple(puiple00);
        dp.savePuiple(puiple11);
        assertEquals(2, dp.getAllPuiple().size());
        dp.deletePuiple(0);
        assertEquals(1, dp.getAllPuiple().size());
        dp.deletePuiple(3);
        assertEquals(1, dp.getAllPuiple().size());
    }

    @Test
    public void invalidFoodItemCreationTest(){
        dp.saveFoodCategory(foodCategory0);
        dp.saveStaff(staff0);
        dp.saveFoodItem(foodItem1);
        assertEquals(1, dp.getAllFoodItems().size());
        dp.saveFoodItem(foodItem1);
        assertEquals(1, dp.getAllFoodItems().size());
        dp.saveFoodItem(foodItem1);
        assertEquals(1, dp.getAllFoodItems().size());
    }

    @Test
    public void invalidPuipleCreationTest(){
        dp.saveOrder(order0);
        dp.saveStaff(staff0);
        dp.savePuiple(puiple00);
        assertEquals(1, dp.getAllPuiple().size());
        dp.savePuiple(puiple1);
        assertEquals(1, dp.getAllPuiple().size());
        dp.savePuiple(puiple11);
        assertEquals(1, dp.getAllPuiple().size());
    }
}
