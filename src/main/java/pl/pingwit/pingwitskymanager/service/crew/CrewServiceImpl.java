package pl.pingwit.pingwitskymanager.service.crew;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewDto;
import pl.pingwit.pingwitskymanager.converter.CrewConverter;
import pl.pingwit.pingwitskymanager.repository.crew.CrewRepository;

import java.util.List;

@Service
public class CrewServiceImpl implements CrewService {

    private final CrewConverter crewConverter;
    private final CrewRepository crewRepository;

    public CrewServiceImpl(CrewConverter crewConverter, CrewRepository crewRepository) {
        this.crewConverter = crewConverter;
        this.crewRepository = crewRepository;
    }

    @Override
    public List<CrewDto> findAllCrews() {
        return crewRepository.findAll().stream()
                .map(crewConverter::toDto)
                .toList();
    }

    @Override
    public Integer createCrew(CreateCrewInputDto input) {
        return crewRepository.save(crewConverter.toEntity(input)).getId();
    }
}
