package pl.pingwit.pingwitskymanager.service.direction;

import pl.pingwit.pingwitskymanager.controller.direction.CreateDirectionInputDto;
import pl.pingwit.pingwitskymanager.controller.direction.DirectionDto;

import java.util.List;

public interface DirectionService {
    List<DirectionDto> findAllDirections();

    DirectionDto findById(Integer id);

    Integer createDirection(CreateDirectionInputDto directionInputDto);
}
