//package pl.edu.anstar.reservation.worker;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import pl.edu.anstar.reservation.model.Reservation;
//import pl.edu.anstar.reservation.service.ReservationService;
//
//@Component
//public class ReservationWorker {
//
//    @Autowired
//    private ReservationService reservationService;
//
//    public void processReservation(Reservation reservation) {
//        // Tutaj dodaj logikę przetwarzania rezerwacji
//    }
//}
package pl.edu.anstar.reservation.worker;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.anstar.reservation.model.Reservation;
import pl.edu.anstar.reservation.service.ReservationService;

@Component
public class ReservationWorker {

    @Autowired
    private ReservationService reservationService;

//    @JobWorker(type = "reserveRoom")
    public void processReservation(Reservation reservation) {
        // Pobierz informacje o rezerwacji
        Long reservationId = reservation.getReservation_id();

        // Sprawdź, czy rezerwacja jest ważna
        boolean isValid = reservationService.isValid(reservation);

        if (isValid) {
            // Jeśli rezerwacja jest ważna, zaktualizuj rezerwację
            reservationService.updateReservation(reservationId, reservation);
        } else {
            // Jeśli rezerwacja nie jest ważna, zwróć odpowiednią informację
            throw new RuntimeException("Reservation is not valid.");
        }
    }
}
