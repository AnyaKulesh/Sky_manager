package pl.pingwit.pingwitskymanager.controller.aircraftmodel;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.aircraftmodel.AircraftModelService;

import java.util.List;

import static pl.pingwit.pingwitskymanager.controller.UrlUtils.AIRCRAFT_MODEL_URL;

@Tag(name = "Aircraft Model API")
@RestController
@RequestMapping(AIRCRAFT_MODEL_URL)
public class AircraftModelController {
    private final AircraftModelService aircraftModelService;

    public AircraftModelController(AircraftModelService aircraftModelService) {
        this.aircraftModelService = aircraftModelService;
    }

    @GetMapping
    public List<AircraftModelDto> getAircraftModels() {
        return aircraftModelService.getAllModels();
    }

    @GetMapping("/{id}")
    public AircraftModelDto getModelById(@PathVariable Integer id) {
        return aircraftModelService.getById(id);
    }

    @PostMapping
    public Integer createAircraftModel(@RequestParam String name) {
        return aircraftModelService.createModel(name);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable(name = "id") Integer id) {
        aircraftModelService.deleteModel(id);
    }

}
