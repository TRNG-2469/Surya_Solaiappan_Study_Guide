package exercise;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecureAuthGateway {

    public boolean authenticateUser(String emailInput, String passwordInput, Connection conn) throws SQLException {

        String query = "SELECT * FROM members WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, emailInput);
            stmt.setString(2, passwordInput);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            SecureAuthGateway gateway = new SecureAuthGateway();
            boolean authenticated = gateway.authenticateUser("alice@example.com", "password", conn);
            System.out.println("Authenticated: " + authenticated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}