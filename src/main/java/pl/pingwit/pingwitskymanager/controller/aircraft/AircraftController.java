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
    public void updateAircraft(@RequestBody String registrationPlate, @PathVariable Integer id) {
        aircraftService.updateAircraft(id, registrationPlate);
    }
}
