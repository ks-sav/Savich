package ru.sfedu.SchoolMeals.api.ConverterCSV;

import ru.sfedu.SchoolMeals.model.FoodCategory;

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
