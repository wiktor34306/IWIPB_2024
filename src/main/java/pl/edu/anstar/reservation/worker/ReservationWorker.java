package pl.edu.anstar.reservation.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.anstar.reservation.exception.InvalidReservationException;
import pl.edu.anstar.reservation.model.Reservation;
import pl.edu.anstar.reservation.service.ReservationService;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class ReservationWorker {

    private ReservationService reservationService;

    @JobWorker(type = "checkValidDate")
    public Map<String, Object> registerApplication(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        String dateTimeString = job.getVariablesAsMap().get("datetime_a89z3v").toString();
        OffsetDateTime odt = OffsetDateTime.parse(dateTimeString);
        LocalDateTime dateTime = odt.toLocalDateTime();

        Boolean isValid = reservationService.isValid(dateTime);

        jobResultVariables.put("isAvailable", isValid);

        return jobResultVariables;
    }

    @JobWorker(type = "reserveRoom")
    public Map<String, Object> reservation(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        String dateTimeString = job.getVariablesAsMap().get("datetime_a89z3v").toString();
        OffsetDateTime odt = OffsetDateTime.parse(dateTimeString);
        LocalDateTime dateTime = odt.toLocalDateTime();

        String dateTimeString2 = job.getVariablesAsMap().get("datetime_qws6f").toString();
        OffsetDateTime odt2 = OffsetDateTime.parse(dateTimeString2);
        LocalDateTime dateTime2 = odt2.toLocalDateTime();

        Reservation reservation = Reservation.builder()
                .end_time(dateTime2)
                .start_time(dateTime)
                .room(job.getVariablesAsMap().get("select_cbxj6c").toString())
                .user_name(job.getVariablesAsMap().get("textfield_zuk19b").toString()  //first_name
                        + ' ' + job.getVariablesAsMap().get("textfield_2sz4hc").toString() //second_name
                        + ' ' + job.getVariablesAsMap().get("textfield_y4ipsg").toString()) //company_name
                .build();

        Reservation object = reservationService.createReservation(reservation);
        jobResultVariables.put("reservation", object);
        return jobResultVariables;
    }
}
