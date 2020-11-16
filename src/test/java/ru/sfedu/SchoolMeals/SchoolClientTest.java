package ru.sfedu.SchoolMeals;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class SchoolClientTest {

    @Test
    void logBasicSystemInfo() throws IOException {
        //Здесь	происходит	инициализация	класса	и	вызов	его	метода.
        SchoolClient client = new SchoolClient();
        client.logBasicSystemInfo();
    }
}