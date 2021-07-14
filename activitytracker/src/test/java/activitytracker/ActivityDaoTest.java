package activitytracker;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {


    private ActivityDao activityDao;

    Activity activityVariant1;
    Activity activityVariant2;
    Activity activityVariant3;
    Activity activityVariant4;



    @BeforeEach
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);



        Activity activityVariant1 = new Activity(LocalDateTime.of(2021, 3, 15, 9, 0),
                "easy running", ActivityType.RUNNING);

        Activity activityVariant2 = new Activity(LocalDateTime.of(2021, 4, 11, 9, 0),
                "Tour of the Alps", ActivityType.HIKING);

        Activity activityVariant3 = new Activity(LocalDateTime.of(2021, 5, 12, 9, 0),
                "mountain biking", ActivityType.BIKING);

        Activity activityVariant4= new Activity(LocalDateTime.of(2021, 1, 12, 9, 0),
                "a little basketball", ActivityType.BASKETBALL);

      /*  MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/activitytracker ");
        dataSource.setUser("activitytracker ");
       dataSource.setPassword("activitytracker ");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();*/
    }

    @Test
    void testSaveActivity() {

      //  Activity activity = new Activity(LocalDateTime.of(2021, 3, 15, 9, 0),
      //          "easy running", ActivityType.RUNNING);
        activityDao.saveActivity(activityVariant1);
        Activity loadedActivity = activityDao
                .findActivityById(activityVariant1.getId());
        assertEquals("easy running", loadedActivity.getDesc());
    }

    @Test
    void testFindActivityById() {
        activityDao.saveActivity(activityVariant1);

        Long id = activityVariant1.getId();

        activityVariant1 = activityDao.findActivityById(id);

        assertEquals("easy running", activityVariant1.getDesc());
        assertEquals(LocalDateTime.of(2021, 3, 15, 9, 0), activityVariant1.getStartTime());
        assertEquals(ActivityType.RUNNING, activityVariant1.getType());


    }

    @Test
    void testListActivities() {
      activityDao.saveActivity(activityVariant1);
      activityDao.saveActivity(activityVariant2);
      activityDao.saveActivity(activityVariant3);
      activityDao.saveActivity(activityVariant4);

        List<Activity> activities = activityDao.listActivities();
        List<String> description = activities.stream()
                .map(Activity::getDesc)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("easy running", "Tour of the Alps", "mountain biking","a little basketball" ),
                description);

        assertThat(activities)
                .hasSize(4)
                .extracting(Activity::getType)
                .contains(ActivityType.BIKING, ActivityType.HIKING,
                        ActivityType.BASKETBALL, ActivityType.RUNNING);

    }
}






