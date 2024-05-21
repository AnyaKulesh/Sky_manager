package pl.pingwit.pingwitskymanager.repository.crew;

import jakarta.persistence.*;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;

@Entity
@Table(name = "crew_list", schema = "sky_manager")
public class CrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crew_list_id_gen")
    @SequenceGenerator(name = "crew_list_id_gen", sequenceName = "crew_list_id_seq", schema = "sky_manager", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
