package entity;

import jakarta.persistence.PostPersist;

public class PersistListener {

    @PostPersist
    void logStudent(Student student) {
        System.out.println("Student persisted: " + student.getStudentName());
    }

}
