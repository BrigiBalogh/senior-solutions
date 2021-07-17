package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {


    private ActivityDao activityDao;

    @BeforeEach
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao (entityManagerFactory);
    }

    @Test
    public void testSaveAndFind() {
        activityDao.saveActivity(new Activity(
                LocalDateTime.of(2021, 7, 15, 9, 0),
                "easy running"));


        activityDao.saveActivity(new SimpleActivity(
                LocalDateTime.of(2021, 5, 15, 9, 0),
                "mountain biking", "Funchal"));


        activityDao.saveActivity(new ActivityWithTrack(
                LocalDateTime.of(2021, 6, 15, 9, 0),
                "tour in the mountains", 25, 11000));

        Activity activity = activityDao.findActivityByDescription("easy running");
        assertEquals("easy running", activity.getDescription());

        Activity simpleActivity = activityDao.findActivityByDescription("mountain biking");
        assertEquals("mountain biking", simpleActivity.getDescription());
        assertEquals("Funchal", ((SimpleActivity) simpleActivity).getPlace());


        Activity activityWithTrack = activityDao.findActivityByDescription("tour in the mountains");
        assertEquals("tour in the mountains", activityWithTrack.getDescription());
        assertEquals(25, ((ActivityWithTrack) activityWithTrack).getDistance());
    }
}