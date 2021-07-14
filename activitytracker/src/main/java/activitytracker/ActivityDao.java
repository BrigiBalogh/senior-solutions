package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    public void changeDescription(long id, String desc) {
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
        entityManager.remove(activity);
        entityManager.getTransaction().commit();
    }

}






