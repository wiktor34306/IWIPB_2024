package pl.edu.anstar.reservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.anstar.reservation.model.Reservation;
import pl.edu.anstar.reservation.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController extends Controller {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{roomId}")
    public ResponseEntity<Reservation> createReservation(@PathVariable Long roomId,
                                                         @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.createReservation(roomId, reservation));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.getReservation(reservationId));
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId,
                                                         @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(reservationId, reservation));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(ReservationController.class);
    }
}
