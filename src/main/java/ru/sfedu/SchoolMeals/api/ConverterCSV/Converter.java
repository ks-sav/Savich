package ru.sfedu.SchoolMeals.api.ConverterCSV;

public interface Converter<T> {
    String[] toCsv(T t);
    T fromCsv(String [] csvData);
}
