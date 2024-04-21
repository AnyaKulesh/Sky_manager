package pl.pingwit.pingwitskymanager.controller.aircraft;

import java.util.Objects;

public class CreateAircraftInputDto {
    private String registrationPlate;
    private String model;
    private Integer passengerCapacity;
    private Integer maxGrossWeight;
    private Integer courseSpeedLimit;

    public CreateAircraftInputDto() {
    }

    public CreateAircraftInputDto(String registrationPlate, String model, Integer passengerCapacity, Integer maxGrossWeight, Integer courseSpeedLimit) {
        this.registrationPlate = registrationPlate;
        model = model;
        this.passengerCapacity = passengerCapacity;
        this.maxGrossWeight = maxGrossWeight;
        this.courseSpeedLimit = courseSpeedLimit;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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
        CreateAircraftInputDto that = (CreateAircraftInputDto) o;
        return Objects.equals(registrationPlate, that.registrationPlate) && Objects.equals(model, that.model) && Objects.equals(passengerCapacity, that.passengerCapacity) && Objects.equals(maxGrossWeight, that.maxGrossWeight) && Objects.equals(courseSpeedLimit, that.courseSpeedLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationPlate, model, passengerCapacity, maxGrossWeight, courseSpeedLimit);
    }
}
