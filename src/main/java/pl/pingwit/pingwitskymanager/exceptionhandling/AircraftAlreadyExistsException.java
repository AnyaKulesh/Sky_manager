package pl.pingwit.pingwitskymanager.exceptionhandling;

public class AircraftAlreadyExistsException extends RuntimeException {
    public AircraftAlreadyExistsException(String message) {
        super(message);
    }
}
