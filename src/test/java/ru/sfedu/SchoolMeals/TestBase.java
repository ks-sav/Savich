package ru.sfedu.SchoolMeals;

import ru.sfedu.SchoolMeals.bean.User;

public class TestBase {

    public User createUser(long id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }
}
