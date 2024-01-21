package pl.edu.anstar.reservation.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    private Long room_id;
    private String room_name;
    private int capacity;

    // Getters and setters

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
