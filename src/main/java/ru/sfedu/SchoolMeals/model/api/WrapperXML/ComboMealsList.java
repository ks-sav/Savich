package ru.sfedu.SchoolMeals.model.api.WrapperXML;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.SchoolMeals.model.bean.ComboMeals;;

import java.util.List;

@Root
public class ComboMealsList {
    @ElementList
    public List<ComboMeals> comboMeals;

    public ComboMealsList(List<ComboMeals> comboMeals) {
        this.comboMeals = comboMeals;
    }

    public ComboMealsList() {
    }
}
