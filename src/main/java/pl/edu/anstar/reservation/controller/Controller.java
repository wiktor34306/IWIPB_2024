package pl.edu.anstar.reservation.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pl.edu.anstar.reservation.exception.InvalidReservationException;
import pl.edu.anstar.reservation.exception.TechnicalException;

import org.slf4j.LoggerFactory;

public abstract class Controller {

    @Autowired private HttpServletRequest request;

    public abstract Logger getLogger();
    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    @ExceptionHandler(InvalidReservationException.class)
    public ResponseEntity<Map<String, String>> invalidReservationExceptionHandler(InvalidReservationException e) {
        getLogger().warn("Handling invalid reservation exception", e);
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Map<String, String>> technicalExceptionHandler(TechnicalException e) {
        getLogger().warn("Handling technical exception", e);
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    protected String getServerHost() {
        HttpServletRequest request = getHttpServletRequest();
        return request
                .getRequestURL()
                .substring(0, request.getRequestURL().length() - request.getRequestURI().length());
    }

    protected HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getResponse();
    }
}