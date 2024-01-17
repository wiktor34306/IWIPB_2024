package pl.edu.anstar.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.anstar.reservation.model.Reservation;
import pl.edu.anstar.reservation.repository.ReservationRepository;
import pl.edu.anstar.reservation.exception.RoomNotAvailableException;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Long roomId, Reservation reservation) {
        // Sprawdź, czy sala jest dostępna
        Optional<Reservation> existingReservation = reservationRepository.findByRoomId(roomId);
        if (existingReservation.isPresent()) {
            throw new RoomNotAvailableException("Room is already reserved");
        }

        // Jeśli sala jest dostępna, utwórz rezerwację
        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(Long reservationId) {
        // Pobierz rezerwację
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RoomNotAvailableException("Reservation not found"));
    }

    public Reservation updateReservation(Long reservationId, Reservation reservation) {
        // Sprawdź, czy rezerwacja istnieje
        Reservation existingReservation = getReservation(reservationId);

        // Aktualizuj rezerwację
        existingReservation.setRoom(reservation.getRoom());
        existingReservation.setUser(reservation.getUser());
        // Dodaj inne pola do aktualizacji

        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long reservationId) {
        // Usuń rezerwację
        reservationRepository.deleteById(reservationId);
    }

    public boolean isValid(Reservation reservation) {
        // Sprawdź, czy sala jest dostępna
        Optional<Reservation> existingReservation = reservationRepository.findByRoomId(reservation.getId());
        if (existingReservation.isPresent()) {
            // Jeśli sala jest już zarezerwowana, rezerwacja jest nieważna
            return false;
        }

        // Jeśli sala jest dostępna, rezerwacja jest ważna
        return true;
    }
}
