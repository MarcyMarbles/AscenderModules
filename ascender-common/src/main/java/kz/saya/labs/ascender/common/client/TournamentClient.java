package kz.saya.labs.ascender.common.client;

import kz.saya.labs.ascender.common.dto.TournamentDto;
import kz.saya.labs.ascender.common.dto.TournamentJoinRequestDto;
import kz.saya.labs.ascender.common.dto.TournamentMatchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Feign client for the Tournament Service.
 * This interface allows other microservices to communicate with the Tournament Service.
 */
@FeignClient(name = "ascender-tournament-service")
public interface TournamentClient {

    /**
     * Get all tournaments.
     *
     * @return List of all tournaments
     */
    @GetMapping("/api/tournaments")
    ResponseEntity<List<TournamentDto>> getAllTournaments();

    /**
     * Get a tournament by ID.
     *
     * @param id Tournament ID
     * @return Tournament if found
     */
    @GetMapping("/api/tournaments/{id}")
    ResponseEntity<TournamentDto> getTournamentById(@PathVariable("id") UUID id);

    /**
     * Create a new tournament.
     *
     * @param tournament Tournament to create
     * @return Created tournament
     */
    @PostMapping("/api/tournaments")
    ResponseEntity<TournamentDto> createTournament(@RequestBody TournamentDto tournament);

    /**
     * Update a tournament.
     *
     * @param id Tournament ID
     * @param tournament Tournament to update
     * @return Updated tournament
     */
    @PutMapping("/api/tournaments/{id}")
    ResponseEntity<TournamentDto> updateTournament(@PathVariable("id") UUID id, @RequestBody TournamentDto tournament);

    /**
     * Delete a tournament.
     *
     * @param id Tournament ID
     * @return No content
     */
    @DeleteMapping("/api/tournaments/{id}")
    ResponseEntity<Void> deleteTournament(@PathVariable("id") UUID id);

    /**
     * Add a team to a tournament.
     *
     * @param tournamentId Tournament ID
     * @param teamId Team ID
     * @return Updated tournament
     */
    @PostMapping("/api/tournaments/{tournamentId}/teams")
    ResponseEntity<TournamentDto> addTeamToTournament(@PathVariable("tournamentId") UUID tournamentId, @RequestBody Map<String, UUID> teamId);

    /**
     * Remove a team from a tournament.
     *
     * @param tournamentId Tournament ID
     * @param teamId Team ID
     * @return Updated tournament
     */
    @DeleteMapping("/api/tournaments/{tournamentId}/teams/{teamId}")
    ResponseEntity<TournamentDto> removeTeamFromTournament(@PathVariable("tournamentId") UUID tournamentId, @PathVariable("teamId") UUID teamId);

    /**
     * Create a match in a tournament.
     *
     * @param tournamentId Tournament ID
     * @param match Match to create
     * @return Created match
     */
    @PostMapping("/api/tournaments/{tournamentId}/matches")
    ResponseEntity<TournamentMatchDto> createMatch(@PathVariable("tournamentId") UUID tournamentId, @RequestBody TournamentMatchDto match);

    /**
     * Update match scores.
     *
     * @param matchId Match ID
     * @param scores Map of team IDs to scores
     * @return Updated match
     */
    @PutMapping("/api/tournaments/matches/{matchId}/scores")
    ResponseEntity<TournamentMatchDto> updateMatchScores(@PathVariable("matchId") UUID matchId, @RequestBody Map<UUID, Integer> scores);

    /**
     * Set a technical result for a match.
     *
     * @param matchId Match ID
     * @param techResult Map containing teamId and techResult
     * @return Updated match
     */
    @PutMapping("/api/tournaments/matches/{matchId}/tech-result")
    ResponseEntity<TournamentMatchDto> setTechnicalResult(@PathVariable("matchId") UUID matchId, @RequestBody Map<String, Object> techResult);

    /**
     * Generate a bracket for a tournament.
     *
     * @param tournamentId Tournament ID
     * @return List of generated matches
     */
    @PostMapping("/api/tournaments/{tournamentId}/bracket")
    ResponseEntity<List<TournamentMatchDto>> generateBracket(@PathVariable("tournamentId") UUID tournamentId);

    /**
     * Update a tournament bracket.
     *
     * @param tournamentId Tournament ID
     * @return No content
     */
    @PutMapping("/api/tournaments/{tournamentId}/bracket")
    ResponseEntity<Void> updateBracket(@PathVariable("tournamentId") UUID tournamentId);

    /**
     * Get all join requests for a tournament.
     *
     * @param tournamentId Tournament ID
     * @return List of join requests
     */
    @GetMapping("/api/tournaments/{tournamentId}/join-requests")
    ResponseEntity<List<TournamentJoinRequestDto>> getJoinRequestsByTournament(@PathVariable("tournamentId") UUID tournamentId);

    /**
     * Create a join request for a tournament.
     *
     * @param joinRequest Join request to create
     * @return Created join request
     */
    @PostMapping("/api/tournaments/join-requests")
    ResponseEntity<TournamentJoinRequestDto> createJoinRequest(@RequestBody TournamentJoinRequestDto joinRequest);

    /**
     * Update a join request.
     *
     * @param id Join request ID
     * @param joinRequest Join request to update
     * @return Updated join request
     */
    @PutMapping("/api/tournaments/join-requests/{id}")
    ResponseEntity<TournamentJoinRequestDto> updateJoinRequest(@PathVariable("id") UUID id, @RequestBody TournamentJoinRequestDto joinRequest);

    /**
     * Delete a join request.
     *
     * @param id Join request ID
     * @return No content
     */
    @DeleteMapping("/api/tournaments/join-requests/{id}")
    ResponseEntity<Void> deleteJoinRequest(@PathVariable("id") UUID id);
}