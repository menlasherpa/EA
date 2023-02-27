package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long courseId;
    private String title;
    private int capacity;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    List<Student> students = new ArrayList<>();

    public Course() {

    }

    public Course(long courseId, String title, int capacity) {
        this.courseId = courseId;
        this.title = title;
        this.capacity = capacity;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getStudent() {
        return new ArrayList<Student>(this.students);
    }

}
