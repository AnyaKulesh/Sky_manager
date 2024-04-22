package pl.pingwit.pingwitskymanager.repository.direction;

import jakarta.persistence.*;

@Entity
@Table(name = "direction", schema = "sky_manager")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "direction_id_gen")
    @SequenceGenerator(name = "direction_id_gen", sequenceName = "direction_id_seq", schema = "sky_manager", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "\"from\"")
    private String from;

    @Column(name = "\"to\"")
    private String to;

    public Direction() {
    }

    public Direction(Integer id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
