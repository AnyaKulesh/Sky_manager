package pl.pingwit.pingwitskymanager.service.aircraftmodel;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.aircraftmodel.AircraftModelDto;
import pl.pingwit.pingwitskymanager.converter.AircraftModelConverter;
import pl.pingwit.pingwitskymanager.exceptionhandling.AircraftAlreadyExistsException;
import pl.pingwit.pingwitskymanager.exceptionhandling.AircraftNotFoundException;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModel;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModelRepository;

import java.util.List;

@Service
public class AircraftModelServiceImpl implements AircraftModelService {

    private final AircraftModelRepository aircraftModelRepository;
    private final AircraftModelConverter aircraftModelConverter;

    public AircraftModelServiceImpl(AircraftModelRepository aircraftModelRepository, AircraftModelConverter aircraftModelConverter) {
        this.aircraftModelRepository = aircraftModelRepository;
        this.aircraftModelConverter = aircraftModelConverter;
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
                .orElseThrow(() -> new AircraftNotFoundException(String.format("Aircraft model with id not found: %d", id)));
    }

    @Override
    public Integer createModel(String name) {
        if (aircraftModelRepository.existsByName(name)) { // в дальнейшем, эту логику нужно будет перенести в валидатор
            throw new AircraftAlreadyExistsException(String.format("Aircraft model with name '%s' already exists", name));
        }
        AircraftModel aircraftModel = aircraftModelConverter.toEntity(name);
        return aircraftModelRepository.save(aircraftModel).getId();
    }

    @Override
    public void deleteModel(Integer id) {
        aircraftModelRepository.deleteById(id);
    }
}
