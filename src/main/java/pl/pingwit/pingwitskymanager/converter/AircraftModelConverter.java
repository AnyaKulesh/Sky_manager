package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.aircraftmodel.AircraftModelDto;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModel;

@Component
public class AircraftModelConverter {
    public AircraftModelDto toDto(AircraftModel aircraftModel) {
        AircraftModelDto aircraftModelDto = new AircraftModelDto();
        aircraftModelDto.setId(aircraftModel.getId());
        aircraftModelDto.setName(aircraftModel.getName());
        return aircraftModelDto;
    }

    public AircraftModel toEntity(String name) {
        AircraftModel aircraftModel = new AircraftModel();
        aircraftModel.setName(name);
        return aircraftModel;
    }
}
