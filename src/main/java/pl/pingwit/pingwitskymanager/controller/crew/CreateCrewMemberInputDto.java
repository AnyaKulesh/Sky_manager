package pl.pingwit.pingwitskymanager.controller.crew;

public class CreateCrewMemberInputDto {
    private String email;
    private Boolean isCaptain;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Boolean isCaptain) {
        this.isCaptain = isCaptain;
    }
}