package ru.sfedu.SchoolMeals;

import ru.sfedu.SchoolMeals.model.bean.Customer;

public class TestBase {

    public Customer createCustomer(long id, String name) {
        Customer user = new Customer();
        user.setId(id);
        user.setName(name);
        return user;
    }
}
