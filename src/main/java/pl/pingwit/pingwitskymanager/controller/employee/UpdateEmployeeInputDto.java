package pl.pingwit.pingwitskymanager.controller.employee;

import java.util.Objects;

public class UpdateEmployeeInputDto {
    private String surname;
    private String email;
    private EmployeeTypeDto employeeType;

    public UpdateEmployeeInputDto() {
    }

    public UpdateEmployeeInputDto(String surname, String email, EmployeeTypeDto employeeType) {
        this.surname = surname;
        this.email = email;
        this.employeeType = employeeType;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeTypeDto getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeTypeDto employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEmployeeInputDto that = (UpdateEmployeeInputDto) o;
        return Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && employeeType == that.employeeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, email, employeeType);
    }
}
