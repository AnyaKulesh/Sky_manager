package pl.pingwit.pingwitskymanager.validator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pingwit.pingwitskymanager.controller.employee.CreateEmployeeInputDto;
import pl.pingwit.pingwitskymanager.controller.employee.EmployeeTypeDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.ValidationException;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeRepository;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeType;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeValidatorTest {
    private static final String NEW_EMAIL = "anya@email.com";
    private static final String EXISTED_EMAIL = "anyak@email.com";
    private static final Employee EXISTED_EMPLOYEE = new Employee(1, "Oleg", "Valera", EXISTED_EMAIL, EmployeeType.CABIN_CREW);
    private static final String INVALID_EMAIL = "123com";

    EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    EmployeeValidator employeeValidator = new EmployeeValidator(employeeRepository);

    @BeforeEach
    void setUp() {
        when(employeeRepository.findByEmail(NEW_EMAIL)).thenReturn(Optional.empty());
        when(employeeRepository.findByEmail(EXISTED_EMAIL)).thenReturn(Optional.of(EXISTED_EMPLOYEE));
    }

    @Test
    void testSuccess() {
        CreateEmployeeInputDto employee = new CreateEmployeeInputDto();
        employee.setName("Hanna");
        employee.setSurname("Ovechkina");
        employee.setEmail(NEW_EMAIL);
        employee.setEmployeeType(EmployeeTypeDto.CABIN_CREW);

        Assertions.assertDoesNotThrow(() -> employeeValidator.validateOnCreate(employee));
    }

    @Test
    void testExistedEmail() {
        CreateEmployeeInputDto employee = new CreateEmployeeInputDto();
        employee.setName("Hanna");
        employee.setSurname("Ovechkina");
        employee.setEmail(EXISTED_EMAIL);
        employee.setEmployeeType(EmployeeTypeDto.CABIN_CREW);

        ValidationException exception = Assertions.assertThrows(ValidationException.class,
                () -> employeeValidator.validateOnCreate(employee));
        Assertions.assertEquals(String
                        .format("Employee data is invalid: " +
                                "[Email %s already exists]", EXISTED_EMAIL),
                exception.getMessage());
    }

    @Test
    void testInvalidParameters() {
        CreateEmployeeInputDto employee = new CreateEmployeeInputDto();
        employee.setName(" ");
        employee.setSurname("  ");
        employee.setEmail(INVALID_EMAIL);
        employee.setEmployeeType(EmployeeTypeDto.CABIN_CREW);

        ValidationException exception = Assertions.assertThrows(ValidationException.class,
                () -> employeeValidator.validateOnCreate(employee));
        Assertions.assertEquals(String.format("Employee data is invalid: " +
                        "[Name is blank, Surname is blank, " +
                        "Name should have only letters, " +
                        "Surname should have only letters, " +
                        "Email is invalid: %s]", INVALID_EMAIL),
                exception.getMessage());
    }


}