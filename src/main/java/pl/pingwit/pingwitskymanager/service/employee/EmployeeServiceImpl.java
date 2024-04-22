package pl.pingwit.pingwitskymanager.service.employee;

import org.springframework.stereotype.Service;
import pl.pingwit.pingwitskymanager.controller.employee.CreateEmployeeInputDto;
import pl.pingwit.pingwitskymanager.controller.employee.EmployeeDto;
import pl.pingwit.pingwitskymanager.controller.employee.UpdateEmployeeInputDto;
import pl.pingwit.pingwitskymanager.converter.EmployeeConverter;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeRepository;
import pl.pingwit.pingwitskymanager.validator.EmployeeValidator;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final EmployeeValidator employeeValidator;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter, EmployeeValidator employeeValidator) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
        this.employeeValidator = employeeValidator;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeConverter::employeeToDto)
                .toList();
    }

    @Override
    public EmployeeDto getById(Integer id) {
        return employeeRepository.findById(id).map(employeeConverter::employeeToDto)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with id %d not found", id)));
    }

    @Override
    public Integer createEmployee(CreateEmployeeInputDto createEmployeeInputDto) {
        employeeValidator.validateOnCreate(createEmployeeInputDto);
        Employee employee = employeeConverter.createEmployeeToEntity(createEmployeeInputDto);
        return employeeRepository.save(employee).getId();
    }

    @Override
    public void updateEmployee(UpdateEmployeeInputDto employeeInputDto, Integer id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        Employee employeeToUpdate = employeeConverter.updateEmployeeToEntity(employeeInputDto);
        employeeToUpdate.setId(existingEmployee.getId());
        employeeToUpdate.setName(existingEmployee.getName());

        employeeRepository.save(employeeToUpdate);
    }
}
