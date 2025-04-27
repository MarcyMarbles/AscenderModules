package kz.saya.labs.ascender.team.controller;

import jakarta.validation.Valid;
import kz.saya.labs.ascender.common.exception.ForbiddenException;
import kz.saya.labs.ascender.team.dto.JoinRequestDto;
import kz.saya.labs.ascender.team.entity.JoinRequest.RequestStatus;
import kz.saya.labs.ascender.team.service.JoinRequestService;
import kz.saya.labs.ascender.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing join requests.
 */
@RestController
@RequestMapping("/api/join-requests")
@RequiredArgsConstructor
public class JoinRequestController {

    private final JoinRequestService joinRequestService;
    private final TeamService teamService;

    /**
     * Get all join requests.
     *
     * @return List of all join requests
     */
    @GetMapping
    public ResponseEntity<List<JoinRequestDto>> getAllJoinRequests() {
        return ResponseEntity.ok(joinRequestService.getAllJoinRequests());
    }

    /**
     * Get a join request by ID.
     *
     * @param id Join request ID
     * @return Join request if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<JoinRequestDto> getJoinRequestById(@PathVariable UUID id) {
        return ResponseEntity.ok(joinRequestService.getJoinRequestById(id));
    }

    /**
     * Create a new join request.
     *
     * @param dto DTO with join request data
     * @param userId User ID of the player making the request
     * @return Created join request
     */
    @PostMapping
    public ResponseEntity<JoinRequestDto> createJoinRequest(@Valid @RequestBody JoinRequestDto dto, @RequestHeader("X-User-ID") UUID userId) {
        // Ensure the player ID in the request matches the authenticated user
        if (!dto.getPlayerId().equals(userId)) {
            throw new ForbiddenException("You can only create join requests for yourself");
        }
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(joinRequestService.createJoinRequest(dto));
    }

    /**
     * Update a join request (accept or reject).
     *
     * @param id Join request ID
     * @param dto DTO with updated join request data
     * @param userId User ID of the team creator
     * @return Updated join request
     */
    @PutMapping("/{id}")
    public ResponseEntity<JoinRequestDto> updateJoinRequest(@PathVariable UUID id, 
                                                          @Valid @RequestBody JoinRequestDto dto, 
                                                          @RequestHeader("X-User-ID") UUID userId) {
        JoinRequestDto existingRequest = joinRequestService.getJoinRequestById(id);
        
        // Check if user is the team creator
        if (!teamService.isTeamCreator(existingRequest.getTeamId(), userId)) {
            throw new ForbiddenException("Only the team creator can accept or reject join requests");
        }
        
        return ResponseEntity.ok(joinRequestService.updateJoinRequest(id, dto));
    }

    /**
     * Delete a join request.
     *
     * @param id Join request ID
     * @param userId User ID of the player who made the request
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoinRequest(@PathVariable UUID id, @RequestHeader("X-User-ID") UUID userId) {
        JoinRequestDto existingRequest = joinRequestService.getJoinRequestById(id);
        
        // Check if user is the player who made the request or the team creator
        if (!existingRequest.getPlayerId().equals(userId) && 
            !teamService.isTeamCreator(existingRequest.getTeamId(), userId)) {
            throw new ForbiddenException("You don't have permission to delete this join request");
        }
        
        joinRequestService.deleteJoinRequest(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Find join requests by team ID.
     *
     * @param teamId Team ID
     * @param userId User ID of the team creator
     * @return List of join requests for the specified team
     */
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<JoinRequestDto>> findJoinRequestsByTeam(@PathVariable UUID teamId, @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the team creator
        if (!teamService.isTeamCreator(teamId, userId)) {
            throw new ForbiddenException("Only the team creator can view join requests for the team");
        }
        
        return ResponseEntity.ok(joinRequestService.findJoinRequestsByTeam(teamId));
    }

    /**
     * Find pending join requests by team ID.
     *
     * @param teamId Team ID
     * @param userId User ID of the team creator
     * @return List of pending join requests for the specified team
     */
    @GetMapping("/team/{teamId}/pending")
    public ResponseEntity<List<JoinRequestDto>> findPendingJoinRequestsByTeam(@PathVariable UUID teamId, @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the team creator
        if (!teamService.isTeamCreator(teamId, userId)) {
            throw new ForbiddenException("Only the team creator can view pending join requests for the team");
        }
        
        return ResponseEntity.ok(joinRequestService.findPendingJoinRequestsByTeam(teamId));
    }

    /**
     * Find join requests by player ID.
     *
     * @param playerId Player ID
     * @param userId User ID of the player
     * @return List of join requests made by the specified player
     */
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<JoinRequestDto>> findJoinRequestsByPlayer(@PathVariable UUID playerId, @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the player
        if (!playerId.equals(userId)) {
            throw new ForbiddenException("You can only view your own join requests");
        }
        
        return ResponseEntity.ok(joinRequestService.findJoinRequestsByPlayer(playerId));
    }

    /**
     * Find pending join requests by player ID.
     *
     * @param playerId Player ID
     * @param userId User ID of the player
     * @return List of pending join requests made by the specified player
     */
    @GetMapping("/player/{playerId}/pending")
    public ResponseEntity<List<JoinRequestDto>> findPendingJoinRequestsByPlayer(@PathVariable UUID playerId, @RequestHeader("X-User-ID") UUID userId) {
        // Check if user is the player
        if (!playerId.equals(userId)) {
            throw new ForbiddenException("You can only view your own pending join requests");
        }
        
        return ResponseEntity.ok(joinRequestService.findPendingJoinRequestsByPlayer(playerId));
    }

    /**
     * Accept a join request.
     *
     * @param id Join request ID
     * @param userId User ID of the team creator
     * @return Updated join request
     */
    @PutMapping("/{id}/accept")
    public ResponseEntity<JoinRequestDto> acceptJoinRequest(@PathVariable UUID id, @RequestHeader("X-User-ID") UUID userId) {
        JoinRequestDto existingRequest = joinRequestService.getJoinRequestById(id);
        
        // Check if user is the team creator
        if (!teamService.isTeamCreator(existingRequest.getTeamId(), userId)) {
            throw new ForbiddenException("Only the team creator can accept join requests");
        }
        
        JoinRequestDto updateDto = new JoinRequestDto();
        updateDto.setStatus(RequestStatus.ACCEPTED);
        
        return ResponseEntity.ok(joinRequestService.updateJoinRequest(id, updateDto));
    }

    /**
     * Reject a join request.
     *
     * @param id Join request ID
     * @param userId User ID of the team creator
     * @return Updated join request
     */
    @PutMapping("/{id}/reject")
    public ResponseEntity<JoinRequestDto> rejectJoinRequest(@PathVariable UUID id, @RequestHeader("X-User-ID") UUID userId) {
        JoinRequestDto existingRequest = joinRequestService.getJoinRequestById(id);
        
        // Check if user is the team creator
        if (!teamService.isTeamCreator(existingRequest.getTeamId(), userId)) {
            throw new ForbiddenException("Only the team creator can reject join requests");
        }
        
        JoinRequestDto updateDto = new JoinRequestDto();
        updateDto.setStatus(RequestStatus.REJECTED);
        
        return ResponseEntity.ok(joinRequestService.updateJoinRequest(id, updateDto));
    }
}