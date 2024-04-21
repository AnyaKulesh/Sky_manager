package pl.pingwit.pingwitskymanager.repository.aircraftmodel;

import jakarta.persistence.*;

@Entity
@Table(name = "aircraft_model", schema = "sky_manager")
public class AircraftModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aircraft_model_id_gen")
    @SequenceGenerator(name = "aircraft_model_id_gen", sequenceName = "aircraft_model_id_seq", schema = "sky_manager", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public AircraftModel() {
    }

    public AircraftModel(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
