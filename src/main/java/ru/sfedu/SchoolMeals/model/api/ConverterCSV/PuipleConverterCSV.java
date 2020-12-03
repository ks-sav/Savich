package ru.sfedu.SchoolMeals.model.api.ConverterCSV;
import ru.sfedu.SchoolMeals.model.bean.Puiple;

public class PuipleConverterCSV implements Converter<Puiple> {
    @Override
    public String[] toCsv(Puiple puiple) {
        return new String[]{
                String.valueOf(puiple.getId()),
                String.valueOf(puiple.getName()),
                String.valueOf(puiple.getNClass()),
                String.valueOf(puiple.getFreeMeals())
        };
    }

    @Override
    public Puiple fromCsv(String[] csvData) {
        return new Puiple(
                Long.parseLong(csvData[0]),
                csvData[1],
                Long.parseLong(csvData[2]),
                Boolean.valueOf(csvData[3])
        );
    }
}
