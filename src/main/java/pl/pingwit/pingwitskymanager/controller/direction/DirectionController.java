package pl.pingwit.pingwitskymanager.controller.direction;

import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.direction.DirectionService;

import java.util.List;

@RestController
@RequestMapping ("/direction")
public class DirectionController {
    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    @GetMapping
    public List<DirectionDto> findAllDirections(){
        return directionService.findAllDirections();
    }

    @GetMapping("/{id}")
    public DirectionDto findById(@PathVariable Integer id){
        return directionService.findById(id);
    }

    @PostMapping
    public Integer createDirection(@RequestBody CreateDirectionInputDto directionInputDto){
        return directionService.createDirection(directionInputDto);
    }
}
