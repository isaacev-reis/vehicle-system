package model;

import java.time.LocalDate;

public class Rent {
    private Long id;
    private Vehicle vehicle;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean finished;

    public Rent(Vehicle vehicle, String clientName, LocalDate startDate) {
        this.vehicle = vehicle;
        this.clientName = clientName;
        this.startDate = startDate;
        this.finished = false;
    }

    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isFinished() {
        return finished;
    }
}
