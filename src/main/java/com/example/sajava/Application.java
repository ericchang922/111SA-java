package com.example.sajava;

import com.example.sajava.controller.dataBaseConnect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //dataBaseConnect dbc = new dataBaseConnect();
        //dbc.connect_to_db();
    }

}
