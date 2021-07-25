package meetingrooms;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class MeetingRoomsRepositoryWithJPa  implements MeetingRoomsRepository {


    private EntityManagerFactory factory;

    @Override
    public MeetingRoom save(String name, int width, int length){
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(meetingRoom);
        entityManager.getTransaction().commit();
        entityManager.close();
        return meetingRoom;
    }


    @Override
    public List<String> getMeetingRoomsOrderedByName() {
        EntityManager entityManager = factory.createEntityManager();
        List<String> names = entityManager
                .createQuery("select mr.name from MeetingRoom mr order by mr.name",
                        String.class)
                .getResultList();
        entityManager.close();
        return names;

    }

    @Override
    public List<String> getEverySecondMeetingRoom() {

        EntityManager entityManager = factory.createEntityManager();
        List<String> meetingRooms = entityManager.createQuery(
                "select mr.name from MeetingRoom mr order by mr.name", String.class)
                .getResultList();
        entityManager.close();
        return IntStream.range(0, meetingRooms.size())
                .filter(m -> (m + 1) % 2 == 0)
                .mapToObj(meetingRooms::get)
                .collect(Collectors.toList());
    }


   @Override
   public List<MeetingRoom> getMeetingRooms() {
       EntityManager entityManager = factory.createEntityManager();
       List<MeetingRoom> meetingRooms = entityManager
               .createQuery("select mr from MeetingRoom mr ",
                       MeetingRoom.class)
               .getResultList();
       entityManager.close();
       return meetingRooms;
   }


   @Override
    public List<MeetingRoom> getExactMeetingRoomByName(String name) {

        EntityManager entityManager = factory.createEntityManager();
        List<MeetingRoom> meetingRooms = entityManager
                .createQuery("select mr from MeetingRoom mr where mr.name = :name order by mr.name",
                MeetingRoom.class)
                .setParameter("name", name)
                .getResultList();
        entityManager.close();
        return meetingRooms;
    }


    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix) {
        EntityManager entityManager = factory.createEntityManager();
        List<MeetingRoom> meetingRooms = entityManager
                .createQuery("select mr from MeetingRoom mr where mr.name like :name  order by mr.name",
                        MeetingRoom.class)
                .setParameter("name", nameOrPrefix + "%")
                .getResultList();
        entityManager.close();
        return meetingRooms;
    }


    @Override
    public void deleteAll() {

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from MeetingRoom")
        .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
