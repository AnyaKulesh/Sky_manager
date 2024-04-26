package pl.pingwit.pingwitskymanager.repository.crew;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "crew", schema = "sky_manager")
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crew_id_gen")
    @SequenceGenerator(name = "crew_id_gen", sequenceName = "crew_id_seq", schema = "sky_manager", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "base_city")
    private String baseCity;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
    private List<CrewMember> crewMembers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseCity() {
        return baseCity;
    }

    public void setBaseCity(String baseCity) {
        this.baseCity = baseCity;
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }
}
