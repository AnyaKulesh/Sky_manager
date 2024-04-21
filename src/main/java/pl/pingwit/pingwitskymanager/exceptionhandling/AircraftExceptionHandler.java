package pl.pingwit.pingwitskymanager.exceptionhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class AircraftExceptionHandler {
    @ExceptionHandler(AircraftNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(AircraftNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AircraftAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(AircraftAlreadyExistsException e) {
        return ResponseEntity.status(FORBIDDEN).body(e.getMessage());
    }
}
