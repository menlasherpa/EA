import entity.Car;
import entity.Driver;
import entity.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        Car car1 = new Car(101, "Honda", "SUV", 2021);
//        Car car2 = new Car(102, "Subaru", "Meta", 2012);
        Owner owner = new Owner(1, "Lakpa Sherpa");
        Driver driver = new Driver(111, "Menla Sherpa");

        owner.addCar(car1);
        car1.setDriver(driver);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(car1);
        em.persist(driver);
        em.persist(owner);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
