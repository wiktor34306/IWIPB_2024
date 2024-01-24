package pl.edu.anstar.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private String user_name;

    private String room;

    private LocalDateTime start_time;
    private LocalDateTime end_time;

    // Getters and setters

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(User user_id) {
        this.user_name = user_name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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
