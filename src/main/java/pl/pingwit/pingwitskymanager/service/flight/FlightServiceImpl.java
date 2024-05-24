package pl.pingwit.pingwitskymanager.service.flight;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.flight.CreateFlightInputDto;
import pl.pingwit.pingwitskymanager.controller.flight.FlightDto;
import pl.pingwit.pingwitskymanager.converter.FlightConverter;
import pl.pingwit.pingwitskymanager.repository.flight.FlightRepository;
import pl.pingwit.pingwitskymanager.validator.FlightValidator;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightConverter flightConverter;
    private final FlightValidator flightValidator;

    public FlightServiceImpl(FlightRepository flightRepository, FlightConverter flightConverter, FlightValidator flightValidator) {
        this.flightRepository = flightRepository;
        this.flightConverter = flightConverter;
        this.flightValidator = flightValidator;
    }

    @Override
    public List<FlightDto> findAll() {
        return flightRepository.findAll().stream()
                .map(flightConverter::toDto)
                .toList();
    }

    @Override
    public Integer createFlight(CreateFlightInputDto flightInputDto) {
        flightValidator.validateOnCreate(flightInputDto);
        return flightRepository.save(flightConverter.flightToEntity(flightInputDto)).getId();
    }

    @Override
    public void deleteFlight(Integer id) {
        flightRepository.deleteById(id);
    }
}
