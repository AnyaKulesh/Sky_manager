package pl.pingwit.pingwitskymanager.controller.employee;

import java.util.Objects;

public class EmployeeDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private EmployeeTypeDto type;

    public EmployeeDto(Integer id, String name, String surname, String email, EmployeeTypeDto type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
    }

    public EmployeeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public EmployeeTypeDto getType() {
        return type;
    }

    public void setType(EmployeeTypeDto type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, type);
    }
}
