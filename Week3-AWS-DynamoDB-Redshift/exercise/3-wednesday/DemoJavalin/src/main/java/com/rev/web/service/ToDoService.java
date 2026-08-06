package com.rev.web.service;

import com.rev.web.DAO.ToDoDAO;
import com.rev.web.model.ToDo;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoService {
    private ToDoDAO dao;

    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public ToDoService(ToDoDAO dao) {
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public ToDo get(int id) {
        String sql = "SELECT * FROM todos WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching todo with id " + id, e);
        }
    }

    public List<ToDo> getAll() {
        String sql = "SELECT * FROM todos";
        List<ToDo> todos = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                todos.add(mapRow(rs));
            }
            return todos;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all todos", e);
        }
    }

    public ToDo insert(ToDo task) {
        String sql = "INSERT INTO todos (title, complete) VALUES (?, ?) RETURNING id";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.getTitle());
            ps.setBoolean(2, task.isComplete());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int newId = rs.getInt("id");
                return new ToDo(newId, task.getTitle(), task.isComplete());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting todo", e);
        }
    }

    public ToDo update(ToDo task) {
        String sql = "UPDATE todos SET title = ?, complete = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.getTitle());
            ps.setBoolean(2, task.isComplete());
            ps.setInt(3, task.getId());
            ps.executeUpdate();
            return task;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating todo with id " + task.getId(), e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting todo with id " + id, e);
        }
    }

    private ToDo mapRow(ResultSet rs) throws SQLException {
        return new ToDo(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getBoolean("complete")
        );
    }
}