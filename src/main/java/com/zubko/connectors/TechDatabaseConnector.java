package com.zubko.connectors;

import com.zubko.config.Config;
import com.zubko.models.Tech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechDatabaseConnector {
    private static TechDatabaseConnector instance;
    final Logger logger = LoggerFactory.getLogger(this.getClass().getName());// Combat.class

    public static TechDatabaseConnector getInstance() {
        if (instance == null) {
            instance = new TechDatabaseConnector();
        }
        return instance;
    }

    private TechDatabaseConnector() {
        try {
            createNewTable();
        } catch (Exception e) {
            throw new RuntimeException("Failed createNewTable connection");
        }
    }

    private void createNewTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS tech ("
                + "	id INTEGER PRIMARY KEY  autoincrement,"
                + "	type VARCHAR(20) NOT NULL,"
                + "	name VARCHAR(20) NOT NULL,"
                + "	model VARCHAR(20) NOT NULL,"
                + "	date VARCHAR(20) NOT NULL,"
                + "	userId INTEGER(100)"
                + ");";

        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            logger.info("table is created");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insert(Tech tech) {
        String sql = "INSERT INTO tech(type,name, model, date) VALUES(?,?,?,?);";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tech.getType());
            pstmt.setString(2, tech.getName());
            pstmt.setString(3, tech.getModel());
            pstmt.setString(4, tech.getDate());
            pstmt.execute();
            logger.info("inserted  tech {}", tech);
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tech WHERE id = ?;";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.execute();
            logger.info("inserted  tech id {}", id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }
    public void update(Tech tech) {
        String sql = "UPDATE tech set type = ?, name =?, model = ?, date = ?, userId = ? WHERE id = ?";
        int id = Integer.valueOf(tech.getId());
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tech.getType());
            pstmt.setString(2, tech.getName());
            pstmt.setString(3, tech.getModel());
            pstmt.setString(4, tech.getDate());
            pstmt.setInt(5, tech.getUserId());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            logger.info("updated  tech  {}", tech);
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
        System.out.println("User update = " + tech);

    }

    public List<Tech> findBy(String param, String value) {
        String sql = "SELECT * FROM tech WHERE " + param + "='" + value + "';";
        List<Tech> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                array.add(new Tech(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getInt("userId")));
            }
            logger.info("findBy  tech by {} = {}, response {}", param,value, array);
            return array;
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection");
        }
    }
    public List<Tech> findBy(String param, int value) {
        String sql = "SELECT * FROM tech WHERE " + param + "='" + value + "';";
        List<Tech> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                array.add(new Tech(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getInt("userId")));
            }
            logger.info("findBy  tech by {} = {}, response {}", param,value, array);
            return array;
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection");
        }
    }

    public Tech findById(int value) {
        String sql = "SELECT * FROM tech WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Tech temp = new Tech(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getInt("userId"));
                logger.info("findBy  tech by id = {}, response {}", value, temp);
                return temp;
            } else {
                throw new RuntimeException("not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e.getMessage());
        }
    }

    public List<Tech> getAll() {
        String sql = "SELECT * FROM tech;";
        List<Tech> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                array.add(new Tech(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getInt("userId")));
            }
            logger.info("getAll , response {}", array);
            return array;
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }
    public List<Tech> getAllSentToUser() {
        String sql = "SELECT * FROM tech WHERE userId > 0;";
        List<Tech> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                array.add(new Tech(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getInt("userId")));
            }
            logger.info("getAllSentToUser , response {}", array);
            return array;
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }
}
