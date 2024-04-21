package pl.pingwit.pingwitskymanager.service.aircraftmodel;

import pl.pingwit.pingwitskymanager.controller.aircraftmodel.AircraftModelDto;

import java.util.List;

public interface AircraftModelService {

    List<AircraftModelDto> getAllModels();

    AircraftModelDto getById(Integer id);

    Integer createModel(String name);

    void deleteModel(Integer id);
}
