package pl.pingwit.pingwitskymanager.controller.crew;

import java.util.List;

public class CrewDto {

    private Integer id;
    private String baseCity;
    private List<CrewMemberDto> crewMembers;

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

    public List<CrewMemberDto> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CrewMemberDto> crewMembers) {
        this.crewMembers = crewMembers;
    }
}
