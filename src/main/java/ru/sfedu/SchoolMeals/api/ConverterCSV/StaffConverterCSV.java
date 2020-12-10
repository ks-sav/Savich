package ru.sfedu.SchoolMeals.api.ConverterCSV;
import ru.sfedu.SchoolMeals.model.CustomerType;
import ru.sfedu.SchoolMeals.model.Staff;

public class StaffConverterCSV implements Converter<Staff> {
    @Override
    public String[] toCsv(Staff staff) {
        return new String[]{
                String.valueOf(staff.getId()),
                String.valueOf(staff.getName()),
                String.valueOf(staff.getCustomerType()),
                String.valueOf(staff.getUnionMember()),
        };
    }

    @Override
    public Staff fromCsv(String[] csvData) {
        return new Staff(
                Long.parseLong(csvData[0]),
                csvData[1],
                CustomerType.valueOf(csvData[2]),
                Boolean.valueOf(csvData[3])
        );
    }
}
