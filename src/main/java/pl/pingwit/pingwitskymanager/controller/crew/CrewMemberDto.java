package pl.pingwit.pingwitskymanager.controller.crew;

import pl.pingwit.pingwitskymanager.controller.employee.EmployeeDto;

public class CrewMemberDto {
    private EmployeeDto employee;
    private Boolean isCaptain;

    public CrewMemberDto(EmployeeDto employee, Boolean isCaptain) {
        this.employee = employee;
        this.isCaptain = isCaptain;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public Boolean getCaptain() {
        return isCaptain;
    }

    public void setCaptain(Boolean captain) {
        isCaptain = captain;
    }
}
