
import entity.Distance;
import entity.OnCampus;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Student student1 = new Student(101, "Lakpa", 4.0f);
        Student student2 = new Student(102, "Menla", 3.6f);
        Student student3 = new Student(103, "Karl", 2.9f);
        Student student4 = new Student(104, "Max", 3.8f);

        OnCampus cs590 = new OnCampus(590, "SA", LocalDate.of(2021, 8, 11),  "Payman", "VerillHall101", 30);
        OnCampus cs431 = new OnCampus(431, "MPP", LocalDate.of(2021, 5, 11), "Joseph", "VerillHall45", 25);
        OnCampus cs432 = new OnCampus(432, "FPP", LocalDate.of(2023, 9, 11), "Anne", "VerillHall45", 27);
        OnCampus cs433 = new OnCampus(433, "STC-1", LocalDate.of(2023, 9, 11), "Jim", "VerillHall45", 35);
        OnCampus cs434 = new OnCampus(434, "STC-2", LocalDate.of(2023, 9, 11), "Jim", "VerillHall45", 43);
        OnCampus cs435 = new OnCampus(435, "WAP", LocalDate.of(2023, 9, 11), "Michael", "VerillHall45", 36);
        OnCampus cs436 = new OnCampus(436, "Algorithms", LocalDate.of(2023, 9, 11), "Paul", "VerillHall45", 26);
        OnCampus cs437 = new OnCampus(437, "WAA", LocalDate.of(2023, 9, 11), "Muhyidin", "VerillHall45", 22);

        List<LocalDate> webinarDates = new ArrayList<>();
        webinarDates.add(LocalDate.of(2023, 9, 11));
        webinarDates.add(LocalDate.of(2023, 9, 11));
        webinarDates.add(LocalDate.of(2023, 9, 11));

        Distance cs521 = new Distance(521, "Big Data", LocalDate.of(2023, 9, 11), "Mrudula", "Mrudula", webinarDates);
        Distance cs541 = new Distance(541, "EA", LocalDate.of(2023, 9, 11), "Najeeb", "Najeeb", webinarDates);
        Distance cs542 = new Distance(542, "Parallel Programming", LocalDate.of(2023, 9, 11), "Najeeb", "Najeeb", webinarDates);
        Distance cs543 = new Distance(543, "DBMS", LocalDate.of(2023, 9, 11), "Pearl", "Pearl", webinarDates);
        Distance cs544 = new Distance(544, "ASD", LocalDate.of(2023, 9, 11), "Rene", "Rene", webinarDates);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(student1);
        em.persist(student2);
        em.persist(student3);
        em.persist(student4);

        em.persist(cs590);
        em.persist(cs431);
        em.persist(cs432);
        em.persist(cs433);
        em.persist(cs434);
        em.persist(cs435);
        em.persist(cs436);
        em.persist(cs437);

        em.persist(cs521);
        em.persist(cs542);
        em.persist(cs543);
        em.persist(cs544);

        student2.setCampusCourseAttending(cs431);
        student3.setCampusCourseAttending(cs434);
        student4.setCampusCourseAttending(cs435);

        student3.setDistanceCourseAttending(cs541);

        student1.addCourseAttended(cs431);
        student1.addCourseAttended(cs432);
        student1.addCourseAttended(cs433);
        student1.addCourseAttended(cs434);
        student1.addCourseAttended(cs435);
        student1.addCourseAttended(cs436);
        student1.addCourseAttended(cs437);
        student1.addCourseAttended(cs541);
        student1.addCourseAttended(cs590);
        student1.addCourseAttended(cs521);

        student2.addCourseAttended(cs432);
        student3.addCourseAttended(cs543);


        // ********************* using JPQL ***************************

        String queryString = "SELECT s FROM Student s JOIN s.campusCourseAttending c WHERE s.gpa > :gpa AND  c.capacity > :capacity";

        TypedQuery<Student> query1 = em.createQuery(queryString, Student.class);

        query1.setParameter("gpa", 3.5);
        query1.setParameter("capacity", 30);

        List<Student> students = query1.getResultList();

        System.out.println("students with GPA greater than 3.5 and attending a course with capacity > 30 are: ");
        Iterator<Student> studentIterator = students.iterator();
        while (studentIterator.hasNext()) {
            Student s = studentIterator.next();
            System.out.println(s.getStudentId());
        }


        // ********************* using Named Query ********************

        TypedQuery<Student> canGraduate = em.createNamedQuery("Student.findStudents", Student.class);
        List<Student> students2 = canGraduate.getResultList();

        System.out.println("All students that have a GPA greater than or equal to 3.0 and have taken at least 9 courses and are not enrolled in a course right now:");
        Iterator<Student> itr = students2.iterator();
        while (itr.hasNext()) {
            Student s = itr.next();
            System.out.println(s.getStudentId() + ", " + s.getStudentName());
        }



        // *********************** using criteria *********************

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);

        Root<Student> rootStudent = cq.from(Student.class);
        cq.select(rootStudent);

        Join<Student, Distance> joinDistance = rootStudent.join("distanceCourseAttending");

        Predicate gpaPredicate = cb.lessThan(rootStudent.get("gpa"), 3.0 );
        Predicate distanceProfessorPredicate = cb.equal(joinDistance.get("professorName"), "Najeeb");
        cq.where(gpaPredicate, distanceProfessorPredicate);

        TypedQuery<Student> cbQuery = em.createQuery(cq);
        List<Student> students3 = cbQuery.getResultList();

        System.out.println("All students with GPA less than 3.0 and attending a DE course with Najeeb:");
        Iterator<Student> stItr = students3.iterator();
        while(stItr.hasNext()) {
            Student s = stItr.next();
            System.out.println(s.getStudentId() + ", " + s.getStudentName());
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
