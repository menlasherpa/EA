package entity;

import jakarta.persistence.PostRemove;

public class DeleteListener {

    @PostRemove
    void logStudentOrCourse(Object entity) {
        if(entity instanceof Student) {
            System.out.println("Student deleted: " + ((Student) entity).getStudentName());
        } else if(entity instanceof OnCampus) {
            System.out.println("Course deleted: " + ((OnCampus) entity).getCourseId());
        } else if (entity instanceof Distance) {
            System.out.println("Distance course deleted:" + ((Distance) entity).getCourseId());
        }
    }
}
