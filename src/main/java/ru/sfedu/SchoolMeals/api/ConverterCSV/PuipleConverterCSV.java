package ru.sfedu.SchoolMeals.api.ConverterCSV;
import ru.sfedu.SchoolMeals.model.CustomerType;
import ru.sfedu.SchoolMeals.model.Puiple;

public class PuipleConverterCSV implements Converter<Puiple> {
    @Override
    public String[] toCsv(Puiple puiple) {
        return new String[]{
                String.valueOf(puiple.getId()),
                String.valueOf(puiple.getName()),
                String.valueOf(puiple.getCustomerType()),
                String.valueOf(puiple.getNClass()),
                String.valueOf(puiple.getFreeMeals())
        };
    }

    @Override
    public Puiple fromCsv(String[] csvData) {
        return new Puiple(
                Long.parseLong(csvData[0]),
                csvData[1],
                CustomerType.valueOf(csvData[2]),
                Long.parseLong(csvData[3]),
                Boolean.valueOf(csvData[4])
        );
    }
}
