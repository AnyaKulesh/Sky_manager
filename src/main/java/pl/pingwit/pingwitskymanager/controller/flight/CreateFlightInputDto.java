package pl.pingwit.pingwitskymanager.controller.flight;

import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;

import java.time.LocalDateTime;

public class CreateFlightInputDto {
    private String registrationPlate;
    private String from;
    private String to;
    private CreateCrewInputDto crew;
    private LocalDateTime takeOffDateTime;
    private Integer travelTime;

    public LocalDateTime getTakeOffDateTime() {
        return takeOffDateTime;
    }

    public void setTakeOffDateTime(LocalDateTime takeOffDateTime) {
        this.takeOffDateTime = takeOffDateTime;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public CreateCrewInputDto getCrew() {
        return crew;
    }

    public void setCrew(CreateCrewInputDto crew) {
        this.crew = crew;
    }
}
