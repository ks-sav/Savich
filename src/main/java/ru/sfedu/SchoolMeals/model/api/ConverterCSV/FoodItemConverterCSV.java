package ru.sfedu.SchoolMeals.model.api.ConverterCSV;

import ru.sfedu.SchoolMeals.model.bean.FoodItem;

public class FoodItemConverterCSV implements Converter<FoodItem> {
    @Override
    public String[] toCsv(FoodItem foodItem) {
        return new String[]{
                String.valueOf(foodItem.getId()),
                String.valueOf(foodItem.getItemName_()),
                String.valueOf(foodItem.getPrice()),
                String.valueOf(foodItem.getDescription()),
                String.valueOf(foodItem.getCategory()),
                String.valueOf(foodItem.getInStock())
        };
    }

    @Override
    public FoodItem fromCsv(String[] csvData) {
        return new FoodItem(
                Long.parseLong(csvData[0]),
                csvData[1],
                Double.parseDouble(csvData[2]),
                csvData[3],
                Long.parseLong(csvData[4]),
                Boolean.valueOf(csvData[4])
        );
    }
}
