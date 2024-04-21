package pl.pingwit.pingwitskymanager.controller.aircraft;

import pl.pingwit.pingwitskymanager.controller.aircraftmodel.AircraftModelDto;

import java.util.Objects;

public class AircraftDto {
    private Integer id;
    private String registrationPlate;
    private AircraftModelDto model;
    private Integer passengerCapacity;
    private Integer maxGrossWeight;
    private Integer courseSpeedLimit;

    public AircraftDto() {
    }

    public AircraftDto(Integer id, String registrationPlate, AircraftModelDto model, Integer passengerCapacity, Integer maxGrossWeight, Integer courseSpeedLimit) {
        this.id = id;
        this.registrationPlate = registrationPlate;
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.maxGrossWeight = maxGrossWeight;
        this.courseSpeedLimit = courseSpeedLimit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public AircraftModelDto getModel() {
        return model;
    }

    public void setModel(AircraftModelDto model) {
        this.model = model;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Integer getMaxGrossWeight() {
        return maxGrossWeight;
    }

    public void setMaxGrossWeight(Integer maxGrossWeight) {
        this.maxGrossWeight = maxGrossWeight;
    }

    public Integer getCourseSpeedLimit() {
        return courseSpeedLimit;
    }

    public void setCourseSpeedLimit(Integer courseSpeedLimit) {
        this.courseSpeedLimit = courseSpeedLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AircraftDto that = (AircraftDto) o;
        return Objects.equals(id, that.id) && Objects.equals(registrationPlate, that.registrationPlate) && Objects.equals(model, that.model) && Objects.equals(passengerCapacity, that.passengerCapacity) && Objects.equals(maxGrossWeight, that.maxGrossWeight) && Objects.equals(courseSpeedLimit, that.courseSpeedLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationPlate, model, passengerCapacity, maxGrossWeight, courseSpeedLimit);
    }
}
