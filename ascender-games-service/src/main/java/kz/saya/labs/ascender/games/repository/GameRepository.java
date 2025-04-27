package kz.saya.labs.ascender.games.repository;

import kz.saya.labs.ascender.games.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for managing Game entities.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    
    /**
     * Find all games that support scrims.
     * 
     * @return List of games that support scrims
     */
    List<Game> findByScrimableTrue();
}