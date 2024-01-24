package pl.edu.anstar.reservation.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.anstar.reservation.model.Reservation;
import pl.edu.anstar.reservation.repository.ReservationRepository;
import pl.edu.anstar.reservation.exception.RoomNotAvailableException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {

        // Jeśli sala jest dostępna, utwórz rezerwację
        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(Long reservationId) {
        // Pobierz rezerwację
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RoomNotAvailableException("Reservation not found"));
    }

//    public Reservation updateReservation(Long reservationId, Reservation reservation) {
//        // Sprawdź, czy rezerwacja istnieje
//        Reservation existingReservation = getReservation(reservationId);
//
//        // Aktualizuj rezerwację
//        existingReservation.setRoom(reservation.getRoom());
//        existingReservation.setUser_id(reservation.getUser_id());
//        // Dodaj inne pola do aktualizacji
//
//        return reservationRepository.save(existingReservation);
//    }

    public void deleteReservation(Long reservationId) {
        // Usuń rezerwację
        reservationRepository.deleteById(reservationId);
    }

    public boolean isValid(LocalDateTime localDateTime) {
//        return true;
        // Sprawdź, czy sala jest dostępna
        List<Reservation> existingReservation = reservationRepository.findAllByStartTime(localDateTime);
        System.out.println(existingReservation);
        if (existingReservation.isEmpty()) {
            // Jeśli sala jest już zarezerwowana, rezerwacja jest nieważna
            return true;
        }

        // Jeśli sala jest dostępna, rezerwacja jest ważna
        return false;
    }
}
