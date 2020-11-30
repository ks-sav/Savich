package ru.sfedu.SchoolMeals.model.api.WrapperXML;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.SchoolMeals.model.bean.Puiple;

import java.util.List;

@Root
public class PuipleList {
    @ElementList
    public List<Puiple> puiple;

    public PuipleList(List<Puiple> puiple) {
        this.puiple = puiple;
    }

    public PuipleList() {
    }
}
