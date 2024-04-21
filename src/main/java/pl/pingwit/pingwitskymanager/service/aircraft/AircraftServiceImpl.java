package pl.pingwit.pingwitskymanager.service.aircraft;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.aircraft.AircraftDto;
import pl.pingwit.pingwitskymanager.controller.aircraft.CreateAircraftInputDto;
import pl.pingwit.pingwitskymanager.converter.AircraftConverter;
import pl.pingwit.pingwitskymanager.exceptionhandling.AircraftAlreadyExistsException;
import pl.pingwit.pingwitskymanager.exceptionhandling.AircraftNotFoundException;
import pl.pingwit.pingwitskymanager.repository.aircraft.Aircraft;
import pl.pingwit.pingwitskymanager.repository.aircraft.AircraftRepository;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModelRepository;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftModelRepository aircraftModelRepository;
    private final AircraftConverter aircraftConverter;

    public AircraftServiceImpl(AircraftRepository aircraftRepository, AircraftModelRepository aircraftModelRepository, AircraftConverter aircraftConverter) {
        this.aircraftRepository = aircraftRepository;
        this.aircraftModelRepository = aircraftModelRepository;
        this.aircraftConverter = aircraftConverter;
    }

    @Override
    public List<AircraftDto> getAllAircraft() {
        return aircraftRepository.findAll().stream()
                .map(aircraftConverter::toDto)
                .toList();
    }

    @Override
    public AircraftDto getAircraftById(Integer id) {
        return aircraftRepository.findById(id)
                .map(aircraftConverter::toDto)
                .orElseThrow(() -> new RuntimeException(String.format("Aircraft model with id not found: %s", id)));
    }

    @Override
    public Integer createAircraft(CreateAircraftInputDto aircraftInputDto) {
        Aircraft aircraft = aircraftConverter.toEntity(aircraftInputDto);

        //** Можно ли настроить базу так, чтобы автоматически  создавался aircraftModel, если он отсутствует
        //aircraft.getModel().setId(aircraftModelRepository.save(aircraft.getModel()).getId());
        // можно ли при сохранении существующей модели получить id, а если нет то создать новую


        // Как лучше, вытягивать модель для создания aircraft внутри конвертера или оставить здесь
        aircraft.getModel().setId(aircraftModelRepository.findByName(aircraft.getModel().getName())
                .orElseGet(() -> aircraftModelRepository.save(aircraft.getModel())).getId());

        return aircraftRepository.save(aircraft).getId();
    }

    @Override
    public void updateAircraft(Integer id, String registrationPlate) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new AircraftNotFoundException("Aircraft not found!"));
        if (!registrationPlate.equals(aircraft.getRegistrationPlate())) {
            if (aircraftRepository.existsByRegistrationPlate(registrationPlate)) {
                throw new AircraftAlreadyExistsException(String.format("Aircraft with registration plate '%s' already exists", registrationPlate));
            }
            aircraft.setRegistrationPlate(registrationPlate);
            aircraftRepository.save(aircraft);
        }
    }
}
