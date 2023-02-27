import entity.Car;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) throws Exception {
//        create();
//        read();
        delete();
//        update();

    }

    public static void create() {
        Car car = new Car(101, "Civic", "Honda", 2012, 100);
        Car car1 = new Car(102, "RAV4", "Toyota", 2023, 500);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(car);
        em.persist(car1);
        System.out.println("********* CREATE operation done **********");
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("End");

    }

    public static void read() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Long id = Long.valueOf(101);
        Car car = em.find(Car.class, id);
        System.out.println("************* READ Operation **********");
        System.out.println("ID: " + car.getId());
        System.out.println("Make: " + car.getMake());
        System.out.println("Model: " + car.getModel());
        System.out.println("Year: " + car.getYear());
        System.out.println("Mileage: " + car.getMileage());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void update() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Long id = Long.valueOf(101);
        Car car = em.find(Car.class, id);
        car.setMileage(300);
        System.out.println("********* UPDATE operation done **********");

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void delete() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Long id = Long.valueOf(102);
        Car car = em.find(Car.class, id);
        em.remove(car);
        System.out.println("********* DELETE operation done **********");

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
