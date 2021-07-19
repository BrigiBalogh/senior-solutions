package activitytrackerspringbootsolution;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TrackPointDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void saveTrackPoint(TrackPoint trackPoint) {
        entityManager.persist(trackPoint);
    }



    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDate afterThis, int start, int max) {

        return entityManager
                .createNamedQuery("findTrackPointCoordinatesByDate", Coordinate.class)
                .setParameter("time", afterThis)
                .setFirstResult(start)
                .setMaxResults(max).getResultList();
    }
}
