package pl.pingwit.pingwitskymanager.validator;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.exceptionhandling.AlreadyExistsException;
import pl.pingwit.pingwitskymanager.repository.aircraft.AircraftRepository;

@Component
public class AircraftValidator {
    private final AircraftRepository aircraftRepository;

    public AircraftValidator(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public void validateOnUpdate(String registrationPlate){
        if (aircraftRepository.existsByRegistrationPlate(registrationPlate)) {
            throw new AlreadyExistsException(String.format("Aircraft with registration plate '%s' already exists", registrationPlate));
        }
    }

    // нужно создать метод validateOnCreate
}
