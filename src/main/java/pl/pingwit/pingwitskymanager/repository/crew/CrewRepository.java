package pl.pingwit.pingwitskymanager.repository.crew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew,Integer> {

    @Query("""
            SELECT crew
            FROM Employee as e
            LEFT JOIN CrewMember as clist ON e.id = clist.employee.id
            LEFT JOIN Crew as crew ON crew.id = clist.crew.id
            WHERE  crew.baseCity = ?1
            AND e.email IN ?2
            GROUP BY crew.id
            HAVING count(e.email) = ?3
            """)
    List<Crew> findByBaseCityAndListOfEmails(String baseCity, List<String> emails, int employeeNumber);
}
