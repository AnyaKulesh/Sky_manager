package pl.pingwit.pingwitskymanager.repository.direction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository <Direction,Integer> {

    boolean existsByFromAndTo(String from, String to);
}
