package pl.pingwit.pingwitskymanager.controller.aircraftmodel;

import java.util.Objects;

public class AircraftModelDto {
    private Integer id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AircraftModelDto that = (AircraftModelDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
