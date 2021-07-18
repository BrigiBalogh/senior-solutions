package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrackPointDaoTest {

    TrackPointDao trackPointDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        trackPointDao = new TrackPointDao (entityManagerFactory);
    }

 /*   @Test
    void saveTrackPoint() {

        trackPointDao.saveTrackPoint(new TrackPoint(
                LocalDate.of(2019,5,12), 32.66568, -16.92547));
        TrackPoint trackPoint = trackPointDao.findTrackPointCoordinatesByDate(
                LocalDate.of(2019,5,12), 3, 3);
    }*/

}

