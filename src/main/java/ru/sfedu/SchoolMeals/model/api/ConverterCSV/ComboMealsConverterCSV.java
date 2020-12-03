package ru.sfedu.SchoolMeals.model.api.ConverterCSV;

import ru.sfedu.SchoolMeals.model.bean.ComboMeals;
import ru.sfedu.SchoolMeals.model.bean.FoodItem;

import java.sql.Timestamp;
import java.util.List;

public class ComboMealsConverterCSV implements Converter<ComboMeals> {
    @Override
    public String[] toCsv(ComboMeals comboMeals) {
        return new String[]{
                String.valueOf(comboMeals.getId()),
                String.valueOf(comboMeals.getComboId()),
                String.valueOf(comboMeals.getName()),
                String.valueOf(comboMeals.getFoodId())
        };
    }

    @Override
    public ComboMeals fromCsv(String[] csvData) {
        return new ComboMeals(
                Long.parseLong(csvData[0]),
                Long.parseLong(csvData[1]),
                csvData[2],
                Long.parseLong(csvData[3])
        );
    }

}
