package pl.pingwit.pingwitskymanager.controller.employee;

import java.util.Objects;

public class CreateEmployeeInputDto {
    private String name;
    private String surname;
    private String email;
    private EmployeeTypeDto employeeType;

    public CreateEmployeeInputDto() {
    }

    public CreateEmployeeInputDto(String name, String surname, String email, EmployeeTypeDto employeeTypeDto) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.employeeType = employeeTypeDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setEmployeeType(EmployeeTypeDto employeeTypeDto) {
        this.employeeType = employeeTypeDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateEmployeeInputDto that = (CreateEmployeeInputDto) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && employeeType == that.employeeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, employeeType);
    }
}
