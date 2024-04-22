package pl.pingwit.pingwitskymanager.validator;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.direction.CreateDirectionInputDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.AlreadyExistsException;
import pl.pingwit.pingwitskymanager.repository.direction.DirectionRepository;

@Component
public class DirectionValidator {
    private final DirectionRepository directionRepository;

    public DirectionValidator(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    public void validateOnConvert(CreateDirectionInputDto directionInputDto){
        String from = directionInputDto.getFrom().trim().toUpperCase();
        String to = directionInputDto.getTo().trim().toUpperCase();
        if (directionRepository.existsByFromAndTo(from, to)) {
            throw new AlreadyExistsException(String.format("Direction from %s to %s already exists!", from, to));
        }
    }
}
