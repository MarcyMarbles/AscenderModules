package kz.saya.labs.ascender.common.client;

import kz.saya.labs.ascender.common.dto.GameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Feign client for the Games Service.
 * This interface allows other microservices to communicate with the Games Service.
 */
@FeignClient(name = "ascender-games-service")
public interface GameClient {

    /**
     * Get all games.
     *
     * @return List of all games
     */
    @GetMapping("/api/games")
    ResponseEntity<List<GameDto>> getAllGames();

    /**
     * Get a game by ID.
     *
     * @param id Game ID
     * @return Game if found
     */
    @GetMapping("/api/games/{id}")
    ResponseEntity<GameDto> getGameById(@PathVariable("id") UUID id);

    /**
     * Create a new game.
     *
     * @param game Game to create
     * @return Created game
     */
    @PostMapping("/api/games")
    ResponseEntity<GameDto> createGame(@RequestBody GameDto game);

    /**
     * Update a game.
     *
     * @param id   Game ID
     * @param game Game to update
     * @return Updated game
     */
    @PutMapping("/api/games/{id}")
    ResponseEntity<GameDto> updateGame(@PathVariable("id") UUID id, @RequestBody GameDto game);

    /**
     * Delete a game.
     *
     * @param id Game ID
     * @return No content
     */
    @DeleteMapping("/api/games/{id}")
    ResponseEntity<Void> deleteGame(@PathVariable("id") UUID id);

    /**
     * Get all games that support scrims.
     *
     * @return List of games that support scrims
     */
    @GetMapping("/api/games/scrimable")
    ResponseEntity<List<GameDto>> getScrimableGames();
}