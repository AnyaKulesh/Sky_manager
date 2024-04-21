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
    private final AircraftConverter aircraftConverter;

    public AircraftServiceImpl(AircraftRepository aircraftRepository, AircraftConverter aircraftConverter) {
        this.aircraftRepository = aircraftRepository;
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

    // над всеми методами сервиса (или над самим сервисом) нужно добавить аннотацию @Transactional
    @Override
    public Integer createAircraft(CreateAircraftInputDto aircraftInputDto) {
        Aircraft aircraft = aircraftConverter.toEntity(aircraftInputDto);

        //** Можно ли настроить базу так, чтобы автоматически  создавался aircraftModel, если он отсутствует
        // так сделать можно. соответствующий код смотри в конвертере
        //aircraft.getModel().setId(aircraftModelRepository.save(aircraft.getModel()).getId()); - этот код удалить
        // можно ли при сохранении существующей модели получить id, а если нет то создать новую

        // Как лучше, вытягивать модель для создания aircraft внутри конвертера или оставить здесь - Лучше это сделать внутри конвертера
        /*aircraft.getModel().setId(aircraftModelRepository.findByName(aircraft.getModel().getName())
                .orElseGet(() -> aircraftModelRepository.save(aircraft.getModel())).getId());   - этот код удалить*/
        return aircraftRepository.save(aircraft).getId();
    }

    // над всеми методами сервиса (или над самим сервисом) нужно добавить аннотацию @Transactional
    @Override
    public void updateAircraft(Integer id, String registrationPlate) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new AircraftNotFoundException("Aircraft not found!"));
        if (!registrationPlate.equals(aircraft.getRegistrationPlate())) {
            if (aircraftRepository.existsByRegistrationPlate(registrationPlate)) { // эту проверку в последующем нужно будет вынести в валидатор
                throw new AircraftAlreadyExistsException(String.format("Aircraft with registration plate '%s' already exists", registrationPlate));
            }
            aircraft.setRegistrationPlate(registrationPlate);
            aircraftRepository.save(aircraft);
        }
    }
}
