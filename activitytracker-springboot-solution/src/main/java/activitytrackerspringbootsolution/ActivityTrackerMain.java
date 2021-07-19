package activitytrackerspringbootsolution;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ActivityTrackerMain    {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
    EntityManager em = factory.createEntityManager();
}
