package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.aircraft.AircraftDto;
import pl.pingwit.pingwitskymanager.controller.aircraft.CreateAircraftInputDto;
import pl.pingwit.pingwitskymanager.repository.aircraft.Aircraft;

@Component
public class AircraftConverter {

    private final AircraftModelConverter aircraftModelConverter;

    public AircraftConverter(AircraftModelConverter aircraftModelConverter) {
        this.aircraftModelConverter = aircraftModelConverter;
    }

    public AircraftDto toDto(Aircraft aircraft) {
        AircraftDto aircraftDto = new AircraftDto();
        aircraftDto.setId(aircraft.getId());
        aircraftDto.setRegistrationPlate(aircraft.getRegistrationPlate());
        aircraftDto.setModel(aircraftModelConverter.toDto(aircraft.getModel()));
        aircraftDto.setPassengerCapacity(aircraft.getPassengerCapacity());
        aircraftDto.setMaxGrossWeight(aircraft.getMaxGrossWeight());
        aircraftDto.setCourseSpeedLimit(aircraft.getCourseSpeedLimit());
        return aircraftDto;
    }

    public Aircraft toEntity(CreateAircraftInputDto aircraftInputDto) {
        Aircraft aircraft = new Aircraft();
        aircraft.setModel(aircraftModelConverter.toEntity(aircraftInputDto.getModel()));
        aircraft.setRegistrationPlate(aircraftInputDto.getRegistrationPlate());
        aircraft.setPassengerCapacity(aircraftInputDto.getPassengerCapacity());
        aircraft.setMaxGrossWeight(aircraftInputDto.getMaxGrossWeight());
        aircraft.setCourseSpeedLimit(aircraftInputDto.getCourseSpeedLimit());
        return aircraft;
    }


}
