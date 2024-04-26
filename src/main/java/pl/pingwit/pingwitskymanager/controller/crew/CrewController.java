package pl.pingwit.pingwitskymanager.controller.crew;

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
