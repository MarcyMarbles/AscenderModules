package kz.saya.labs.ascender.common.client;

import kz.saya.labs.ascender.common.dto.TeamDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Feign client for the Team Service.
 * This interface allows other microservices to communicate with the Team Service.
 */
@FeignClient(name = "ascender-team-service")
public interface TeamClient {

    /**
     * Get all teams.
     *
     * @return List of all teams
     */
    @GetMapping("/api/teams")
    ResponseEntity<List<TeamDto>> getAllTeams();

    /**
     * Get a team by ID.
     *
     * @param id Team ID
     * @return Team if found
     */
    @GetMapping("/api/teams/{id}")
    ResponseEntity<TeamDto> getTeamById(@PathVariable("id") UUID id);

    /**
     * Create a new team.
     *
     * @param team Team to create
     * @param userId User ID of the creator
     * @return Created team
     */
    @PostMapping("/api/teams")
    ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto team, @RequestHeader("X-User-ID") UUID userId);

    /**
     * Update a team.
     *
     * @param id Team ID
     * @param team Team to update
     * @param userId User ID of the updater
     * @return Updated team
     */
    @PutMapping("/api/teams/{id}")
    ResponseEntity<TeamDto> updateTeam(@PathVariable("id") UUID id, @RequestBody TeamDto team, @RequestHeader("X-User-ID") UUID userId);

    /**
     * Delete a team.
     *
     * @param id Team ID
     * @param userId User ID of the deleter
     * @return No content
     */
    @DeleteMapping("/api/teams/{id}")
    ResponseEntity<Void> deleteTeam(@PathVariable("id") UUID id, @RequestHeader("X-User-ID") UUID userId);

    /**
     * Add a player to a team.
     *
     * @param id Team ID
     * @param requestData Map containing playerId
     * @param userId User ID of the team creator
     * @return Updated team
     */
    @PostMapping("/api/teams/{id}/players")
    ResponseEntity<TeamDto> addPlayerToTeam(@PathVariable("id") UUID id, @RequestBody Map<String, Object> requestData, @RequestHeader("X-User-ID") UUID userId);

    /**
     * Remove a player from a team.
     *
     * @param teamId Team ID
     * @param playerId Player ID
     * @param userId User ID of the team creator
     * @return Updated team
     */
    @DeleteMapping("/api/teams/{teamId}/players/{playerId}")
    ResponseEntity<TeamDto> removePlayerFromTeam(@PathVariable("teamId") UUID teamId, @PathVariable("playerId") UUID playerId, @RequestHeader("X-User-ID") UUID userId);

    /**
     * Find teams by game.
     *
     * @param gameId Game ID
     * @return List of teams for the specified game
     */
    @GetMapping("/api/teams/game/{gameId}")
    ResponseEntity<List<TeamDto>> findTeamsByGame(@PathVariable("gameId") UUID gameId);

    /**
     * Initiate a votekick for a player in a team.
     *
     * @param teamId Team ID
     * @param targetId Target player ID
     * @param userId User ID of the initiator
     * @return Updated team
     */
    @PostMapping("/api/teams/{teamId}/votekick/{targetId}")
    ResponseEntity<TeamDto> initiateVotekick(@PathVariable("teamId") UUID teamId, @PathVariable("targetId") UUID targetId, @RequestHeader("X-User-ID") UUID userId);
}