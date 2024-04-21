package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.aircraft.AircraftDto;
import pl.pingwit.pingwitskymanager.controller.aircraft.CreateAircraftInputDto;
import pl.pingwit.pingwitskymanager.repository.aircraft.Aircraft;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModel;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModelRepository;

@Component
public class AircraftConverter {

    private final AircraftModelConverter aircraftModelConverter;
    private final AircraftModelRepository aircraftModelRepository;

    public AircraftConverter(AircraftModelConverter aircraftModelConverter,
                             AircraftModelRepository aircraftModelRepository) {
        this.aircraftModelConverter = aircraftModelConverter;
        this.aircraftModelRepository = aircraftModelRepository;
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


        aircraft.setModel(aircraftModelRepository.findByName(aircraftInputDto.getModel()) // ищем модель по имени и если нашли - стеаем ее
                .orElse(new AircraftModel(aircraftInputDto.getModel()))); // если не нашли - создаем новую модель
        // (но в базу пока что не сохраняем!! сохранение произойдет, когда в сервисе ты вызовешь метод aircraftRepository.save(aircraft)

        aircraft.setRegistrationPlate(aircraftInputDto.getRegistrationPlate());
        aircraft.setPassengerCapacity(aircraftInputDto.getPassengerCapacity());
        aircraft.setMaxGrossWeight(aircraftInputDto.getMaxGrossWeight());
        aircraft.setCourseSpeedLimit(aircraftInputDto.getCourseSpeedLimit());
        return aircraft;
    }


}
