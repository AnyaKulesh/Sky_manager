package pl.pingwit.pingwitskymanager.service.aircraftmodel;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.aircraftmodel.AircraftModelDto;
import pl.pingwit.pingwitskymanager.converter.AircraftModelConverter;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModel;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModelRepository;
import pl.pingwit.pingwitskymanager.validator.AircraftModelValidator;

import java.util.List;

@Service
public class AircraftModelServiceImpl implements AircraftModelService {

    private final AircraftModelRepository aircraftModelRepository;
    private final AircraftModelConverter aircraftModelConverter;
    private final AircraftModelValidator aircraftModelValidator;

    public AircraftModelServiceImpl(AircraftModelRepository aircraftModelRepository, AircraftModelConverter aircraftModelConverter, AircraftModelValidator aircraftModelValidator) {
        this.aircraftModelRepository = aircraftModelRepository;
        this.aircraftModelConverter = aircraftModelConverter;
        this.aircraftModelValidator = aircraftModelValidator;
    }

    @Override
    public List<AircraftModelDto> getAllModels() {
        return aircraftModelRepository.findAll().stream()
                .map(aircraftModelConverter::toDto)
                .toList();
    }

    @Override
    public AircraftModelDto getById(Integer id) {
        return aircraftModelRepository.findById(id)
                .map(aircraftModelConverter::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Aircraft model with id not found: %d", id)));
    }

    @Override
    public Integer createModel(String name) {
        aircraftModelValidator.validateOnCreate(name);
        AircraftModel aircraftModel = aircraftModelConverter.toEntity(name);
        return aircraftModelRepository.save(aircraftModel).getId();
    }

    @Override
    public void deleteModel(Integer id) {
        aircraftModelRepository.deleteById(id);
    }
}
