package pl.pingwit.pingwitskymanager.controller.employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.pingwit.pingwitskymanager.service.employee.EmployeeService;

import java.util.List;

import static pl.pingwit.pingwitskymanager.controller.UrlUtils.EMPLOYEE_URL;

@Tag(name = "Employee API")
@RestController
@RequestMapping(EMPLOYEE_URL)
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> findAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Integer id){
        return employeeService.getById(id);
    }

    @PostMapping
    public Integer createEmployee(@RequestBody CreateEmployeeInputDto employeeInputDto){
        return employeeService.createEmployee(employeeInputDto);
    }
    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody UpdateEmployeeInputDto employeeInputDto, @PathVariable(name = "id") Integer id){
        employeeService.updateEmployee(employeeInputDto, id);
    }
}
