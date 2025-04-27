package kz.saya.labs.ascender.tournament.repository;

import kz.saya.labs.ascender.tournament.entity.Tournament;
import kz.saya.labs.ascender.tournament.entity.Tournament.TournamentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Tournament entities.
 */
@Repository
public interface TournamentRepository extends JpaRepository<Tournament, UUID> {
    
    /**
     * Find tournaments by game ID.
     *
     * @param gameId Game ID
     * @return List of tournaments for the specified game
     */
    List<Tournament> findByGameId(UUID gameId);
    
    /**
     * Find tournaments by status.
     *
     * @param status Tournament status
     * @return List of tournaments with the specified status
     */
    List<Tournament> findByStatus(TournamentStatus status);
    
    /**
     * Find tournaments by game ID and status.
     *
     * @param gameId Game ID
     * @param status Tournament status
     * @return List of tournaments for the specified game with the specified status
     */
    List<Tournament> findByGameIdAndStatus(UUID gameId, TournamentStatus status);
    
    /**
     * Find tournaments that a team is participating in.
     *
     * @param teamId Team ID
     * @return List of tournaments that the team is participating in
     */
    @Query("SELECT t FROM Tournament t JOIN t.teamIds tid WHERE tid = :teamId")
    List<Tournament> findByTeamId(@Param("teamId") UUID teamId);
    
    /**
     * Find tournaments by name containing the specified string (case-insensitive).
     *
     * @param name Name to search for
     * @return List of tournaments with names containing the specified string
     */
    List<Tournament> findByNameContainingIgnoreCase(String name);
}