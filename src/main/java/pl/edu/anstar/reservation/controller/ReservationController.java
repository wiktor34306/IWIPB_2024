package pl.edu.anstar.reservation.controller;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.anstar.reservation.ProcessConstants;
import pl.edu.anstar.reservation.model.Reservation;
import pl.edu.anstar.reservation.service.ReservationService;

import java.util.Map;

@RestController
@RequestMapping("/reservations")
public class ReservationController extends Controller {

//    private static final Logger LOG = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;
//    @Qualifier("zeebeClientLifecycle")
//    @Autowired
//    private ZeebeClient client;

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
//    @PostMapping("/start")
//    public void startProcessInstance(@RequestBody Map<String, Object> variables) {
//
//        LOG.info("Starting process " + ProcessConstants.BPMN_PROCESS_ID + " with variables: " + variables);
//
//        //variables.put("orderTotal", 200);
//
//        client
//                .newCreateInstanceCommand()
//                .bpmnProcessId(ProcessConstants.BPMN_PROCESS_ID)
//                .latestVersion()
//                .variables(variables)
//                .send();
//    }

//    @PostMapping("/start")
//    public ResponseEntity<String> startProcessInstance(@RequestBody Map<String, Object> variables) {
//
//        LOG.info("Starting process with variables: " + variables);
//
//        // Extract room selection from variables
//        String roomSelection = (String) variables.get("select_clpl2n");
//        if (roomSelection == null) {
//            return ResponseEntity.badRequest().body("Room selection is required");
//        }
//
//        // Create a new Reservation object
//        Reservation reservation = new Reservation();
//        reservation.setRoomId(Long.parseLong(roomSelection));
//
//        // Call the createReservation method to start the reservation process
//        Reservation createdReservation = reservationService.createReservation(Long.parseLong(roomSelection), reservation);
//
//        return ResponseEntity.ok("Reservation process started for room: " + roomSelection);
//    }
//
//    @PostMapping("/start")
//    public ResponseEntity<Reservation> startProcessInstance(@RequestBody Map<String, Object> variables) {
//
//        LOG.info("Starting process with variables: " + variables);
//
//        // Extract room selection from variables
//        String roomSelection = (String) variables.get("select_clpl2n");
//        if (roomSelection == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Create a new Reservation object
//        Reservation reservation = new Reservation();
//        reservation.setRoomId(Long.parseLong(roomSelection));
//
//        // Call the createReservation method to start the reservation process
//        Reservation createdReservation = reservationService.createReservation(Long.parseLong(roomSelection), reservation);
//
//        return ResponseEntity.ok(createdReservation);
//    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(ReservationController.class);
    }
}
