package pl.pingwit.pingwitskymanager.service.flight;

import pl.pingwit.pingwitskymanager.controller.flight.CreateFlightInputDto;
import pl.pingwit.pingwitskymanager.controller.flight.FlightDto;

import java.util.List;

public interface FlightService {
    List<FlightDto> findAll();
    Integer createFlight(CreateFlightInputDto flightInputDto);

    void deleteFlight(Integer id);
}
