package activitytrackerspringbootsolution;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AreaDao {

     @PersistenceContext
    private EntityManager entityManager;


   @Transactional
    public void saveArea(Area area) {
        entityManager.persist(area);
    }

    public Area findAreaByName(String name) {
        return entityManager.createQuery("select a from Area a join fetch a.activities where a.name = :name"
                ,Area.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    public Area findAreaById(Long id) {

       return entityManager.createQuery("select a from Area a join fetch a.cities where a.id = :id",
                Area.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
