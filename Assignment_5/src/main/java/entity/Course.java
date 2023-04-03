package entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "COURSE_TYPE")
@Cacheable(value = true)
@EntityListeners({DeleteListener.class})
public abstract class Course {

    @Id
    @GeneratedValue
    private long courseId;

    @Transient
    public long lastSyncCourse;

    private String title;

    private LocalDate startDate;

    private String professorName;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Student student;

    public Course() {
        super();
    }

    public Course(long courseId, String title, LocalDate startDate, String professorName) {
        super();
        this.courseId = courseId;
        this.title = title;
        this.startDate = startDate;
        this.professorName = professorName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
}
