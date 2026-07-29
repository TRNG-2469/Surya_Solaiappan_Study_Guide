package com.rev.jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class JDBCUpdate {
    public static void main(String[] args) {

        try {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("DB_URL");
            Connection connection = DriverManager.getConnection(
                    url,
                    dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));

            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET emp_id = 8 WHERE emp_id = 6 RETURNING *");
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                System.out.println(resultset.getInt("emp_id") + " " + resultset.getString("name") + " " + resultset.getDouble("salary"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}