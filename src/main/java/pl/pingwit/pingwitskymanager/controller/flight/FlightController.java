package pl.pingwit.pingwitskymanager.controller.flight;

import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.flight.FlightService;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDto> findAllFlights(){
        return flightService.findAll();
    }

    // сейчас получает так, что при создании рейса обязательно создавать и команду (Crew) и нет возможности использовать
    // уже существующую. в таком случае, отпадает необходимость в pl.pingwit.pingwitskymanager.controller.crew.CrewController.createCrew
    // но и становится невозможным использовать одну и ту же команду на нескольких рейсах
    // если так и задумано - ок. если нет - используй в CreateFlightInputDto айдишник команды
    @PostMapping
    public Integer createFlight(@RequestBody CreateFlightInputDto flightInputDto) {
        return flightService.createFlight(flightInputDto);
    }
}
