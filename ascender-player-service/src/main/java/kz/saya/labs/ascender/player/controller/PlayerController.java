package kz.saya.labs.ascender.player.controller;

import jakarta.validation.Valid;
import kz.saya.labs.ascender.common.dto.PlayerDto;
import kz.saya.labs.ascender.player.dto.PlayerCreateDto;
import kz.saya.labs.ascender.player.dto.PlayerUpdateDto;
import kz.saya.labs.ascender.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing player profiles.
 */
@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    /**
     * Get all players.
     *
     * @return List of all players
     */
    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    /**
     * Get a player by ID.
     *
     * @param id Player ID
     * @return Player if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable UUID id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    /**
     * Create a new player.
     *
     * @param createDto DTO with player data
     * @return Created player
     */
    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@Valid @RequestBody PlayerCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerService.createPlayer(createDto));
    }

    /**
     * Update a player.
     *
     * @param id Player ID
     * @param updateDto DTO with updated player data
     * @return Updated player
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable UUID id, 
                                                 @Valid @RequestBody PlayerUpdateDto updateDto) {
        return ResponseEntity.ok(playerService.updatePlayer(id, updateDto));
    }

    /**
     * Delete a player.
     *
     * @param id Player ID
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Find players by skill level.
     *
     * @param skillLevel Skill level to search for
     * @return List of players with the specified skill level
     */
    @GetMapping("/skill/{skillLevel}")
    public ResponseEntity<List<PlayerDto>> findPlayersBySkillLevel(@PathVariable String skillLevel) {
        return ResponseEntity.ok(playerService.findPlayersBySkillLevel(skillLevel));
    }

    /**
     * Find players looking for a team.
     *
     * @return List of players looking for a team
     */
    @GetMapping("/looking-for-team")
    public ResponseEntity<List<PlayerDto>> findPlayersLookingForTeam() {
        return ResponseEntity.ok(playerService.findPlayersLookingForTeam());
    }

    /**
     * Find a player by user ID.
     *
     * @param userId User ID
     * @return Player if found
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<PlayerDto> findPlayerByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(playerService.findPlayerByUserId(userId));
    }

    /**
     * Update a player's avatar.
     *
     * @param id Player ID
     * @param avatarId Avatar file ID
     * @return Updated player
     */
    @PutMapping("/{id}/avatar/{avatarId}")
    public ResponseEntity<PlayerDto> updatePlayerAvatar(@PathVariable UUID id, 
                                                       @PathVariable UUID avatarId) {
        return ResponseEntity.ok(playerService.updatePlayerAvatar(id, avatarId));
    }

    /**
     * Update a player's profile background.
     *
     * @param id Player ID
     * @param backgroundId Background file ID
     * @return Updated player
     */
    @PutMapping("/{id}/background/{backgroundId}")
    public ResponseEntity<PlayerDto> updatePlayerBackground(@PathVariable UUID id, 
                                                          @PathVariable UUID backgroundId) {
        return ResponseEntity.ok(playerService.updatePlayerBackground(id, backgroundId));
    }
}