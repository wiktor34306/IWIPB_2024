package pl.edu.anstar.reservation.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservation", schema = "bpmn_projekt")
public class Reservation {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "reservation_seq")
    @SequenceGenerator(
        name = "reservation_seq",
        schema = "bpmn_projekt",
        initialValue = 6)
    private Long reservation_id;

    @ManyToOne
    private User user_id;

    @ManyToOne
    private Room room;

    private LocalDateTime start_time;
    private LocalDateTime end_time;

    // Getters and setters

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }
}
