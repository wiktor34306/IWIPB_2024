package pl.edu.anstar.reservation;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath*:/model/*.*")
public class ConferenceRoomReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceRoomReservationApplication.class, args);
    }
}