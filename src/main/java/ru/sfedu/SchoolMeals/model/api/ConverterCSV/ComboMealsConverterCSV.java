package ru.sfedu.SchoolMeals.model.api.ConverterCSV;

import ru.sfedu.SchoolMeals.model.bean.ComboMeals;
import ru.sfedu.SchoolMeals.model.bean.FoodItem;

import java.sql.Timestamp;
import java.util.List;

public class ComboMealsConverterCSV implements Converter<ComboMeals> {
    @Override
    public String[] toCsv(ComboMeals comboMeals) {
        return new String[]{
                String.valueOf(comboMeals.getIdCombo()),
                String.valueOf(comboMeals.getDate()),
                String.valueOf(comboMeals.getMenu()),
                String.valueOf(comboMeals.getPrice())
        };
    }

    @Override
    public ComboMeals fromCsv(String[] csvData) {
        "test";
        return null;
    }
    /*
//решить, как распарсить лист в бин (еще один конвертер?)
    @Override
    public ComboMeals fromCsv(String[] csvData) {
        return new ComboMeals(
                Long.parseLong(csvData[0]),
                Timestamp.valueOf(csvData[1]),
                List.(csvData[2]), //тут List<FoodItem>
                Double.valueOf(csvData[3])
        );
    }*/
}
