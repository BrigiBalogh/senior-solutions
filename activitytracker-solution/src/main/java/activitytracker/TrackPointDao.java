package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

public class TrackPointDao {


    private EntityManagerFactory entityManagerFactory;

    public TrackPointDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public void saveTrackPoint(TrackPoint trackPoint) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(trackPoint);
        entityManager.getTransaction().commit();
        entityManager.close();
    }



    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDate afterThis, int start, int max) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Coordinate> data = entityManager
                        .createNamedQuery("findTrackPointCoordinatesByDate", Coordinate.class)
                        .setParameter("time", afterThis)
                        .setFirstResult(start)
                        .setMaxResults(max)
                        .getResultList();
        entityManager.close();
        return data;
    }
}


