package pl.pingwit.pingwitskymanager.service.crew;

import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewDto;

import java.util.List;

public interface CrewService {
    List<CrewDto> findAllCrews();

    Integer createCrew(CreateCrewInputDto input);
}
