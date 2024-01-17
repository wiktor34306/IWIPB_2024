package pl.edu.anstar.reservation.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.anstar.reservation.model.Room;
import pl.edu.anstar.reservation.service.RoomService;

@Component
public class RoomWorker {

    private final RoomService roomService;

    @Autowired
    public RoomWorker(RoomService roomService) {
        this.roomService = roomService;
    }

    public void handleNewRoom(Room room) {
        // Sprawdź, czy sala o podanej nazwie już istnieje
        if (roomService.getRoomByName(room.getName()) != null) {
            // Jeśli sala o podanej nazwie już istnieje, zgłoś błąd
            throw new RuntimeException("Room with the given name already exists.");
        } else {
            // Jeśli sala o podanej nazwie nie istnieje, dodaj nową salę do bazy danych
            roomService.addRoom(room);
        }
    }
}
