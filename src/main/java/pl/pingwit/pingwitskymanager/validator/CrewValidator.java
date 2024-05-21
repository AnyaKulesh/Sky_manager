package pl.pingwit.pingwitskymanager.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewMemberInputDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.ValidationException;
import pl.pingwit.pingwitskymanager.repository.crew.Crew;
import pl.pingwit.pingwitskymanager.repository.crew.CrewMember;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeType;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrewValidator implements Validator {

    private static final int MIN_NUMBER_OF_PILOTS = 2;

    public void validateCrewInput(CreateCrewInputDto crewInputDto) {
        List<String> errors = new ArrayList<>();

        validateBaseCity(crewInputDto, errors);
        validateCrewCompleteness(crewInputDto, errors);

        if (!errors.isEmpty()) {
            throw new ValidationException("Crew data is not valid: ", errors);
        }
    }

    public void validateNumberOfPilots(Crew crew) {
        int pilotCounter = 0;
        for (CrewMember crewMember : crew.getCrewMembers()) {
            if (EmployeeType.PILOT.equals(crewMember.getEmployee().getType())) {
                pilotCounter++;
            }
        }
        if (pilotCounter < MIN_NUMBER_OF_PILOTS) {
            throw new ValidationException("Number of pilots should be not less than " + MIN_NUMBER_OF_PILOTS);
        }
    }

    private void validateCrewCompleteness(CreateCrewInputDto crewInputDto, List<String> errors) {
        for (CreateCrewMemberInputDto crewMemberInputDto : crewInputDto.getCrewMembers()) {
            if (!EMAIL_PATTERN.matcher(crewMemberInputDto.getEmail()).matches()) {
                errors.add(EMAIL_PATTERN_ERROR + crewMemberInputDto.getEmail());
            }
        }
    }

    private void validateBaseCity(CreateCrewInputDto crewInputDto, List<String> errors) {
        if (StringUtils.isBlank(crewInputDto.getBaseCity().trim())) {
            errors.add(BLANK_NAME_ERROR);
        }
        if (!ONLY_LETTERS_PATTERN.matcher(crewInputDto.getBaseCity()).matches()) {
            errors.add(ONLY_LETTERS_NAME_ERROR);
        }
    }
}
