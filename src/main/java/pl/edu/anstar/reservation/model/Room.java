package pl.edu.anstar.reservation.model;

import javax.persistence.*;

@Entity
@Table(name = "Room", schema = "bpmn_projekt")
public class Room {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "room_seq")
    @SequenceGenerator(
        name = "room_seq",
        schema = "bpmn_projekt",
        initialValue = 6)
    private Long room_id;
    private String roomName;
    private int capacity;

    // Getters and setters

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String room_name) {
        this.roomName = room_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
