package pl.pingwit.pingwitskymanager.controller.crew;

import pl.pingwit.pingwitskymanager.controller.employee.EmployeeDto;

public class CrewMemberDto {
    private EmployeeDto employee;

    public CrewMemberDto(EmployeeDto employee) {
        this.employee = employee;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }
}
