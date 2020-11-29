package ru.sfedu.SchoolMeals.model.api.WrapperXML;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

;

@Root
public class OrderList {
    @ElementList
    public List<OrderList> order;

    public OrderList(List<OrderList> order) {
        this.order = order;
    }

    public OrderList() {
    }
}
