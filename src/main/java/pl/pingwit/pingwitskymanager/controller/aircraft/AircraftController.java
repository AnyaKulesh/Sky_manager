package pl.pingwit.pingwitskymanager.controller.aircraft;

import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.aircraft.AircraftService;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<AircraftDto> findAllAircraft() {
        return aircraftService.getAllAircraft();
    }

    @GetMapping("/{id}")
    public AircraftDto findAircraftById(@PathVariable Integer id) {
        return aircraftService.getAircraftById(id);
    }

    @PostMapping
    public Integer createAircraft(@RequestBody CreateAircraftInputDto aircraftInputDto) {
        return aircraftService.createAircraft(aircraftInputDto);
    }

    @PutMapping("/{id}")
    public void updateAircraft(@RequestParam String registrationPlate, @PathVariable Integer id) {
        // здесь нужно работать не через @RequestParam, а через @RequestBody. Даже если сейчас обновить можно только рег номер.
        // во-первых, в будущем количество параметров для обновления может увеличиться. во-вторых, REST предполагает использование тела запроса в PUT запросах
        aircraftService.updateAircraft(id, registrationPlate);
    }
}
