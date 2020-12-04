package ru.sfedu.SchoolMeals.model.api.WrapperXML;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.SchoolMeals.model.bean.Order;

import java.util.List;

@Root
public class OrderList {
    @ElementList
    public List<Order> order;

    public OrderList(List<Order> order) {
        this.order = order;
    }

    public OrderList() {
    }
}
