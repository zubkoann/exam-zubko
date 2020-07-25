package com.zubko.connectors;

import com.zubko.config.Config;
import com.zubko.exceptions.UserNotFoundException;
import com.zubko.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabaseConnector {
    private static UserDatabaseConnector instance;
    final Logger logger = LoggerFactory.getLogger(this.getClass().getName());// Combat.class

    public static UserDatabaseConnector getInstance() {
        if (instance == null) {
            instance = new UserDatabaseConnector();
        }
        return instance;
    }

    private UserDatabaseConnector() {
        try {
            createNewTable();
        } catch (Exception e) {
            throw new RuntimeException("Failed createNewTable connection");
        }
    }

    private void createNewTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "	id INTEGER PRIMARY KEY  autoincrement,"
                + "	surname VARCHAR(20) NOT NULL,"
                + "	name VARCHAR(20) NOT NULL,"
                + "	email VARCHAR(20) NOT NULL,"
                + "	role VARCHAR(20) NOT NULL,"
                + "	team VARCHAR(20)"
                + ");";

        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            logger.info("table is created");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insert(User user) {
        String sql = "INSERT INTO users(surname,name, email, role, team) VALUES(?,?,?,?,?);";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getSurname());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getTeam());
            pstmt.execute();
            logger.info("inserted   {}", user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?;";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.execute();
            logger.info("delete  {}", id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
        System.out.println("User deleted = " + id);
    }

    public void update(User user) {
        String sql = "UPDATE users set userName = ?, name =?, email = ?, password = ?, role = ? WHERE id = ?";
        int id = Integer.valueOf(user.getId());
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getSurname());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getTeam());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            logger.info("update  {}", user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
        System.out.println("User update = " + user);

    }

    public User findBy(String param, String value) {
        String sql = "SELECT * FROM users WHERE " + param + "='" + value + "';";
        List<User> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User temp = new User(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("team"));
                logger.info("findBy {} = {} , response {}", param, value, temp);
                return temp;
            } else {
                throw new UserNotFoundException(param + " with " + value + " NOT FOUND");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection");
        }
    }

    public User findById(int value) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User temp = new User(rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("team"));
                logger.info("findById = {} , response {}", value, temp);
                return temp;
            } else {
                throw new RuntimeException("not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e.getMessage());
        }
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users;";
        List<User> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                array.add(new User(rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("team")));
            }
            logger.info("getAll , response {}", array);
            return array;
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }
}
