package pl.pingwit.pingwitskymanager.repository.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository <Aircraft,Integer> {
    boolean existsByRegistrationPlate(String registrationPlate);
}
