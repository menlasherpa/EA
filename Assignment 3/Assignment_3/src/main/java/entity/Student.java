package entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Student.findStudents", query = "SELECT s FROM Student s WHERE s.gpa >= 3 AND SIZE(s.courseAttended) >= 9 AND s.campusCourseAttending IS NULL AND s.distanceCourseAttending IS NULL")
@Entity
public class Student {

    @Id
    @GeneratedValue
    private long studentId;

    private String studentName;
    private float gpa;

    @OneToOne
    private OnCampus campusCourseAttending;

    @OneToOne
    private Distance distanceCourseAttending;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Course> courseAttended = new ArrayList<>();

    public Student() {

    }

    public Student(long studentId, String studentName, float gpa) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gpa = gpa;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public OnCampus getCampusCourseAttending() {
        return campusCourseAttending;
    }

    public void setCampusCourseAttending(OnCampus campusCourseAttending) {
        this.campusCourseAttending = campusCourseAttending;
    }

    public Distance getDistanceCourseAttending() {
        return distanceCourseAttending;
    }

    public void setDistanceCourseAttending(Distance distanceCourseAttending) {
        this.distanceCourseAttending = distanceCourseAttending;
    }

    public void setCourseAttended(List<Course> courseAttended) {
        this.courseAttended = courseAttended;
    }

    public void addCourseAttended(Course course) {
        courseAttended.add(course);
    }

    public List<Course> getCourseAttended() {
        return new ArrayList<>(this.courseAttended);
    }
}
