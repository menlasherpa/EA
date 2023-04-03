package entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Distance")
@Cacheable(value = false)
@EntityListeners({DeleteListener.class})
public class Distance extends Course {

    private String examProfessor;

    @ElementCollection
    private List<LocalDate> webinarSessionDates = new ArrayList<>();

    public Distance() {
        super();
    }

    public Distance(long courseId, String title, LocalDate startDate, String professorName, String examProfessor, List<LocalDate> webinarSessionDates) {
        super(courseId, title, startDate, professorName);
        this.examProfessor = examProfessor;
        this.webinarSessionDates = webinarSessionDates;
    }

    public String getExamProfessor() {
        return examProfessor;
    }

    public void setExamProfessor(String examProfessor) {
        this.examProfessor = examProfessor;
    }

    public List<LocalDate> getWebinarSessionDates() {
        return webinarSessionDates;
    }

    public void setWebinarSessionDates(List<LocalDate> webinarSessionDates) {
        this.webinarSessionDates = webinarSessionDates;
    }
}
