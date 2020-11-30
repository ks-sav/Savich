package ru.sfedu.SchoolMeals.model.api.WrapperXML;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.SchoolMeals.model.bean.FoodCategory;
import java.util.List;

@Root
public class FoodCategoryList {
    @ElementList
    public List<FoodCategory> foodCategory;

    public FoodCategoryList(List<FoodCategory> foodCategory) {
        this.foodCategory = foodCategory;
    }

    public FoodCategoryList() {
    }
}
