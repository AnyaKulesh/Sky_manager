package pl.pingwit.pingwitskymanager.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.employee.CreateEmployeeInputDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.ValidationException;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.pingwit.pingwitskymanager.validator.ValidationUtilConstants.*;

@Component
public class EmployeeValidator implements Validator {
    private final EmployeeRepository employeeRepository;

    public EmployeeValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void validateOnCreate(CreateEmployeeInputDto createEmployeeInputDto) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isBlank(createEmployeeInputDto.getName().trim())) {
            errors.add(BLANK_NAME_ERROR.toString());
        }
        if (StringUtils.isBlank(createEmployeeInputDto.getSurname().trim())) {
            errors.add(BLANK_SURNAME_ERROR.toString());
        }
        if (!ONLY_LETTERS_PATTERN.matcher(createEmployeeInputDto.getName()).matches()) {
            errors.add(ONLY_LETTERS_NAME_ERROR.toString());
        }
        if (!ONLY_LETTERS_PATTERN.matcher(createEmployeeInputDto.getName()).matches()) {
            errors.add(ONLY_LETTERS_SURNAME_ERROR.toString());
        }

        if (!EMAIL_PATTERN.matcher(createEmployeeInputDto.getEmail()).matches()) {
            errors.add(EMAIL_PATTERN_ERROR + createEmployeeInputDto.getEmail());
        }

        if (!validateUniqueEmail(createEmployeeInputDto.getEmail())) {
            errors.add("Email " + createEmployeeInputDto.getEmail() + " already exists");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("Employee data is invalid: ", errors);
        }
    }

    private boolean validateUniqueEmail(String email) {
        Optional<Employee> byEmail = employeeRepository.findByEmail(email);
        return byEmail.isEmpty();
    }
}


