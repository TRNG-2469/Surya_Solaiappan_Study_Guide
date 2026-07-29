package com.rev.jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class JDBCRead {
    public static void main(String[] args) {

        try {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("DB_URL");
            Connection connection = DriverManager.getConnection(
                    url,
                    dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees ORDER BY emp_id ASC");
            ResultSet resultset = statement.executeQuery();
            while(resultset.next()){
                    System.out.println(resultset.getInt("emp_id")+ " "+ resultset.getString("name")+ " "+ resultset.getDouble("salary"));
               }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}