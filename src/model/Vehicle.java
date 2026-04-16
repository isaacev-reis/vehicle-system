package model;

import java.util.concurrent.ThreadLocalRandom;

public class Vehicle {
    private Long id;
    private String plate;
    private String brand;
    private String model;
    private int year;
    private double dailyRate;
    private boolean available;

    public Vehicle(String plate, String brand, String model, int year, double dailyRate) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", dailyRate=" + dailyRate +
                ", available=" + available +
                '}';
    }
}
