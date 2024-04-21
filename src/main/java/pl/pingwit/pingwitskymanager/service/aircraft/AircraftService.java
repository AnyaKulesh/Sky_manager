package pl.pingwit.pingwitskymanager.service.aircraft;

import pl.pingwit.pingwitskymanager.controller.aircraft.AircraftDto;
import pl.pingwit.pingwitskymanager.controller.aircraft.CreateAircraftInputDto;

import java.util.List;

public interface AircraftService {

    List<AircraftDto> getAllAircraft();

    AircraftDto getAircraftById(Integer id);

    Integer createAircraft(CreateAircraftInputDto aircraftInputDto);

    void updateAircraft(Integer id, String registrationPlate);
}
