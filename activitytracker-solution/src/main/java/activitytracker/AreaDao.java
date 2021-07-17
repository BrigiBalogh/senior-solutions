package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AreaDao {


    private EntityManagerFactory entityManagerFactory;

    public AreaDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveArea(Area area) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(area);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Area findAreaByName(String name) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Area area = entityManager.createQuery("select a from Area a join fetch a.activities where a.name = :name"
                ,Area.class)
                .setParameter("name", name)
                .getSingleResult();
        entityManager.close();
        return area;
    }
}
