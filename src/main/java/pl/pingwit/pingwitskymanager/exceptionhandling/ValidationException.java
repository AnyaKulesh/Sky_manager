package pl.pingwit.pingwitskymanager.exceptionhandling;

import java.util.List;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, List<String> errors) {
        super(message + errors);
    }
}
