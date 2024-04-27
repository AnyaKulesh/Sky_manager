package pl.pingwit.pingwitskymanager.repository.direction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectionRepository extends JpaRepository <Direction,Integer> {

    boolean existsByFromAndTo(String from, String to);

    Optional<Direction> findByFromAndTo(String from, String to);
}
