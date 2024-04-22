package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.direction.CreateDirectionInputDto;
import pl.pingwit.pingwitskymanager.controller.direction.DirectionDto;
import pl.pingwit.pingwitskymanager.repository.direction.Direction;
import pl.pingwit.pingwitskymanager.validator.DirectionValidator;

@Component
public class DirectionConverter {

    private final DirectionValidator directionValidator;

    public DirectionConverter(DirectionValidator directionValidator) {
        this.directionValidator = directionValidator;
    }

    public DirectionDto directionToDto(Direction direction) {
        DirectionDto directionDto = new DirectionDto();
        directionDto.setId(direction.getId());
        directionDto.setFrom(direction.getFrom());
        directionDto.setTo(direction.getTo());
        return directionDto;
    }

    public Direction directionToEntity(CreateDirectionInputDto directionInputDto) {
        directionValidator.validateOnConvert(directionInputDto);

        Direction direction = new Direction();
        direction.setFrom(directionInputDto.getFrom().trim().toUpperCase());
        direction.setTo(directionInputDto.getTo().trim().toUpperCase());
        return direction;
    }
}
