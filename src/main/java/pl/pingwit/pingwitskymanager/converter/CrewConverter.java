package pl.pingwit.pingwitskymanager.converter;

import org.springframework.stereotype.Component;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CreateCrewMemberInputDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewDto;
import pl.pingwit.pingwitskymanager.controller.crew.CrewMemberDto;
import pl.pingwit.pingwitskymanager.exceptionhandling.NotFoundException;
import pl.pingwit.pingwitskymanager.repository.crew.Crew;
import pl.pingwit.pingwitskymanager.repository.crew.CrewMember;
import pl.pingwit.pingwitskymanager.repository.employee.Employee;
import pl.pingwit.pingwitskymanager.repository.employee.EmployeeRepository;
import pl.pingwit.pingwitskymanager.validator.CrewValidator;


@Component
public class CrewConverter {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    // crewValidator здесь быть не должно. в конвертерах не должно быть валидации
    private final CrewValidator crewValidator;

    public CrewConverter(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter, CrewValidator crewValidator) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
        this.crewValidator = crewValidator;
    }

    public CrewDto toDto(Crew crew) {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(crew.getId());
        crewDto.setBaseCity(crew.getBaseCity());
        crewDto.setCrewMembers(crew.getCrewMembers().stream()
                .map(crewMember -> new CrewMemberDto(employeeConverter.employeeToDto(crewMember.getEmployee())))
                .toList());
        return crewDto;
    }

    public Crew toEntity(CreateCrewInputDto crewInputDto) {
        Crew crew = new Crew();
        crew.setBaseCity(crewInputDto.getBaseCity());
        crew.setCrewMembers(crewInputDto.getCrewMembers().stream()
                .map(crewMemberInput -> crewMemberToEntity(crewMemberInput, crew))
                .toList());
        // этот вызов здесь лишний. такая валидация должна быть сделана ранее, в pl.pingwit.pingwitskymanager.validator.CrewValidator.validateCrewInput
        // в конвертерах в принципе не должно быть валидации, это не их ответственность
        crewValidator.validateNumberOfPilots(crew);
        return crew;
    }

    private CrewMember crewMemberToEntity(CreateCrewMemberInputDto crewMemberInputDto, Crew crew) {
        CrewMember crewMember = new CrewMember();
        Employee employee = employeeRepository
                .findByEmail(crewMemberInputDto.getEmail())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Employee with email '%s' not found", crewMemberInputDto.getEmail())
                ));
        crewMember.setEmployee(employee);
        crewMember.setCrew(crew);
        return crewMember;
    }
}
