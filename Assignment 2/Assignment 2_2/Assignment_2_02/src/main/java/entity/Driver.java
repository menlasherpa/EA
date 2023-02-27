package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Driver{
    @Id
    @GeneratedValue
    private long driverId;
    private String name;

    public Driver() {

    }
    public Driver(long driverId, String name) {
        this.driverId = driverId;
        this.name = name;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
