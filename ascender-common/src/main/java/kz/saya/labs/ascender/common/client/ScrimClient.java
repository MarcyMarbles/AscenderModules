package kz.saya.labs.ascender.common.client;

import kz.saya.labs.ascender.common.dto.ScrimDto;
import kz.saya.labs.ascender.common.dto.ScrimRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Feign client for the Scrim Service.
 * This interface allows other microservices to communicate with the Scrim Service.
 */
@FeignClient(name = "ascender-scrim-service")
public interface ScrimClient {

    /**
     * Get all scrims.
     *
     * @return List of all scrims
     */
    @GetMapping("/api/scrims")
    ResponseEntity<List<ScrimDto>> getAllScrims();

    /**
     * Get a scrim by ID.
     *
     * @param id Scrim ID
     * @return Scrim if found
     */
    @GetMapping("/api/scrims/{id}")
    ResponseEntity<ScrimDto> getScrimById(@PathVariable("id") UUID id);

    /**
     * Create a new scrim.
     *
     * @param scrim Scrim to create
     * @return Created scrim
     */
    @PostMapping("/api/scrims")
    ResponseEntity<ScrimDto> createScrim(@RequestBody ScrimDto scrim);

    /**
     * Update a scrim.
     *
     * @param id    Scrim ID
     * @param scrim Scrim to update
     * @return Updated scrim
     */
    @PutMapping("/api/scrims/{id}")
    ResponseEntity<ScrimDto> updateScrim(@PathVariable("id") UUID id, @RequestBody ScrimDto scrim);

    /**
     * Delete a scrim.
     *
     * @param id Scrim ID
     * @return No content
     */
    @DeleteMapping("/api/scrims/{id}")
    ResponseEntity<Void> deleteScrim(@PathVariable("id") UUID id);

    /**
     * Get all scrim requests.
     *
     * @return List of all scrim requests
     */
    @GetMapping("/api/scrims/requests")
    ResponseEntity<List<ScrimRequestDto>> getAllScrimRequests();

    /**
     * Get a scrim request by ID.
     *
     * @param id Scrim request ID
     * @return Scrim request if found
     */
    @GetMapping("/api/scrims/requests/{id}")
    ResponseEntity<ScrimRequestDto> getScrimRequestById(@PathVariable("id") UUID id);

    /**
     * Create a new scrim request.
     *
     * @param requestData Request data containing name, description, gameId, and teamId
     * @return Created scrim request
     */
    @PostMapping("/api/scrims/requests")
    ResponseEntity<ScrimRequestDto> createScrimRequest(@RequestBody Map<String, Object> requestData);

    /**
     * Accept a scrim request.
     *
     * @param id          Scrim request ID
     * @param requestData Request data containing acceptingTeamId
     * @return Created scrim
     */
    @PostMapping("/api/scrims/requests/{id}/accept")
    ResponseEntity<ScrimDto> acceptScrimRequest(@PathVariable("id") UUID id, @RequestBody Map<String, Object> requestData);

    /**
     * Add a player to a scrim.
     *
     * @param id          Scrim ID
     * @param requestData Request data containing playerId
     * @return Updated scrim
     */
    @PostMapping("/api/scrims/{id}/players")
    ResponseEntity<ScrimDto> addPlayerToScrim(@PathVariable("id") UUID id, @RequestBody Map<String, Object> requestData);

    /**
     * Complete a scrim.
     *
     * @param id          Scrim ID
     * @param requestData Request data containing winnerTeamId, result, and duration
     * @return Updated scrim
     */
    @PostMapping("/api/scrims/{id}/complete")
    ResponseEntity<ScrimDto> completeScrim(@PathVariable("id") UUID id, @RequestBody Map<String, Object> requestData);

    /**
     * Find active scrims by game.
     *
     * @param gameId Game ID
     * @return List of active scrims for the specified game
     */
    @GetMapping("/api/scrims/active/game/{gameId}")
    ResponseEntity<List<ScrimDto>> findActiveScrimsByGame(@PathVariable("gameId") UUID gameId);

    /**
     * Find pending scrim requests by game.
     *
     * @param gameId Game ID
     * @return List of pending scrim requests for the specified game
     */
    @GetMapping("/api/scrims/requests/pending/game/{gameId}")
    ResponseEntity<List<ScrimRequestDto>> findPendingScrimRequestsByGame(@PathVariable("gameId") UUID gameId);
}