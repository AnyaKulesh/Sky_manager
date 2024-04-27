package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.flight.CreateFlightInputDto;
import pl.pingwit.pingwitskymanager.controller.flight.FlightDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.aircraft.AircraftRepository;
import pl.pingwit.pingwitskymanager.repository.crew.CrewMember;
import pl.pingwit.pingwitskymanager.repository.direction.DirectionRepository;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.flight.Flight;

@Component
public class FlightConverter {
    private final AircraftConverter aircraftConverter;
    private final CrewConverter crewConverter;
    private final DirectionConverter directionConverter;
    private final AircraftRepository aircraftRepository;
    private final DirectionRepository directionRepository;

    public FlightConverter(AircraftConverter aircraftConverter, CrewConverter crewConverter, DirectionConverter directionConverter, AircraftRepository aircraftRepository, DirectionRepository directionRepository) {
        this.aircraftConverter = aircraftConverter;
        this.crewConverter = crewConverter;
        this.directionConverter = directionConverter;
        this.aircraftRepository = aircraftRepository;
        this.directionRepository = directionRepository;
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
        /*
        Нам нужно написать запрос в базу: найти экипаж (crew) по списку членов экипажа (crew members)
        Если не нашли, то сохранить в базу новый экипаж. Можем ли мы это сделать также, как при создании aircraft (aircraft model создавался при создании aircraft)
        flight.setCrew(?);
         */
        flight.setTakeOffDateTime(flightInputDto.getTakeOffDateTime());
        flight.setTravelTime(flightInputDto.getTravelTime());

        return flight;
    }
}
