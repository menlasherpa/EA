package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("On-campus")
public class OnCampus extends Course {

    private String room;
    int capacity;

    public OnCampus() {
        super();
    }

    public OnCampus(long courseId, String title, LocalDate startDate, String professorName, String room, int capacity) {
        super(courseId, title, startDate, professorName);
        this.room = room;
        this.capacity = capacity;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
