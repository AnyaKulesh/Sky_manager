package pl.pingwit.pingwitskymanager.repository.aircraftmodel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AircraftModelRepository extends JpaRepository <AircraftModel, Integer> {

    Optional<AircraftModel> findByName(String name);
    boolean existsByName(String name);
}
