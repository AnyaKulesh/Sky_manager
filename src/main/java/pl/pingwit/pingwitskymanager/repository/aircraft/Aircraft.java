package pl.pingwit.pingwitskymanager.repository.aircraft;

import jakarta.persistence.*;
import pl.pingwit.pingwitskymanager.repository.aircraftmodel.AircraftModel;

@Entity
@Table(name = "aircraft", schema = "sky_manager")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aircraft_id_gen")
    @SequenceGenerator(name = "aircraft_id_gen", sequenceName = "aircraft_id_seq", schema = "sky_manager", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    @Column(name = "registration_plate")
    private String registrationPlate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_model_id")
    private AircraftModel model;
    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;
    @Column(name = "max_gross_weight")
    private Integer maxGrossWeight;
    @Column(name = "courseSpeedLimit")
    private Integer courseSpeedLimit;

    public Aircraft() {
    }

    public Aircraft(Integer id, String registrationPlate, AircraftModel model, Integer passengerCapacity, Integer maxGrossWeight, Integer courseSpeedLimit) {
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

    public AircraftModel getModel() {
        return model;
    }

    public void setModel(AircraftModel model) {
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
}
