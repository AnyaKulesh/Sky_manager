package pl.pingwit.pingwitskymanager.controller.direction;

import java.util.Objects;

public class CreateDirectionInputDto {
    private String from;
    private String to;

    public CreateDirectionInputDto() {
    }

    public CreateDirectionInputDto(String from, String to) {
        this.from = from;
        this.to = to;
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
        CreateDirectionInputDto that = (CreateDirectionInputDto) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash( from, to);
    }
}
