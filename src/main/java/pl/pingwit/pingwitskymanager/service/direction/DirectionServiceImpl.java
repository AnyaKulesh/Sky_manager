package pl.pingwit.pingwitskymanager.service.direction;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.direction.CreateDirectionInputDto;
import pl.pingwit.pingwitskymanager.controller.direction.DirectionDto;
import pl.pingwit.pingwitskymanager.converter.DirectionConverter;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.direction.Direction;
import pl.pingwit.pingwitskymanager.repository.direction.DirectionRepository;

import java.util.List;

@Service
public class DirectionServiceImpl implements DirectionService {
    private final DirectionRepository directionRepository;
    private final DirectionConverter directionConverter;

    public DirectionServiceImpl(DirectionRepository directionRepository, DirectionConverter directionConverter) {
        this.directionRepository = directionRepository;
        this.directionConverter = directionConverter;
    }

    @Override
    public List<DirectionDto> findAllDirections() {
        return directionRepository.findAll().stream()
                .map(directionConverter::directionToDto)
                .toList();
    }

    @Override
    public DirectionDto findById(Integer id) {
        return directionRepository.findById(id)
                .map(directionConverter::directionToDto)
                .orElseThrow(() -> new NotFoundException(String.format("Direction with id %d not found", id)));
    }

    @Override
    public Integer createDirection(CreateDirectionInputDto directionInputDto) {
        Direction direction = directionConverter.directionToEntity(directionInputDto);
        return directionRepository.save(direction).getId();
    }
}
