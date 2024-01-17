package pl.edu.anstar.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.anstar.reservation.model.Room;
import pl.edu.anstar.reservation.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        // Sprawdź, czy sala o podanej nazwie już istnieje
        if (roomRepository.findByName(room.getName()) != null) {
            // Jeśli sala o podanej nazwie już istnieje, zgłoś błąd
            throw new RuntimeException("Room with the given name already exists.");
        } else {
            // Jeśli sala o podanej nazwie nie istnieje, dodaj nową salę do bazy danych
            return roomRepository.save(room);
        }
    }

    public Room getRoom(Long id) {
        // Pobierz salę z bazy danych za pomocą jej ID
        return roomRepository.findById(id).orElse(null);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        // Pobierz salę z bazy danych za pomocą jej ID
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found for this id :: " + id));

        // Aktualizuj dane pokoju
        room.setName(roomDetails.getName());
        room.setCapacity(roomDetails.getCapacity());

        // Zapisz zaktualizowane dane pokoju do bazy danych
        final Room updatedRoom = roomRepository.save(room);
        return updatedRoom;
    }

    public Room getRoomByName(String name) {
        // Pobierz salę z bazy danych za pomocą jej nazwy
        return roomRepository.findByName(name);
    }


    public void deleteRoom(Long id) {
        // Usuń salę z bazy danych za pomocą jej ID
        roomRepository.deleteById(id);
    }
}
