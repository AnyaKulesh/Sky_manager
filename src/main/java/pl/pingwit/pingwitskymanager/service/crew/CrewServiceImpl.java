package pl.pingwit.pingwitskymanager.service.crew;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewDto;
import pl.pingwit.pingwitskymanager.converter.CrewConverter;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.crew.Crew;
import pl.pingwit.pingwitskymanager.repository.crew.CrewRepository;
import pl.pingwit.pingwitskymanager.validator.CrewValidator;

import java.util.List;

@Service
public class CrewServiceImpl implements CrewService {
    private final CrewConverter crewConverter;
    private final CrewRepository crewRepository;
    private final CrewValidator crewValidator;

    public CrewServiceImpl(CrewConverter crewConverter, CrewRepository crewRepository, CrewValidator crewValidator) {
        this.crewConverter = crewConverter;
        this.crewRepository = crewRepository;
        this.crewValidator = crewValidator;
    }


    @Override
    public List<CrewDto> findAllCrews() {
        return crewRepository.findAll().stream()
                .map(crewConverter::toDto)
                .toList();
    }

    @Override
    public CrewDto findById(Integer id) {
        return crewRepository.findById(id)
                .map(crewConverter::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Crew with id %d not found", id)));
    }

    @Override
    public Integer createCrew(CreateCrewInputDto crewInputDto) {
        crewValidator.validateCrewInput(crewInputDto);
        Crew crew = crewConverter.toEntity(crewInputDto);
        crewValidator.validateCrew(crew);
        return crewRepository.save(crew).getId();
    }
}
