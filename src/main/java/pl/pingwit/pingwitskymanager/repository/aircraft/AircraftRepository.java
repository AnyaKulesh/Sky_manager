package pl.pingwit.pingwitskymanager.repository.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AircraftRepository extends JpaRepository <Aircraft,Integer> {
    boolean existsByRegistrationPlate(String registrationPlate);

    Optional<Aircraft> findByRegistrationPlate(String registrationPlate);
}
