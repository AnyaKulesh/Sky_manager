package pl.pingwit.pingwitskymanager.controller.direction;

import java.util.Objects;

public class DirectionDto {
    private Integer id;
    private String from;
    private String to;

    public DirectionDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectionDto that = (DirectionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to);
    }
}
