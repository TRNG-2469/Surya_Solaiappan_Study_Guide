package com.rev.jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class JDBCCreate {
    public static void main(String[] args) {

        try {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("DB_URL");
            Connection connection = DriverManager.getConnection(
                    url,
                    dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));

            PreparedStatement statement = connection.prepareStatement("INSERT INTO employees VALUES (?, ?, ?) RETURNING *");
            statement.setInt(1, 12);
            statement.setString(2, "Mohn");
            statement.setInt(3, 99);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                System.out.println(resultset.getInt("emp_id") + " " + resultset.getString("name") + " " + resultset.getInt("salary"));
            }
            System.out.println("# of rows affected:"+resultset);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}