package ru.sfedu.SchoolMeals;

public class Constants {
    static public final String ENV_CONST="source";
    static public final Integer INT_CONST=88937;
    static public final String SELECT="select * from \"";
    static public final String DELETE="delete from \"";
    static public final String INSERT="insert into ";
    static public final String END="\"";
    static public final String INS_FOODCATEGORY=INSERT+"\"FoodCategory\" (id,categoryName) values(?, ?)";
    static public final String INS_COMBOMEALS=INSERT+"\"ComboMeals\" (id,comboId,name,foodId) values(?, ?,?,?)";
    static public final String INS_FOODITEM=INSERT+"\"FoodItem\" (id,itemName,price,description,category_id,inStock) values(?, ?,?,?,?,?)";
    static public final String INS_ORDER=INSERT+"\"Order\" (id,customerId,date,status,totalCost) values(?, ?, ?, ?, ?)";
    static public final String INS_STAFF=INSERT+"\"Staff\" (id,name,customerType,unionMember) values(?, ?, ?,?)";
    static public final String INS_PUIPLE=INSERT+"\"Puiple\" (id,name,customerType, nClass,freeMeals) values(?, ?, ?, ?, ?)";
}
