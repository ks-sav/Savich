package ru.sfedu.SchoolMeals.model.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.SchoolMeals.model.bean.*;

import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;
import static ru.sfedu.SchoolMeals.model.bean.OrderStatus.PRE;

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

    private final Order order0 = new Order(0,1 , new Timestamp(1), PRE,123);
    private final Order order1 = new Order(1,2, new Timestamp(1), PRE,321.3);
    private final Order order11 = new Order(1,333, new Timestamp(1), PRE,12.50);

    private final Staff staff0 = new Staff(0,"Amina Antonovna", Boolean.TRUE);
    private final Staff staff1 = new Staff(1,"Anna Arcadievna", Boolean.FALSE);
    private final Staff staff11 = new Staff(1,"Sergey Sergeevich", Boolean.FALSE);

    private final FoodCategory foodCategory0 = new FoodCategory(0,"Meat");
    private final FoodCategory foodCategory00 = new FoodCategory(0,"Soup");
    private final FoodCategory foodCategory1 = new FoodCategory(1,"Sweet");

    private final Puiple puiple0 = new Puiple(0,"Oleg",5, Boolean.TRUE);
    private final Puiple puiple1 = new Puiple(1,"Kirill",5,Boolean.FALSE);
    private final Puiple puiple11 = new Puiple(1,"Kirill",10 ,Boolean.FALSE);

    private final FoodItem foodItem1 = new FoodItem(1, "Borsh", 100.50, "Nice", 0,Boolean.TRUE);
    private final FoodItem foodItem2 = new FoodItem(2,"Kompot", 20, "Good", 1,Boolean.TRUE);
    private final FoodItem foodItem22 = new FoodItem(2,"Limonad", 10.50 ,"Super",1,Boolean.TRUE);
    private final FoodItem foodItem4 = new FoodItem(4,"Pureshka", 112, "Bomba!",10,Boolean.TRUE);

    private final ComboMeals combo0 = new ComboMeals(1,1 ,"Sunday" , 0);
    private final ComboMeals combo1 = new ComboMeals(1,2 ,"Monday", 1);
    private final ComboMeals combo2 = new ComboMeals(2,2,"Monday" , 2);
    private final ComboMeals combo22 = new ComboMeals(22,2,"Monday", 1);

    private final Logger log = LogManager.getLogger();

    @Test
    public void writeAndUpdateOrderTest(){
        log.debug("-----writeAndUpdateOrderTest--------------");
        dp.saveOrder(order0);
        assertEquals(1, dp.getAllOrders().size());
        dp.saveOrder(order1);
        assertEquals(order0, dp.getOrderById(0));
        assertEquals(order1, dp.getOrderById(1));
        //assertEquals(order1, dp.getOrderById(0)); //негативный
        assertEquals(2, dp.getAllOrders().size());
        dp.saveOrder(order11);
        assertEquals(order11, dp.getOrderById(1));//update
        assertEquals(2, dp.getAllOrders().size());
    }

    @Test
    public void deleteOrderTest(){
        log.debug("-----deleteOrderTest--------------");
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
        log.debug("-----writeAndUpdateStaffTest--------------");
        dp.saveStaff(staff0);
        assertEquals(1, dp.getAllStaff().size());
        dp.saveStaff(staff1);
        assertEquals(staff0, dp.getStaffById(0));
        assertEquals(staff1, dp.getStaffById(1));
        assertEquals(2, dp.getAllStaff().size());
        dp.saveStaff(staff11);
        assertEquals(staff11, dp.getStaffById(1));
        assertEquals(2, dp.getAllStaff().size());
    }

    @Test
    public void deleteStaffTest(){
        log.debug("-----deleteStaffTest--------------");
        dp.saveStaff(staff0);
        dp.saveStaff(staff1);
        assertEquals(2, dp.getAllStaff().size());
        dp.deleteStaff(0);
        assertEquals(1, dp.getAllStaff().size());
        dp.deleteStaff(3);
        assertEquals(1, dp.getAllStaff().size());
    }

    @Test
    public void writeAndUpdatePuipleTest(){
        log.debug("-----writeAndUpdatePuipleTest--------------");
        dp.savePuiple(puiple0);
        assertEquals(1, dp.getAllPuiple().size());
        dp.savePuiple(puiple1);
        assertEquals(puiple0, dp.getPuipleById(0));
        assertEquals(puiple1, dp.getPuipleById(1));
        assertEquals(2, dp.getAllPuiple().size());
        dp.savePuiple(puiple11);
        assertEquals(puiple11, dp.getPuipleById(1));
        assertEquals(2, dp.getAllPuiple().size());
    }

    @Test
    public void deletePuipleTest(){
        log.debug("-----deletePuipleTest--------------");
        dp.savePuiple(puiple0);
        dp.savePuiple(puiple1);
        assertEquals(2, dp.getAllPuiple().size());
        dp.deletePuiple(0);
        assertEquals(1, dp.getAllPuiple().size());
        dp.deletePuiple(3);
        assertEquals(1, dp.getAllPuiple().size());
    }

    @Test
    public void writeAndUpdateFoodCategoryTest(){
        log.debug("-----writeAndUpdateFoodCategoryTest--------------");
        dp.saveFoodCategory(foodCategory0);
        assertEquals(1, dp.getAllFoodCategory().size());
        dp.saveFoodCategory(foodCategory1);
        assertEquals(foodCategory0, dp.getFoodCategoryById(0));
        assertEquals(foodCategory1, dp.getFoodCategoryById(1));
        assertEquals(2, dp.getAllFoodCategory().size());
        dp.saveFoodCategory(foodCategory00);//update
        assertEquals(foodCategory00, dp.getFoodCategoryById(0));
        assertEquals(2, dp.getAllFoodCategory().size());
    }

    @Test
    public void deleteFoodCategoryTest(){
     log.debug("-----deleteFoodCategoryTest--------------");
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        assertEquals(2, dp.getAllFoodCategory().size());
        dp.deleteFoodCategory(0);
        assertEquals(1, dp.getAllFoodCategory().size());//invalid
        dp.deleteFoodCategory(3);
        assertEquals(1, dp.getAllFoodCategory().size());
    }
    @Test
    public void writeAndUpdateFoodItemTest(){
        log.debug("-----writeAndUpdateFoodItemTest--------------");
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        dp.saveFoodItem(foodItem1);
        assertEquals(1, dp.getAllFoodItems().size());
        dp.saveFoodItem(foodItem2);
        assertEquals(foodItem1, dp.getFoodItemById(1));
        assertEquals(foodItem2, dp.getFoodItemById(2));
        assertEquals(2, dp.getAllFoodItems().size());
        dp.saveFoodItem(foodItem22);
        assertEquals(foodItem1, dp.getFoodItemById(1));
        assertEquals(2, dp.getAllFoodItems().size());
        dp.saveFoodItem(foodItem22); //invalid;
    }
    @Test
    public void deleteFoodItemTest(){
        log.debug("-----deleteFoodItemTest--------------");
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        dp.saveFoodItem(foodItem1);
        dp.saveFoodItem(foodItem2);
        assertEquals(2, dp.getAllFoodItems().size());
        dp.deleteFoodItem(1);
        assertEquals(1, dp.getAllFoodItems().size()); //invalid
        dp.deleteFoodItem(3);
        assertEquals(1, dp.getAllFoodItems().size());
    }
    @Test
    public void invalidDeleteFoodItemTest(){
        log.debug("-----invalidDeleteFoodItemTest--------------");
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        dp.saveFoodItem(foodItem1);
        dp.saveComboMeals(combo1);
        assertEquals(1, dp.getAllFoodItems().size());
        dp.deleteFoodItem(1);
        assertEquals(1, dp.getAllFoodItems().size());

    }
    @Test
    public void writeAndUpdateComboMeals(){
        log.debug("-----writeAndUpdateComboMeals--------------");
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        dp.saveFoodItem(foodItem1);
        dp.saveFoodItem(foodItem2);
        dp.saveComboMeals(combo1);
        assertEquals(1, dp.getAllComboMeals().size());
        dp.saveComboMeals(combo2);
        assertEquals(combo1, dp.getComboMealsById(1));
        assertEquals(combo2, dp.getComboMealsById(2));
        assertEquals(2, dp.getAllComboMeals().size());
        dp.saveComboMeals(combo22);
        assertEquals(combo22, dp.getComboMealsById(22));
        assertEquals(3, dp.getAllComboMeals().size());
        dp.saveComboMeals(combo0); //invalid
        assertEquals(3, dp.getAllComboMeals().size());
    }

    @Test
    public void deleteComboMeals(){
        log.debug("-----deleteComboMeals--------------");
        dp.saveFoodCategory(foodCategory0);
        dp.saveFoodCategory(foodCategory1);
        dp.saveFoodItem(foodItem1);
        dp.saveFoodItem(foodItem2);
        dp.saveComboMeals(combo1);
        dp.saveComboMeals(combo2);
        assertEquals(2, dp.getAllComboMeals().size());
        dp.deleteComboMeals(1);
        assertEquals(1, dp.getAllComboMeals().size());
        dp.deleteComboMeals(3);
        assertEquals(1, dp.getAllComboMeals().size());
    }
}
