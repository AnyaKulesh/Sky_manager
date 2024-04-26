package pl.pingwit.pingwitskymanager.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewMemberInputDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.ValidationException;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeType;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrewValidator implements Validator {

    public void validateCrewInput(CreateCrewInputDto crewInputDto) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isBlank(crewInputDto.getBaseCity().trim())) {
            errors.add(BLANK_NAME_ERROR);
        }

        if (!ONLY_LETTERS_PATTERN.matcher(crewInputDto.getBaseCity()).matches()) {
            errors.add(ONLY_LETTERS_NAME_ERROR);
        }

        int captainCounter = 0;
        for (CreateCrewMemberInputDto crewMemberInputDto : crewInputDto.getCrewMembers()) {
            if (!EMAIL_PATTERN.matcher(crewMemberInputDto.getEmail()).matches()) {
                errors.add(EMAIL_PATTERN_ERROR + crewMemberInputDto.getEmail());
            }
            if (crewMemberInputDto.getIsCaptain()) {
                captainCounter++;
            }
        }

        if (captainCounter < 2) {
            errors.add("Number of pilots should be not less than 2");
        }

        if(!errors.isEmpty()){
            throw new ValidationException("Crew data is not valid: ", errors);
        }
    }

    public void validateEmployeeType(Employee employee, boolean isCaptain) {
        if ((employee.getType() == EmployeeType.CABIN_CREW) == isCaptain) {
            throw new ValidationException(
                    String.format("Employee '%s %s %s' can only be a '%s'",
                            employee.getName(),
                            employee.getSurname(),
                            employee.getEmail(),
                            employee.getType()
                    ));
        }
    }
}
