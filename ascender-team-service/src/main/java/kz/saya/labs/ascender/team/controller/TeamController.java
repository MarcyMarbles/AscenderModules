package kz.saya.labs.ascender.team.controller;

import jakarta.validation.Valid;
import kz.saya.labs.ascender.common.dto.TeamDto;
import kz.saya.labs.ascender.common.exception.ForbiddenException;
import kz.saya.labs.ascender.team.dto.TeamCreateDto;
import kz.saya.labs.ascender.team.dto.TeamUpdateDto;
import kz.saya.labs.ascender.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for managing teams.
 */
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    /**
     * Get all teams.
     *
     * @return List of all teams
     */
    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    /**
     * Get a team by ID.
     *
     * @param id Team ID
     * @return Team if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable UUID id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    /**
     * Create a new team.
     *
     * @param createDto DTO with team data
     * @param userId User ID of the creator
     * @return Created team
     */
    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@Valid @RequestBody TeamCreateDto createDto, @RequestHeader("X-User-ID") UUID userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teamService.createTeam(createDto, userId));
    }

    /**
     * Update a team.
     *
     * @param id Team ID
     * @param updateDto DTO with updated team data
     * @param userId User ID of the updater
     * @return Updated team
     */
    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> updateTeam(@PathVariable UUID id, 
                                             @Valid @RequestBody TeamUpdateDto updateDto, 
                                             @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the team creator
        if (!teamService.isTeamCreator(id, userId)) {
            throw new ForbiddenException("You don't have permission to update this team");
        }
        
        return ResponseEntity.ok(teamService.updateTeam(id, updateDto));
    }

    /**
     * Delete a team.
     *
     * @param id Team ID
     * @param userId User ID of the deleter
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id, @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the team creator
        if (!teamService.isTeamCreator(id, userId)) {
            throw new ForbiddenException("You don't have permission to delete this team");
        }
        
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Add a player to a team.
     *
     * @param id Team ID
     * @param requestData Map containing playerId
     * @param userId User ID of the team creator
     * @return Updated team
     */
    @PostMapping("/{id}/players")
    public ResponseEntity<TeamDto> addPlayerToTeam(@PathVariable UUID id, 
                                                  @RequestBody Map<String, Object> requestData, 
                                                  @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the team creator
        if (!teamService.isTeamCreator(id, userId)) {
            throw new ForbiddenException("You don't have permission to add players to this team");
        }
        
        UUID playerId = UUID.fromString((String) requestData.get("playerId"));
        return ResponseEntity.ok(teamService.addPlayerToTeam(id, playerId));
    }

    /**
     * Remove a player from a team.
     *
     * @param teamId Team ID
     * @param playerId Player ID
     * @param userId User ID of the team creator
     * @return Updated team
     */
    @DeleteMapping("/{teamId}/players/{playerId}")
    public ResponseEntity<TeamDto> removePlayerFromTeam(@PathVariable UUID teamId, 
                                                       @PathVariable UUID playerId, 
                                                       @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the team creator
        if (!teamService.isTeamCreator(teamId, userId)) {
            throw new ForbiddenException("You don't have permission to remove players from this team");
        }
        
        return ResponseEntity.ok(teamService.removePlayerFromTeam(teamId, playerId));
    }

    /**
     * Find teams by game.
     *
     * @param gameId Game ID
     * @return List of teams for the specified game
     */
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<TeamDto>> findTeamsByGame(@PathVariable UUID gameId) {
        return ResponseEntity.ok(teamService.findTeamsByGame(gameId));
    }

    /**
     * Find teams by creator.
     *
     * @param creatorId Creator ID
     * @return List of teams created by the specified player
     */
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<TeamDto>> findTeamsByCreator(@PathVariable UUID creatorId) {
        return ResponseEntity.ok(teamService.findTeamsByCreator(creatorId));
    }

    /**
     * Find teams by player.
     *
     * @param playerId Player ID
     * @return List of teams that the player is a member of
     */
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<TeamDto>> findTeamsByPlayer(@PathVariable UUID playerId) {
        return ResponseEntity.ok(teamService.findTeamsByPlayer(playerId));
    }

    /**
     * Initiate a votekick for a player in a team.
     *
     * @param teamId Team ID
     * @param targetId Target player ID
     * @param userId User ID of the initiator
     * @return Updated team
     */
    @PostMapping("/{teamId}/votekick/{targetId}")
    public ResponseEntity<TeamDto> initiateVotekick(@PathVariable UUID teamId, 
                                                   @PathVariable UUID targetId, 
                                                   @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is a team member
        if (!teamService.isTeamMember(teamId, userId)) {
            throw new ForbiddenException("You are not a member of this team");
        }
        
        // Check if target is the team creator
        if (teamService.isTeamCreator(teamId, targetId)) {
            throw new ForbiddenException("You cannot votekick the team creator");
        }
        
        return ResponseEntity.ok(teamService.removePlayerFromTeam(teamId, targetId));
    }
}