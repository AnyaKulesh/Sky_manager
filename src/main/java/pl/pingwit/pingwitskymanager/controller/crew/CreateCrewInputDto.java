package pl.pingwit.pingwitskymanager.controller.crew;

import java.util.List;

public class CreateCrewInputDto {

    private String baseCity;
    private List<CreateCrewMemberInputDto> crewMembers;

    public String getBaseCity() {
        return baseCity;
    }

    public void setBaseCity(String baseCity) {
        this.baseCity = baseCity;
    }

    public List<CreateCrewMemberInputDto> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CreateCrewMemberInputDto> crewMembers) {
        this.crewMembers = crewMembers;
    }
}
