package dao;

import connection.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;

public class RentDAO {

    public void rentVehicle(Long id, String clientName, LocalDate startDate) {

        PreparedStatement stmt;

        try (Connection conn = ConnectionFactory.getConnection()) {

            stmt = conn.prepareStatement("UPDATE vehicles SET available = false WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("INSERT INTO rent (vehicle_id, client_name, start_date) VALUES (?, ?, ?)");
            stmt.setLong(1, id);
            stmt.setString(2, clientName);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnVehicle(Long id) {

        PreparedStatement stmt;

        try (Connection conn = ConnectionFactory.getConnection()) {

            stmt = conn.prepareStatement("UPDATE vehicles SET available = true WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE rent SET finished = true WHERE vehicle_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE rent SET end_date = ? WHERE vehicle_id = ?");
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setLong(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String listAllRents() {

        PreparedStatement stmt;
        String sql = "SELECT * FROM rent";
        String rows;
        String table = "";

        try (Connection conn = ConnectionFactory.getConnection()) {

            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                Long vehicleId = result.getLong("vehicle_id");
                String clientName = result.getString("client_name");
                LocalDate startDate = result.getDate("start_date").toLocalDate();

                Date sqlDate = result.getDate("end_date");
                String endDate;

                if (sqlDate != null) {
                    endDate = (sqlDate.toLocalDate()).toString();
                } else {
                    endDate = "The rent isn't finished";
                }

                boolean finished = result.getBoolean("finished");

                rows = id + " | " + vehicleId + " | " + clientName + " | " +
                        startDate + " | " + endDate + " | " + finished +  " | " + "\n";
                table += rows;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return table;
    }
}
