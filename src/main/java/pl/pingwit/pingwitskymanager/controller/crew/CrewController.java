package pl.pingwit.pingwitskymanager.controller.crew;

import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.crew.CrewService;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewController {

    private final CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping
    public List<CrewDto> listCrews() {
        return crewService.findAllCrews();
    }

    @PostMapping
    public Integer createCrew(@RequestBody CreateCrewInputDto input) {
        return crewService.createCrew(input);
    }
}
