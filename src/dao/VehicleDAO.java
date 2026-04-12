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

        PreparedStatement stmt;
        String sql = "INSERT INTO vehicles (plate, brand, model, year, daily_rate) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = ConnectionFactory.getConnection()) {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicle.getPlate());
            stmt.setString(2, vehicle.getBrand());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setDouble(5, vehicle.getDailyRate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteVehicle(Long id) {

        PreparedStatement stmt;
        String sql = "DELETE FROM vehicles WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String listAllVehicles() {

        PreparedStatement stmt;
        String sql = "SELECT * FROM vehicles";
        String rows;
        String table = "";

        try (Connection conn = ConnectionFactory.getConnection()) {

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
            System.out.println(e.getMessage());
        }

        return table;
    }

    public String listEnabledVehicles() {

        PreparedStatement stmt;
        String sql = "SELECT * FROM vehicles WHERE available = true";
        String rows;
        String table = "";

        try (Connection conn = ConnectionFactory.getConnection()) {

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
            System.out.println(e.getMessage());
        }

        return table;
    }
 }
