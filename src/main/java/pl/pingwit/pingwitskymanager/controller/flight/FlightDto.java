package pl.pingwit.pingwitskymanager.controller.flight;

import pl.pingwit.pingwitskymanager.controller.aircraft.AircraftDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewDto;
import pl.pingwit.pingwitskymanager.controller.direction.DirectionDto;

import java.time.LocalDateTime;

public class FlightDto {
    private Integer id;
    private AircraftDto aircraft;
    private CrewDto crew;
    private DirectionDto direction;
    private LocalDateTime takeOffDateTime;
    private Integer travelTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AircraftDto getAircraft() {
        return aircraft;
    }

    public void setAircraft(AircraftDto aircraft) {
        this.aircraft = aircraft;
    }

    public CrewDto getCrew() {
        return crew;
    }

    public void setCrew(CrewDto crew) {
        this.crew = crew;
    }

    public DirectionDto getDirection() {
        return direction;
    }

    public void setDirection(DirectionDto direction) {
        this.direction = direction;
    }

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
}
