package ru.sfedu.SchoolMeals.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sfedu.SchoolMeals.model.ComboMeals;
import ru.sfedu.SchoolMeals.model.FoodItem;
import ru.sfedu.SchoolMeals.model.Order;
import ru.sfedu.SchoolMeals.model.OrderStatus;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

class IDataProviderTest {

    IDataProvider dataProvider = new DataProviderCSV();
    //Timestamp date = new Timestamp(System.currentTimeMillis());
    String dateString = "2020-05-10 20:34:09.0";
    Timestamp date = dataProvider.StringToTimestamp(dateString);
    long[] itemsId = {2, 1, 3, 5};
    Order order = dataProvider.createOrder(4, dateString, itemsId,false);
    Order order1 = dataProvider.createOrder(1, "2020-05-10 20:34:09.0", itemsId,false);
    Order order2 = dataProvider.createOrder(2, "2020-06-10 20:34:09.0", itemsId,false);
    Order order3 = dataProvider.createOrder(100, "2020-07-10 20:34:09.0", itemsId,false);
    Order order4 = dataProvider.createOrder(12, "2020-08-10 20:34:09.0", itemsId,false);
    Order order5 = dataProvider.createOrder(22, "2020-09-10 20:34:09.0", itemsId,false);
    Order order6 = dataProvider.createOrder(99, "2020-12-10 20:34:09.0", itemsId,false);
    Order order7 = dataProvider.createOrder(45, dateString, itemsId, false);
    Order order8 = dataProvider.createOrder(54, dateString, itemsId, false);
    Order order9 = dataProvider.createOrder(77, "2020-12-26 10:34:09.0", itemsId,false);


    IDataProviderTest() throws ParseException, IOException {
    }

    @Test
    void createOrder() throws IOException, ParseException {

        Assert.assertEquals(45, order7.getId());
        Assert.assertFalse(order8.getCombo());
    }

    @Test
    void pickMeals() throws IOException {
        long[] itemsId = {2, 1, 3, 5};
        List<FoodItem> meals = dataProvider.pickMeals(itemsId);
        Assert.assertEquals(2, meals.get(0).getId());
        Assert.assertEquals(1, meals.get(1).getId());
        Assert.assertEquals(3, meals.get(2).getId());
        Assert.assertEquals(5, meals.get(3).getId());
    }

    @Test
    void selectCombo() throws IOException {
        long[] itemsId = {2, 1, 3, 5};
        ComboMeals combo1 = new ComboMeals(1, 1, "obed", 3);
        ComboMeals combo2 = new ComboMeals(2, 2, "savtrak", 3);
        combo1  = dataProvider.editCombo(combo1, itemsId);
        dataProvider.saveComboMeals(combo1);
        dataProvider.saveComboMeals(combo2);
        long[] itemsInCombo = combo1.getItemsId();
        Assert.assertEquals(2, itemsInCombo[0]);
        Assert.assertEquals(1, itemsInCombo[1]);
        Assert.assertEquals(3, itemsInCombo[2]);
        Assert.assertEquals(5, itemsInCombo[3]);
    }

    @Test
    void makeChangesToOrder() throws IOException, ParseException {
        long[] itemsId2 = {10, 5, 6, 7};
        Assert.assertEquals(4, order.getMeals().size());
        order = dataProvider.makeChangesToOrder(order, itemsId2, true);
        Assert.assertEquals(8, order.getMeals().size());
        order = dataProvider.makeChangesToOrder(order, itemsId2, false);
        Assert.assertEquals(4, order.getMeals().size());
    }

    @Test
    void cancelOrder() throws IOException, ParseException {
        Order order2 = dataProvider.getOrderById(order1.getId());
        Assert.assertEquals(order2.getId(), order1.getId());
        dataProvider.deleteOrder(order1.getId());
        order2 = dataProvider.getOrderById(order1.getId());
        Assert.assertNull(order2);
    }

    @Test
    void viewOrderHistory() throws IOException, ParseException {//Id is unique
        StringBuffer buf = dataProvider.viewOrderHistory(4);
        Assert.assertEquals(order.toString(), buf.toString());
    }

    @Test
    void editCombo() throws IOException {
        long[] itemsId = {10, 5, 6, 7};
        ComboMeals combo = dataProvider.getComboMealsById(1);
        combo = dataProvider.editCombo(combo, itemsId);
        Assert.assertEquals(10, combo.getItemsId()[0]);
        Assert.assertEquals(5, combo.getItemsId()[1]);
        Assert.assertEquals(6, combo.getItemsId()[2]);
        Assert.assertEquals(7, combo.getItemsId()[3]);
    }

    @Test
    void createReport() throws IOException, ParseException {

        String dataStart = order1.getDate();
        String dataEnd = order6.getDate();
        StringBuffer buf = dataProvider.createReport(dataStart, dataEnd, OrderStatus.PRE);
        String bufTest = order2.toString() + '\n' +
                order3.toString() + '\n' +
                order4.toString() + '\n' +
                order5.toString() + '\n';
        Assert.assertEquals(bufTest, buf.toString());
    }

    @Test
    void approvOrder() throws IOException, ParseException {
        Order order10 = dataProvider.createOrder(88, "2020-12-26 08:34:09.0", itemsId,false);
        Assert.assertEquals(OrderStatus.PRE,order10.getStatus());
        dataProvider.approvOrder(order10);
        Assert.assertEquals(OrderStatus.APPROV,order10.getStatus());
    }

    @Test
    void calculateTotalCost() throws IOException, ParseException {
        long[] itemsId = {11};//this elements costs 100
        Order order10 = dataProvider.createOrder(88, "2020-12-26 15:34:09.0", itemsId,false);
        Assert.assertEquals(100,(int)dataProvider.calculateTotalCost(order10));
    }
}