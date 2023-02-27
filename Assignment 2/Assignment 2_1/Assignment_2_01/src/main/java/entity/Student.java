package entity;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public Student() {

    }

    public Student(long studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
