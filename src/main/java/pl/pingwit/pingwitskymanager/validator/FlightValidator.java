package pl.pingwit.pingwitskymanager.validator;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.flight.CreateFlightInputDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightValidator implements Validator {
    private final CrewValidator crewValidator;

    public FlightValidator(CrewValidator crewValidator) {
        this.crewValidator = crewValidator;
    }

    public void validateOnCreate(CreateFlightInputDto flightInputDto) {
        List<String> errors = new ArrayList<>();
        validateFlightData(flightInputDto, errors);
        if (!errors.isEmpty()) {
            throw new ValidationException("Flight data is not valid", errors);
        }

        crewValidator.validateCrewInput(flightInputDto.getCrew());
    }

    private void validateFlightData(CreateFlightInputDto flightInputDto, List<String> errors) {
        if (flightInputDto.getRegistrationPlate() == null) {
            errors.add("Registration plate should not null");
        }

        if (flightInputDto.getFrom() == null) {
            errors.add("Departure airport should be not null");
        }

        if (flightInputDto.getTo() == null) {
            errors.add("Arrival airport should be not null");
        }

        if (flightInputDto.getCrew() == null) {
            errors.add("Crew should not be null");
        }

        if (flightInputDto.getTakeOffDateTime() == null) {
            errors.add("Take off date time should be not null");
        }

        if (flightInputDto.getTravelTime() == null) {
            errors.add("Travel time should be not null");
        } else if (flightInputDto.getTravelTime() <= 0) {
            errors.add("Travel time should be positive");
        }
    }
}
