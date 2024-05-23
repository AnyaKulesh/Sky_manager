package pl.pingwit.pingwitskymanager.validator;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.aircraft.CreateAircraftInputDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.ValidationException;
import pl.pingwit.pingwitskymanager.repository.aircraft.AircraftRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class AircraftValidator {
    private final AircraftRepository aircraftRepository;

    public AircraftValidator(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public void validateOnUpdate(String registrationPlate) {
        List<String> errors = new ArrayList<>();
        validateUniqueness(registrationPlate, errors);
        if (!errors.isEmpty()) {
            throw new ValidationException("Aircraft data is invalid", errors);
        }
    }

    public void validateOnCreate(CreateAircraftInputDto aircraftInputDto) {
        List<String> errors = new ArrayList<>();
        if (aircraftInputDto.getRegistrationPlate() == null) {
            errors.add("Registration plate should be not null");
        } else {
            validateUniqueness(aircraftInputDto.getRegistrationPlate(), errors);
        }

        if (aircraftInputDto.getMaxGrossWeight() == null) {
            errors.add("Max gross weight should be not null");
        }

        if (aircraftInputDto.getPassengerCapacity() == null) {
            errors.add("Passenger capacity should be not null");
        }

        if (aircraftInputDto.getCourseSpeedLimit() == null) {
            errors.add("Course speed limit should be not null");
        }

        if(aircraftInputDto.getModel() == null){
            errors.add("Aircraft model should be not null");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Aircraft data is invalid", errors);
        }
    }

    private void validateUniqueness(String registrationPlate, List<String> errors) {
        if (aircraftRepository.existsByRegistrationPlate(registrationPlate)) {
            errors.add(String.format("Aircraft with registration plate '%s' already exists", registrationPlate));
        }
    }
}
