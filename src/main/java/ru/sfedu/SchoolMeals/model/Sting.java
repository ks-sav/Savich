package ru.sfedu.SchoolMeals.model;

import java.sql.Timestamp;

public class Sting {
    private final Timestamp date;

    public Sting(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }
}
