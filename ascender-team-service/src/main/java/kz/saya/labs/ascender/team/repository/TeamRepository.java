package kz.saya.labs.ascender.team.repository;

import kz.saya.labs.ascender.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Team entities.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    
    /**
     * Find teams by game ID.
     *
     * @param gameId Game ID
     * @return List of teams for the specified game
     */
    @Query("SELECT t FROM Team t JOIN t.gameIds g WHERE g = :gameId")
    List<Team> findByGameId(@Param("gameId") UUID gameId);
    
    /**
     * Find teams by creator ID.
     *
     * @param creatorId Creator ID
     * @return List of teams created by the specified player
     */
    List<Team> findByCreatorId(UUID creatorId);
    
    /**
     * Find teams that a player is a member of.
     *
     * @param playerId Player ID
     * @return List of teams that the player is a member of
     */
    @Query("SELECT t FROM Team t JOIN t.playerIds p WHERE p = :playerId")
    List<Team> findByPlayerId(@Param("playerId") UUID playerId);
}