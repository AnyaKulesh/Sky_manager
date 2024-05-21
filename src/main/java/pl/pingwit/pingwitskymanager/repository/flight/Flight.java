package pl.pingwit.pingwitskymanager.repository.flight;

import jakarta.persistence.*;
import pl.pingwit.pingwitskymanager.repository.aircraft.Aircraft;
import pl.pingwit.pingwitskymanager.repository.crew.Crew;
import pl.pingwit.pingwitskymanager.repository.direction.Direction;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight", schema = "sky_manager")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_gen")
    @SequenceGenerator(name = "flight_id_gen", sequenceName = "flight_id_seq", schema = "sky_manager", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name =  "crew_id")
    private Crew crew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @Column(name = "takeoff_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime takeOffDateTime;

    @Column(name = "travel_time")
    private Integer travelTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
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
