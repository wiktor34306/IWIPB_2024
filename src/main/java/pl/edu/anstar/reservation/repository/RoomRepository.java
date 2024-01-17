package pl.edu.anstar.reservation.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.anstar.reservation.model.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

    Room findByName(String name);
}
