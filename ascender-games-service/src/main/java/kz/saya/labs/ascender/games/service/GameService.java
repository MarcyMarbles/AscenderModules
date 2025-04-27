package kz.saya.labs.ascender.games.service;

import kz.saya.labs.ascender.common.exception.ResourceNotFoundException;
import kz.saya.labs.ascender.games.entity.Game;
import kz.saya.labs.ascender.games.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service for managing games.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    /**
     * Get all games.
     *
     * @return List of all games
     */
    @Transactional(readOnly = true)
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Get a game by ID.
     *
     * @param id Game ID
     * @return Game if found
     * @throws ResourceNotFoundException if game not found
     */
    @Transactional(readOnly = true)
    public Game getGameById(UUID id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", id));
    }

    /**
     * Save a game.
     *
     * @param game Game to save
     * @return Saved game
     */
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Delete a game by ID.
     *
     * @param id Game ID
     */
    public void deleteGame(UUID id) {
        gameRepository.deleteById(id);
    }

    /**
     * Get all games that support scrims.
     *
     * @return List of games that support scrims
     */
    @Transactional(readOnly = true)
    public List<Game> getScrimableGames() {
        return gameRepository.findByScrimableTrue();
    }
}
