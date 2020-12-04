package ru.sfedu.SchoolMeals.model.bean;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.Objects;
/**
 * Class Customer
 */
@Root
public class Customer implements Serializable, WithId {
    @Attribute
    @CsvBindByName
    private long id;

    @Attribute
    @CsvBindByName
    private String name;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

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
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + '}';
    }

}