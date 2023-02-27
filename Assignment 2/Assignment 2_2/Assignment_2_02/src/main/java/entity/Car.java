package entity;

import jakarta.persistence.*;
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private long id;

    private String make;
    private String model;
    private int year;

    @OneToOne
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable
    private Owner owner;

    public Car() {

    }

    public Car(long id, String make, String model, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
