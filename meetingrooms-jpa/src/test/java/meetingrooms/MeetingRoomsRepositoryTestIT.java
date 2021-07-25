package meetingrooms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsRepositoryTestIT {


     EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
     MeetingRoomsRepositoryWithJPa repository = new MeetingRoomsRepositoryWithJPa(factory);


    @Test
    @DisplayName("Sava two meeting rooms than query all")
    void testSaveThanList() {
        repository.save("Jupiter", 100, 100);
        repository.save("Neptune", 100, 100);
        List<String> names = repository.getMeetingRoomsOrderedByName();
        assertEquals(List.of("Jupiter, Neptune"), names);
    }

}