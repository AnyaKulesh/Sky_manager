package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewMemberInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewMemberDto;
import pl.pingwit.pingwitskymanager.repository.crew.Crew;
import pl.pingwit.pingwitskymanager.repository.crew.CrewMember;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeRepository;

@Component
public class CrewConverter {

    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    public CrewConverter(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
    }

    public Crew toEntity(CreateCrewInputDto input) {
        Crew crew = new Crew();
        crew.setBaseCity(input.getBaseCity());
        crew.setCrewMembers(input.getCrewMembers().stream()
                .map(crewMemberInput -> toCrewMemberEntity(crewMemberInput, crew))
                .toList()
        );
        return crew;
    }

    private CrewMember toCrewMemberEntity(CreateCrewMemberInputDto input, Crew crew) {
        CrewMember crewMember = new CrewMember();
        Employee employee = employeeRepository.findById(input.getEmployeeId()).orElseThrow();
        crewMember.setEmployee(employee);
        crewMember.setCaptain(input.getCaptain());
        crewMember.setCrew(crew);
        return crewMember;
    }

    public CrewDto toDto(Crew crew) {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(crew.getId());
        crewDto.setBaseCity(crew.getBaseCity());
        crewDto.setCrewMembers(crew.getCrewMembers().stream()
                .map(crewMember -> new CrewMemberDto(employeeConverter.employeeToDto(crewMember.getEmployee()), crewMember.getCaptain()))
                .toList());
        return crewDto;
    }

}
