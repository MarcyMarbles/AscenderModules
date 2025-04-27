package kz.saya.labs.ascender.games.controller;

import kz.saya.labs.ascender.common.dto.GameDto;
import kz.saya.labs.ascender.common.exception.ResourceNotFoundException;
import kz.saya.labs.ascender.games.entity.Game;
import kz.saya.labs.ascender.games.mapper.GameMapper;
import kz.saya.labs.ascender.games.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing games.
 */
@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;

    /**
     * Get all games.
     *
     * @return List of all games
     */
    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(gameMapper.toDtoList(games));
    }

    /**
     * Get a game by ID.
     *
     * @param id Game ID
     * @return Game if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable UUID id) {
        Game game = gameService.getGameById(id);
        return ResponseEntity.ok(gameMapper.toDto(game));
    }

    /**
     * Create a new game.
     *
     * @param gameDto Game DTO to create
     * @return Created game DTO
     */
    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        if (gameDto.getName() == null || gameDto.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Game game = gameMapper.toEntity(gameDto);
        Game savedGame = gameService.saveGame(game);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameMapper.toDto(savedGame));
    }

    /**
     * Update a game.
     *
     * @param id      Game ID
     * @param gameDto Game DTO to update
     * @return Updated game DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<GameDto> updateGame(@PathVariable UUID id, @RequestBody GameDto gameDto) {
        // Check if game exists
        gameService.getGameById(id);

        // Update game
        gameDto.setId(id);
        Game game = gameMapper.toEntity(gameDto);
        Game updatedGame = gameService.saveGame(game);
        return ResponseEntity.ok(gameMapper.toDto(updatedGame));
    }

    /**
     * Delete a game.
     *
     * @param id Game ID
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID id) {
        // Check if game exists
        gameService.getGameById(id);

        // Delete game
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all games that support scrims.
     *
     * @return List of games that support scrims
     */
    @GetMapping("/scrimable")
    public ResponseEntity<List<GameDto>> getScrimableGames() {
        List<Game> games = gameService.getScrimableGames();
        return ResponseEntity.ok(gameMapper.toDtoList(games));
    }
}
