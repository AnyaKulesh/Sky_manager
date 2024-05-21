package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.flight.CreateFlightInputDto;
import pl.pingwit.pingwitskymanager.controller.flight.FlightDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.aircraft.AircraftRepository;
import pl.pingwit.pingwitskymanager.repository.crew.Crew;
import pl.pingwit.pingwitskymanager.repository.crew.CrewMember;
import pl.pingwit.pingwitskymanager.repository.crew.CrewRepository;
import pl.pingwit.pingwitskymanager.repository.direction.DirectionRepository;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.flight.Flight;

import java.util.Optional;

@Component
public class FlightConverter {
    private final AircraftConverter aircraftConverter;
    private final CrewConverter crewConverter;
    private final DirectionConverter directionConverter;
    private final AircraftRepository aircraftRepository;
    private final DirectionRepository directionRepository;
    private final CrewRepository crewRepository;

    public FlightConverter(AircraftConverter aircraftConverter, CrewConverter crewConverter, DirectionConverter directionConverter, AircraftRepository aircraftRepository, DirectionRepository directionRepository, CrewRepository crewRepository) {
        this.aircraftConverter = aircraftConverter;
        this.crewConverter = crewConverter;
        this.directionConverter = directionConverter;
        this.aircraftRepository = aircraftRepository;
        this.directionRepository = directionRepository;
        this.crewRepository = crewRepository;
    }

    public FlightDto toDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setAircraft(aircraftConverter.toDto(flight.getAircraft()));
        flightDto.setCrew(crewConverter.toDto(flight.getCrew()));
        flightDto.setDirection(directionConverter.directionToDto(flight.getDirection()));
        flightDto.setTakeOffDateTime(flight.getTakeOffDateTime());
        flightDto.setTravelTime(flight.getTravelTime());
        return flightDto;
    }

    public Flight flightToEntity(CreateFlightInputDto flightInputDto) {
        Flight flight = new Flight();
        flight.setAircraft(aircraftRepository.findByRegistrationPlate(flightInputDto.getRegistrationPlate())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Aircraft with registration plate '%s' not found",
                                flightInputDto.getRegistrationPlate())
                )));
        flight.setDirection(directionRepository.findByFromAndTo(flightInputDto.getFrom(), flightInputDto.getTo())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Direction from '%s' to '%s' not found",
                                flightInputDto.getFrom().trim().toUpperCase(),
                                flightInputDto.getTo().trim().toUpperCase())
                )));
        Crew crew = Optional.ofNullable(flightInputDto.getExistingCrewId())
                .flatMap(crewRepository::findById)
                .orElseGet(() -> crewConverter.toEntity(flightInputDto.getCrew()));
        flight.setCrew(crew);

        flight.setTakeOffDateTime(flightInputDto.getTakeOffDateTime());
        flight.setTravelTime(flightInputDto.getTravelTime());

        return flight;
    }
}
