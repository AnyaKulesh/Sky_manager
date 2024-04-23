package pl.pingwit.pingwitskymanager.controller.crew;

public class CreateCrewMemberInputDto {

    private Integer employeeId;
    private Boolean isCaptain;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getCaptain() {
        return isCaptain;
    }

    public void setCaptain(Boolean captain) {
        isCaptain = captain;
    }
}
