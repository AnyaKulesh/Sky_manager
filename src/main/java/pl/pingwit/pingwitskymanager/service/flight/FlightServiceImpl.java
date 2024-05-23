package pl.pingwit.pingwitskymanager.service.flight;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.flight.CreateFlightInputDto;
import pl.pingwit.pingwitskymanager.controller.flight.FlightDto;
import pl.pingwit.pingwitskymanager.converter.FlightConverter;
import pl.pingwit.pingwitskymanager.repository.flight.FlightRepository;
import pl.pingwit.pingwitskymanager.validator.CrewValidator;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
    private final FlightRepository flightRepository;
    private final FlightConverter flightConverter;
    private final CrewValidator crewValidator;

    public FlightServiceImpl(FlightRepository flightRepository, FlightConverter flightConverter, CrewValidator crewValidator) {
        this.flightRepository = flightRepository;
        this.flightConverter = flightConverter;
        this.crewValidator = crewValidator;
    }

    @Override
    public List<FlightDto> findAll() {
        return flightRepository.findAll().stream()
                .map(flightConverter::toDto)
                .toList();
    }

    @Override
    public Integer createFlight(CreateFlightInputDto flightInputDto) {
        crewValidator.validateCrewInput(flightInputDto.getCrew());
        //  также можно создать flightValidator и проверить необходимы поля.
        return flightRepository.save(flightConverter.flightToEntity(flightInputDto)).getId();
    }

    @Override
    public void deleteFlight(Integer id) {
        flightRepository.deleteById(id);
    }
}
