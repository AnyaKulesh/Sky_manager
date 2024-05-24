package pl.pingwit.pingwitskymanager.controller.flight;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.flight.FlightService;

import java.util.List;

@Tag(name = "Flight API")
@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDto> findAllFlights() {
        return flightService.findAll();
    }

    @PostMapping
    public Integer createFlight(@RequestBody CreateFlightInputDto flightInputDto) {
        return flightService.createFlight(flightInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable(name = "id") Integer id) {
        flightService.deleteFlight(id);
    }

}
