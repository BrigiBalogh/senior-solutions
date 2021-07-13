package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        Activity activity = new Activity(LocalDateTime.of(2021,3,15,9,0),
                "könnyű futás",ActivityType.RUNNING);
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
        factory.close();


    }
}
