import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        Student student1 = new Student(101, "Lakpa Sherpa");
        Student student2 = new Student(102, "Hira Kafle");
        Student student3 = new Student(103, "Lady Gaga");

        Course cs472 = new Course(472, "WAP", 30);
        Course cs544 = new Course(544, "EA", 100);
        Course cs590 = new Course(590, "SA", 30);

        cs472.addStudent(student1);
        cs472.addStudent(student2);
        cs472.addStudent(student3);

//        Car car1 = new Car(1, "SUV", "Toyota", 2013);
//        Car car2 = new Car(2, "SUV", "Honda", 2017);
//        Driver driver = new Driver(101, "Driver Sharma");
//        Owner owner = new Owner(103, "Owner KC");
//
//        owner.addCar(car1);
//        owner.addCar(car2);
//        car1.setDriver(driver);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(student1);
        em.persist(student2);
        em.persist(student3);
        em.persist(cs472);


//        em.persist(car1);
//        em.persist(car2);
//        em.persist(driver);
//        em.persist(owner);

        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
