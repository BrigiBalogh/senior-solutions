package activitytrackerspringbootsolution;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ActivityDao {

    @PersistenceContext
    private EntityManager entityManager;



    @Transactional
    public void saveActivity(Activity activity) {
        entityManager.persist(activity);
    }


    public Activity findActivityById(long id){
        Activity activity = entityManager.find(Activity.class, id);
        return activity;
    }


    public List<Activity> listActivities(){

        return entityManager
                .createQuery("select a from Activity a order by a.desc",
                        Activity.class)
                .getResultList();
    }

    @Transactional
    public void updateActivity(long id, String desc) {

        Activity activity = entityManager.find(Activity.class, id);
        activity.setDesc(desc);
    }

    @Transactional
    public void deleteActivity(long id) {

        Activity activity = entityManager.find(Activity.class, id);
        entityManager.remove(activity   );
    }



    public Activity findActivityByIdWithLabels(long id) {

       return entityManager
                .createQuery("select a from Activity a join fetch a.labels where a.id = :id",
                        Activity.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    public Activity findActivityByIdWithTrackPoints(long id) {

        return entityManager.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id"
                ,Activity.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    @Transactional
    public void addTrackPoint(long id,TrackPoint trackPoint) {

        Activity activity = entityManager.getReference(Activity.class, id);
        trackPoint.setActivity(activity);
        entityManager.persist(trackPoint);

    }


    public List<Object[]> findTrackPointCountByActivity() {

        return entityManager
                .createQuery("select count(t.activity) from TrackPoint t", Object[].class)
                .getResultList();
    }

    @Transactional
    public void removeActivitiesByDateAndType(LocalDateTime afterThis, ActivityType type) {

        entityManager.
                createQuery("delete  from Activity a  where a.startTime > :time and a.type = : type")
                .setParameter("time", afterThis)
                .setParameter("type", type)
                .executeUpdate();
    }

}
