package pl.pingwit.pingwitskymanager.exceptionhandling;

public class AircraftNotFoundException extends RuntimeException{
    public AircraftNotFoundException(String message){
        super(message);
    }
}
