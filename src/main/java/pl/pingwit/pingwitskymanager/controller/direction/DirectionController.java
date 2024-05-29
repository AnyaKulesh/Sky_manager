package pl.pingwit.pingwitskymanager.controller.direction;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.direction.DirectionService;

import java.util.List;

import static pl.pingwit.pingwitskymanager.controller.UrlUtils.DIRECTION_URL;

@Tag(name = "Direction API")
@RestController
@RequestMapping (DIRECTION_URL)
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
