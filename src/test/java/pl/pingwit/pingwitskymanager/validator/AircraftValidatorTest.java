package pl.pingwit.pingwitskymanager.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pingwit.pingwitskymanager.controller.aircraft.CreateAircraftInputDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.ValidationException;
import pl.pingwit.pingwitskymanager.repository.aircraft.AircraftRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AircraftValidatorTest {

    private static final String NEW_REGISTRATION_PLATE = "EW455PA";
    private static final String EXISTED_REGISTRATION_PLATE = "EW454PA";

    private final AircraftRepository aircraftRepository = mock(AircraftRepository.class);
    private final AircraftValidator aircraftValidator = new AircraftValidator(aircraftRepository);

    @BeforeEach
    void setup() {
        when(aircraftRepository.existsByRegistrationPlate(NEW_REGISTRATION_PLATE)).thenReturn(false);
        when(aircraftRepository.existsByRegistrationPlate(EXISTED_REGISTRATION_PLATE)).thenReturn(true);
    }

    @Test
    void testSuccess() {
        CreateAircraftInputDto aircraft = new CreateAircraftInputDto();
        aircraft.setModel("Boeing");
        aircraft.setCourseSpeedLimit(850);
        aircraft.setPassengerCapacity(189);
        aircraft.setMaxGrossWeight(2000);
        aircraft.setRegistrationPlate(NEW_REGISTRATION_PLATE);

        Assertions.assertDoesNotThrow(() -> aircraftValidator.validateOnCreate(aircraft));
    }

    @Test
    void testExistedRegistrationPlate() {
        CreateAircraftInputDto aircraft = new CreateAircraftInputDto();
        aircraft.setModel("Boeing");
        aircraft.setCourseSpeedLimit(850);
        aircraft.setPassengerCapacity(189);
        aircraft.setMaxGrossWeight(2000);
        aircraft.setRegistrationPlate(EXISTED_REGISTRATION_PLATE);

        ValidationException e = Assertions.assertThrows(ValidationException.class, () -> aircraftValidator.validateOnCreate(aircraft));
        Assertions.assertEquals(String.
                        format("Aircraft data is invalid[Aircraft with registration plate '%s' already exists]", EXISTED_REGISTRATION_PLATE),
                e.getMessage());
    }


    @Test
    void testAllNull() {
        CreateAircraftInputDto aircraft = new CreateAircraftInputDto();
        aircraft.setRegistrationPlate(null);
        aircraft.setModel(null);
        aircraft.setMaxGrossWeight(null);
        aircraft.setCourseSpeedLimit(null);
        aircraft.setPassengerCapacity(null);

        ValidationException exception = Assertions.assertThrows(ValidationException.class,
                () -> aircraftValidator.validateOnCreate(aircraft));
        Assertions.assertEquals(
                "Aircraft data is invalid" +
                        "[Registration plate should be not null, " +
                        "Max gross weight should be not null, " +
                        "Passenger capacity should be not null, " +
                        "Course speed limit should be not null, " +
                        "Aircraft model should be not null]",
                exception.getMessage()
        );
    }

    @Test
    void testUpdateExisted() {
        ValidationException exception = Assertions.assertThrows(ValidationException.class,
                () -> aircraftValidator.validateOnUpdate(EXISTED_REGISTRATION_PLATE));
        Assertions.assertEquals(String
                        .format("Aircraft data is invalid[Aircraft with registration plate '%s' already exists]", EXISTED_REGISTRATION_PLATE),
                exception.getMessage());
    }

    @Test
    void testUpdateSuccess() {
        Assertions.assertDoesNotThrow(() -> aircraftValidator.validateOnUpdate(NEW_REGISTRATION_PLATE));
    }
}