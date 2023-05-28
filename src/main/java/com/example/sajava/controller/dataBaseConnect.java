package com.example.sajava.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class dataBaseConnect {
    public Connection connect_to_db(){
        String url = "jdbc:postgresql://localhost:5432/your_database"; //your_database是自己的資料庫名稱
        String username = "your_username";//預設是postgres
        String password = "your_password";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);

            if(connection != null)
            {
                System.out.println("success");
            }
            else
            {
                System.out.println("full");
            }

            connection.close(); // 最後別忘了關閉連接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
