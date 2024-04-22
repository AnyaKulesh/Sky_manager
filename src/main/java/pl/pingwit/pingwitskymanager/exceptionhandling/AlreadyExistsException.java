package pl.pingwit.pingwitskymanager.exceptionhandling;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
