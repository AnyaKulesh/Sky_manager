package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.employee.CreateEmployeeInputDto;
import pl.pingwit.pingwitskymanager.controller.employee.EmployeeDto;
import pl.pingwit.pingwitskymanager.controller.employee.EmployeeTypeDto;
import pl.pingwit.pingwitskymanager.controller.employee.UpdateEmployeeInputDto;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeType;

@Component
public class EmployeeConverter {
    public EmployeeDto employeeToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setType(EmployeeTypeDto.valueOf(employee.getType().name()));
        return employeeDto;
    }

    public Employee createEmployeeToEntity(CreateEmployeeInputDto createEmployeeInputDto){
        Employee employee = new Employee();
        employee.setName(createEmployeeInputDto.getName());
        employee.setSurname(createEmployeeInputDto.getSurname());
        employee.setEmail(createEmployeeInputDto.getEmail());
        employee.setType(employeeTypeToEntity(createEmployeeInputDto.getEmployeeType()));
        return employee;
    }

    public Employee updateEmployeeToEntity(UpdateEmployeeInputDto updateEmployeeInputDto){
        Employee employee = new Employee();
        employee.setSurname(updateEmployeeInputDto.getSurname());
        employee.setEmail(updateEmployeeInputDto.getEmail());
        employee.setType(employeeTypeToEntity(updateEmployeeInputDto.getEmployeeType()));
        return employee;
    }

    private EmployeeType employeeTypeToEntity(EmployeeTypeDto employeeTypeDto) {
        return EmployeeType.valueOf(employeeTypeDto.name());
    }
}
