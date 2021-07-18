package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {


    private EntityManagerFactory entityManagerFactory;


    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
        entityManager.close();
   }


   public Activity findActivityById(long id){

       EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity activity = entityManager.find(Activity.class, id);
       entityManager.close();
       return activity;
   }


   public List<Activity> listActivities(){
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       List<Activity>activities = entityManager
               .createQuery("select a from Activity a order by a.desc",
                       Activity.class)
               .getResultList();
       entityManager.close();
       return activities;
    }

    public void updateActivity(long id, String desc) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity = entityManager.find(Activity.class, id);
        activity.setDesc(desc);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteActivity(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity = entityManager.find(Activity.class, id);
        entityManager.remove(activity   );
        entityManager.getTransaction().commit();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
       Activity activity = entityManager
                .createQuery("select a from Activity a join fetch a.labels where a.id = :id",
                        Activity.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.close();
        return activity;
    }

    public Activity findActivityByIdWithTrackPoints(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id"
                ,Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return activity;
    }



    public void addTrackPoint(long id,TrackPoint trackPoint) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        // Employee employee = entityManager.find(Employee.class, id);
        Activity activity = entityManager.getReference(Activity.class, id);
        trackPoint.setActivity(activity);
        entityManager.persist(trackPoint);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<Object[]> findTrackPointCountByActivity() {

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<Object[]> activityDatas = entityManager
                    .createQuery("select count(t.activity) from TrackPoint t", Object[].class)
                    .getResultList();
            entityManager.close();
            return activityDatas;
    }


    public void removeActivitiesByDateAndType(LocalDateTime afterThis, ActivityType type) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.
                createQuery("delete  from Activity a  where a.startTime > :time and a.type = : type")
                .setParameter("time", afterThis)
                .setParameter("type", type)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}




