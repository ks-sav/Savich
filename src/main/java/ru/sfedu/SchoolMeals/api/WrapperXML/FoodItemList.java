package ru.sfedu.SchoolMeals.api.WrapperXML;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.SchoolMeals.model.FoodItem;

import java.util.List;

@Root
public class FoodItemList {
    @ElementList
    public List<FoodItem> foodItem;

    public FoodItemList(List<FoodItem> foodItem) {
        this.foodItem = foodItem;
    }

    public FoodItemList() {
    }
}
