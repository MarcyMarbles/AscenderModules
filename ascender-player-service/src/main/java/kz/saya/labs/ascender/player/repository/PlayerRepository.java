package kz.saya.labs.ascender.player.repository;

import kz.saya.labs.ascender.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Player entities.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    
    /**
     * Find a player by user ID.
     *
     * @param userId User ID
     * @return Player if found
     */
    Optional<Player> findByUserId(UUID userId);
    
    /**
     * Find players by skill level.
     *
     * @param skillLevel Skill level
     * @return List of players with the specified skill level
     */
    List<Player> findBySkillLevel(String skillLevel);
    
    /**
     * Find players looking for a team.
     *
     * @return List of players looking for a team
     */
    List<Player> findByLookingForTeamTrue();
}