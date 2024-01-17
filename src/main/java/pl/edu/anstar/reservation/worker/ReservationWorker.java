package pl.edu.anstar.reservation.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.anstar.reservation.model.Reservation;
import pl.edu.anstar.reservation.service.ReservationService;

@Component
public class ReservationWorker {

    @Autowired
    private ReservationService reservationService;

    public void processReservation(Reservation reservation) {
        // Tutaj dodaj logikę przetwarzania rezerwacji
    }
}
