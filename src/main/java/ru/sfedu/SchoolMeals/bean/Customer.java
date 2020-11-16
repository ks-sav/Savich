package ru.sfedu.SchoolMeals.bean;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.Objects;
/**
 * Class Customer
 */

public class Customer implements Serializable {

    @CsvBindByName
    private long id;

    @CsvBindByName
    private String name;

    public Customer() {
    }
//
    // Methods
    //


    //
    // Accessor methods
    //
    /**
     * Get the value of id
     * @return the value of id
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer user = (Customer) o;
        return id == user.id &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
// int hash = 3;
// return hash;
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}