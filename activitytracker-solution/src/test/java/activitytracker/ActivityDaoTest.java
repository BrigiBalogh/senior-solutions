package activitytracker;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {


    ActivityDao activityDao;
    AreaDao areaDao;


    Activity activityVariant1;
    Activity activityVariant2;
    Activity activityVariant3;
    Activity activityVariant4;


    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);
        areaDao = new AreaDao(entityManagerFactory);


        activityVariant1 = new Activity(LocalDateTime.of(2021, 3, 15, 9, 0),
                "easy running", ActivityType.RUNNING);

        activityVariant2 = new Activity(LocalDateTime.of(2021, 4, 11, 9, 0),
                "Tour of the Alps", ActivityType.HIKING);

        activityVariant3 = new Activity(LocalDateTime.of(2021, 5, 12, 9, 0),
                "mountain biking", ActivityType.BIKING);

        activityVariant4 = new Activity(LocalDateTime.of(2021, 1, 12, 9, 0),
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
        assertEquals(Arrays.asList("a little basketball", "easy running", "mountain biking", "Tour of the Alps"),
                description);

        assertThat(activities)
                .hasSize(4)
                .extracting(Activity::getType)
                .contains(ActivityType.BIKING, ActivityType.HIKING,
                        ActivityType.BASKETBALL, ActivityType.RUNNING);
    }


    @Test
    void testUpdateActivity() {
        activityDao.saveActivity(activityVariant1);
        activityDao.updateActivity(activityVariant1.getId(), "fast running");
        Activity modifiedActivity = activityDao.findActivityById(activityVariant1.getId());

        assertEquals("fast running", modifiedActivity.getDesc());
    }

    @Test
    void testDelete() {
        activityDao.saveActivity(activityVariant1);
        activityDao.saveActivity(activityVariant2);
        activityDao.deleteActivity(activityVariant1.getId());

        List<Activity> activities = activityDao.listActivities();

        List<String> descriptions = activities.stream()
                .map(Activity::getDesc).collect(Collectors.toList());
        assertEquals(Arrays.asList("Tour of the Alps"), descriptions);
    }

    @Test
    void testIllegalId() {
        Activity activity = activityDao.findActivityById(12L);
        assertEquals(null, activity);
    }

    @Test
    void testFindActivityByIdWithLabels() {

        activityVariant1.setLabels(List.of("könnyű", "közepes", "erős"));
        activityDao.saveActivity(activityVariant1);
        Long id = activityVariant1.getId();

        activityVariant2 = activityDao.findActivityByIdWithLabels(id);
        assertEquals(3, activityVariant1.getLabels().size());

        assertThat(activityVariant2.getLabels())
                .hasSize(3)
                .containsExactly("könnyű", "közepes", "erős");
    }

    @Test
    void testFindActivityByIdWithTrackPoints() {

        TrackPoint trackPointFirst = new TrackPoint(LocalDate.of(2021, 6, 12), 25.5, 95.5);
        TrackPoint trackPointSecond = new TrackPoint(LocalDate.of(2021, 5, 5), 35.5, 115.5);

        activityVariant2.addTrackPoint(trackPointFirst);
        activityVariant2.addTrackPoint(trackPointSecond);
        activityDao.saveActivity(activityVariant2);

        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activityVariant2.getId());
        assertEquals(2, anotherActivity.getTrackPoints().size());
        assertEquals(trackPointFirst, anotherActivity.getTrackPoints().get(0).getTime());

        assertThat(anotherActivity.getTrackPoints())
                .hasSize(2)
                .extracting(TrackPoint::getTime)
                .containsExactly(LocalDate.of(2021, 6, 12),
                        LocalDate.of(2021, 5, 5));

        assertEquals(LocalDate.of(2021, 6, 12),
                anotherActivity.getTrackPoints().get(0).getTime());
    }

    @Test
    void testAddTrackPoint() {

        activityDao.saveActivity(activityVariant2);

        activityDao.addTrackPoint(activityVariant2.getId(), new TrackPoint(LocalDate.of(
                2021, 6, 12), 25.5, 95.5));
        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activityVariant2.getId());
        assertEquals(1, anotherActivity.getTrackPoints().size());
    }

    @Test
    void testSaveArea() {

        activityDao.saveActivity(activityVariant1);
        activityDao.saveActivity(activityVariant2);
        activityDao.saveActivity(activityVariant3);

        Area madeira = new Area("Funchal");
        Area spain = new Area("Sierra Nevada");
        Area italy = new Area("Amalfi");

        madeira.addActivity(activityVariant1);
        madeira.addActivity(activityVariant2);
        madeira.addActivity(activityVariant3);

        spain.addActivity(activityVariant1);
        spain.addActivity(activityVariant2);
        spain.addActivity(activityVariant3);

        italy.addActivity(activityVariant1);
        italy.addActivity(activityVariant2);
        italy.addActivity(activityVariant3);

        areaDao.saveArea(madeira);
        areaDao.saveArea(spain);
        areaDao.saveArea(italy);

        Area area = areaDao.findAreaByName("Funchal");

        assertEquals(List.of(activityVariant1, activityVariant2, activityVariant3),
                area.getActivities().stream()
                        .map(Activity::getAreas)
                        .collect(Collectors.toList()));
    }


    @Test
    void testActivityWithDetailsAttributes() {

        activityVariant1.setDistance(50);
        activityVariant1.setDuration(11000);

        activityDao.saveActivity(activityVariant1);

        Activity anotherActivity = activityDao.findActivityById(activityVariant1.getId());
        assertEquals(50, anotherActivity.getDistance());
    }


    void testFindTrackPointCountByActivity() {
        activityDao.saveActivity(activityVariant1);
        List<Object[]> data = activityDao.findTrackPointCountByActivity();
        assertEquals(1, data.size());
        assertEquals("easy running", data.get(0)[1]);
    }


    void removeActivitiesByDateAndType() {
        activityDao.saveActivity(activityVariant1);
        activityDao.saveActivity(activityVariant2);
        activityDao.saveActivity(activityVariant3);

        activityDao.removeActivitiesByDateAndType(LocalDateTime.of(
                2021, 5, 10, 9, 0),
                ActivityType.BIKING);
        List<Activity> activities = activityDao.listActivities();
        assertEquals(1, activities.size());
    }
}






