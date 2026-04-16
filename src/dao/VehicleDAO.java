package dao;

import connection.ConnectionFactory;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public void insertVehicle(Vehicle vehicle) {

        String sql = "INSERT INTO vehicles (plate, brand, model, year, daily_rate) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicle.getPlate());
            stmt.setString(2, vehicle.getBrand());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setDouble(5, vehicle.getDailyRate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting vehicle", e);
        }
    }

    public void deleteVehicle(Long id) {

        String sql = "DELETE FROM vehicles WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error listing vehicle", e);
        }
    }

    public String listAllVehicles() {

        String sql = "SELECT * FROM vehicles";
        String rows;
        String table = "";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                String plate = result.getString("plate");
                String brand = result.getString("brand");
                String model = result.getString("model");
                int year = result.getInt("year");
                double dailyRate = result.getDouble("daily_rate");
                boolean available = result.getBoolean("available");

                rows = id + " | " + plate + " | " + brand + " | " +
                        model + " | " + year + " | " + dailyRate +  " | " +
                        available + "\n";
                table += rows;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listing vehicles", e);
        }

        return table;
    }

    public String listEnabledVehicles() {

        String sql = "SELECT * FROM vehicles WHERE available = true";
        String rows;
        String table = "";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                String plate = result.getString("plate");
                String brand = result.getString("brand");
                String model = result.getString("model");
                int year = result.getInt("year");
                double dailyRate = result.getDouble("daily_rate");

                rows = id + " | " + plate + " | " + brand + " | " +
                        model + " | " + year + " | " + dailyRate +  " | " + "\n";
                table += rows;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing vehicles", e);
        }

        return table;
    }

    public Vehicle findById(Long id) {

        Vehicle vehicle = null;
        String sql = "SELECT * FROM vehicles WHERE id = ?;";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            vehicle = new Vehicle(rs.getString("plate"), rs.getString("brand"),
                                    rs.getString("model"), rs.getInt("year"),
                                    rs.getDouble("daily_rate"));
            vehicle.setId(rs.getLong("id"));

        } catch (SQLException e) {
            throw new RuntimeException("This vehicle is unexist", e);
        }

        return vehicle;
    }
 }
