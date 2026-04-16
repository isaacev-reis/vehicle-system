import dao.RentDAO;
import dao.VehicleDAO;
import model.Rent;
import model.Vehicle;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        VehicleDAO vehicleDAO = new VehicleDAO();

        System.out.println(vehicleDAO.findById(9L));

    }
}