package pl.pingwit.pingwitskymanager.validator;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.exceptionhandling.AlreadyExistsException;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModelRepository;
@Component
public class AircraftModelValidator {
    private final AircraftModelRepository aircraftModelRepository;

    public AircraftModelValidator(AircraftModelRepository aircraftModelRepository) {
        this.aircraftModelRepository = aircraftModelRepository;
    }

    public void validateOnCreate(String name) {
        // здесь также можно добавить проверку, что имя не пустое
        if (aircraftModelRepository.existsByName(name)) {
            throw new AlreadyExistsException(String.format("Aircraft model with name '%s' already exists", name));
        }
    }
}
