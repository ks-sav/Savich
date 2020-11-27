package ru.sfedu.SchoolMeals.model.api.ConverterCSV;

import ru.sfedu.SchoolMeals.model.bean.ComboMeals;
import ru.sfedu.SchoolMeals.model.bean.FoodCategory;

public class FoodCategoryConverterCSV implements Converter<FoodCategory> {
    @Override
    public String[] toCsv(FoodCategory foodCategory) {
        return new String[]{
                String.valueOf(foodCategory.getId()),
                foodCategory.getCategoryName()};
    }

    @Override
    public FoodCategory fromCsv(String[] csvData) {

        return new FoodCategory(
                Long.parseLong(csvData[0]),
                csvData[1]);
    }
}
