package ru.sfedu.SchoolMeals.api.ConverterCSV;

import ru.sfedu.SchoolMeals.model.Order;
import ru.sfedu.SchoolMeals.model.OrderStatus;

import java.sql.Timestamp;

public class OrderConverterCSV implements Converter<Order> {
    @Override
    public String[] toCsv(Order order) {
        return new String[]{
                String.valueOf(order.getId()),
                String.valueOf(order.getPupilId()),
                String.valueOf(order.getDate()),
                String.valueOf(order.getStatus()),
                String.valueOf(order.getTotalCost()),
        };
    }

    @Override
    public Order fromCsv(String[] csvData) {
        return new Order(
                Long.parseLong(csvData[0]),
                Long.parseLong(csvData[1]),
                Timestamp.valueOf(csvData[2]),
                OrderStatus.valueOf(csvData[3]),
                Double.parseDouble(csvData[4])
        );
    }
}
