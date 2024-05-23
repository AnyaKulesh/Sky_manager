package pl.pingwit.pingwitskymanager.service.aircraft;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.aircraft.AircraftDto;
import pl.pingwit.pingwitskymanager.controller.aircraft.CreateAircraftInputDto;
import pl.pingwit.pingwitskymanager.converter.AircraftConverter;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.aircraft.Aircraft;
import pl.pingwit.pingwitskymanager.repository.aircraft.AircraftRepository;
import pl.pingwit.pingwitskymanager.validator.AircraftValidator;

import java.util.List;

@Transactional
@Service
public class AircraftServiceImpl implements AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftConverter aircraftConverter;
    private final AircraftValidator aircraftValidator;

    public AircraftServiceImpl(AircraftRepository aircraftRepository, AircraftConverter aircraftConverter, AircraftValidator aircraftValidator) {
        this.aircraftRepository = aircraftRepository;
        this.aircraftConverter = aircraftConverter;
        this.aircraftValidator = aircraftValidator;
    }

    @Override
    public List<AircraftDto> getAllAircraft() {
        return aircraftRepository.findAll().stream()
                .map(aircraftConverter::toDto)
                .toList();
    }

    @Override
    public AircraftDto getAircraftById(Integer id) {
        return aircraftRepository.findById(id)
                .map(aircraftConverter::toDto)
                .orElseThrow(() -> new RuntimeException(String.format("Aircraft model with id not found: %s", id)));
    }

    @Override
    public Integer createAircraft(CreateAircraftInputDto aircraftInputDto) {
        aircraftValidator.validateOnCreate(aircraftInputDto);
        Aircraft aircraft = aircraftConverter.toEntity(aircraftInputDto);
        return aircraftRepository.save(aircraft).getId();
    }

    @Override
    public void updateAircraft(Integer id, String registrationPlate) {
        aircraftValidator.validateOnUpdate(registrationPlate);
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aircraft not found!"));
        if (!registrationPlate.equals(aircraft.getRegistrationPlate())) {
            aircraft.setRegistrationPlate(registrationPlate);
            aircraftRepository.save(aircraft);
        }
    }
}

