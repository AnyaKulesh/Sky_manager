package pl.pingwit.pingwitskymanager.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT email FROM Employee")
    Set<String> findAllEmails();

    Optional<Employee> findByEmail(String email);
}
