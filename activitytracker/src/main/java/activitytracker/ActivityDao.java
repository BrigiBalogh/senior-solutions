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
}
/*





    public void changeName(long id, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        employee.setName(name);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
    }
*/