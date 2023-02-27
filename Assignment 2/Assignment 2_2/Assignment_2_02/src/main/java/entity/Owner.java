package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue
    private long ownerId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    public Owner() {

    }
    public Owner(long ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public List<Car> getCars() {
        return new ArrayList<Car>(this.cars);
    }
}
