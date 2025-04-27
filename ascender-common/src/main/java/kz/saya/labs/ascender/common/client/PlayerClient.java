package kz.saya.labs.ascender.common.client;

import kz.saya.labs.ascender.common.dto.PlayerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Feign client for the Player Service.
 * This interface allows other microservices to communicate with the Player Service.
 */
@FeignClient(name = "ascender-player-service")
public interface PlayerClient {

    /**
     * Get all players.
     *
     * @return List of all players
     */
    @GetMapping("/api/players")
    ResponseEntity<List<PlayerDto>> getAllPlayers();

    /**
     * Get a player by ID.
     *
     * @param id Player ID
     * @return Player if found
     */
    @GetMapping("/api/players/{id}")
    ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") UUID id);

    /**
     * Create a new player.
     *
     * @param player Player to create
     * @return Created player
     */
    @PostMapping("/api/players")
    ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto player);

    /**
     * Update a player.
     *
     * @param id     Player ID
     * @param player Player to update
     * @return Updated player
     */
    @PutMapping("/api/players/{id}")
    ResponseEntity<PlayerDto> updatePlayer(@PathVariable("id") UUID id, @RequestBody PlayerDto player);

    /**
     * Delete a player.
     *
     * @param id Player ID
     * @return No content
     */
    @DeleteMapping("/api/players/{id}")
    ResponseEntity<Void> deletePlayer(@PathVariable("id") UUID id);

    /**
     * Find players by skill level.
     *
     * @param skillLevel Skill level to search for
     * @return List of players with the specified skill level
     */
    @GetMapping("/api/players/skill/{skillLevel}")
    ResponseEntity<List<PlayerDto>> findPlayersBySkillLevel(@PathVariable("skillLevel") String skillLevel);

    /**
     * Find players looking for a team.
     *
     * @return List of players looking for a team
     */
    @GetMapping("/api/players/looking-for-team")
    ResponseEntity<List<PlayerDto>> findPlayersLookingForTeam();

    /**
     * Find a player by user ID.
     *
     * @param userId User ID
     * @return Player if found
     */
    @GetMapping("/api/players/user/{userId}")
    ResponseEntity<PlayerDto> findPlayerByUserId(@PathVariable("userId") UUID userId);
}