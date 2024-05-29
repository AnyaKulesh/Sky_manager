package pl.pingwit.pingwitskymanager.controller.crew;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.crew.CrewService;

import java.util.List;

import static pl.pingwit.pingwitskymanager.controller.UrlUtils.CREW_URL;

@Tag(name = "Crew API")
@RestController
@RequestMapping(CREW_URL)
public class CrewController {
    private final CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping
    public List<CrewDto> findAllCrew(){
        return crewService.findAllCrews();
    }

    @GetMapping("/{id}")
    public CrewDto findCrewById(@PathVariable Integer id){
        return crewService.findById(id);
    }

    @PostMapping
    public Integer createCrew(@RequestBody CreateCrewInputDto crewInputDto){
        return crewService.createCrew(crewInputDto);
    }

}
