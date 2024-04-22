package pl.pingwit.pingwitskymanager.service.employee;

import pl.pingwit.pingwitskymanager.controller.employee.CreateEmployeeInputDto;
import pl.pingwit.pingwitskymanager.controller.employee.EmployeeDto;
import pl.pingwit.pingwitskymanager.controller.employee.UpdateEmployeeInputDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getById(Integer id);
    Integer createEmployee(CreateEmployeeInputDto createEmployeeInputDto);
    void updateEmployee(UpdateEmployeeInputDto employeeInputDto,Integer id);
}
