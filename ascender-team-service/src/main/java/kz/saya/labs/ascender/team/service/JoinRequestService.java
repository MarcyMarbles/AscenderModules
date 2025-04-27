package kz.saya.labs.ascender.team.service;

import kz.saya.labs.ascender.common.exception.BadRequestException;
import kz.saya.labs.ascender.common.exception.ResourceNotFoundException;
import kz.saya.labs.ascender.team.dto.JoinRequestDto;
import kz.saya.labs.ascender.team.entity.JoinRequest;
import kz.saya.labs.ascender.team.entity.JoinRequest.RequestStatus;
import kz.saya.labs.ascender.team.mapper.JoinRequestMapper;
import kz.saya.labs.ascender.team.repository.JoinRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for managing join requests.
 */
@Service
@RequiredArgsConstructor
public class JoinRequestService {

    private final JoinRequestRepository joinRequestRepository;
    private final JoinRequestMapper joinRequestMapper;
    private final TeamService teamService;

    /**
     * Get all join requests.
     *
     * @return List of all join requests as DTOs
     */
    public List<JoinRequestDto> getAllJoinRequests() {
        return joinRequestRepository.findAll().stream()
                .map(joinRequestMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get a join request by ID.
     *
     * @param id Join request ID
     * @return Join request as DTO
     * @throws ResourceNotFoundException if join request not found
     */
    public JoinRequestDto getJoinRequestById(UUID id) {
        return joinRequestRepository.findById(id)
                .map(joinRequestMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Join request not found with id: " + id));
    }

    /**
     * Create a new join request.
     *
     * @param dto DTO with join request data
     * @return Created join request as DTO
     * @throws BadRequestException if the player is already a member of the team
     */
    @Transactional
    public JoinRequestDto createJoinRequest(JoinRequestDto dto) {
        // Check if the player is already a member of the team
        if (teamService.isTeamMember(dto.getTeamId(), dto.getPlayerId())) {
            throw new BadRequestException("Player is already a member of the team");
        }
        
        // Check if there's already a pending join request
        List<JoinRequest> existingRequests = joinRequestRepository.findByTeamIdAndPlayerIdAndStatus(
                dto.getTeamId(), dto.getPlayerId(), RequestStatus.PENDING);
        
        if (!existingRequests.isEmpty()) {
            throw new BadRequestException("A pending join request already exists");
        }
        
        JoinRequest joinRequest = joinRequestMapper.createEntityFromDto(dto);
        joinRequest.setRequestDate(OffsetDateTime.now());
        joinRequest = joinRequestRepository.save(joinRequest);
        return joinRequestMapper.toDto(joinRequest);
    }

    /**
     * Update a join request.
     *
     * @param id Join request ID
     * @param dto DTO with updated join request data
     * @return Updated join request as DTO
     * @throws ResourceNotFoundException if join request not found
     */
    @Transactional
    public JoinRequestDto updateJoinRequest(UUID id, JoinRequestDto dto) {
        JoinRequest joinRequest = joinRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Join request not found with id: " + id));
        
        joinRequest = joinRequestMapper.updateEntityFromDto(joinRequest, dto);
        
        // If the status is being updated to ACCEPTED or REJECTED, set the response date
        if (dto.getStatus() != null && dto.getStatus() != RequestStatus.PENDING) {
            joinRequest.setResponseDate(OffsetDateTime.now());
            
            // If the status is being updated to ACCEPTED, add the player to the team
            if (dto.getStatus() == RequestStatus.ACCEPTED) {
                teamService.addPlayerToTeam(joinRequest.getTeamId(), joinRequest.getPlayerId());
            }
        }
        
        joinRequest = joinRequestRepository.save(joinRequest);
        return joinRequestMapper.toDto(joinRequest);
    }

    /**
     * Delete a join request.
     *
     * @param id Join request ID
     * @throws ResourceNotFoundException if join request not found
     */
    @Transactional
    public void deleteJoinRequest(UUID id) {
        if (!joinRequestRepository.existsById(id)) {
            throw new ResourceNotFoundException("Join request not found with id: " + id);
        }
        joinRequestRepository.deleteById(id);
    }

    /**
     * Find join requests by team ID.
     *
     * @param teamId Team ID
     * @return List of join requests for the specified team as DTOs
     */
    public List<JoinRequestDto> findJoinRequestsByTeam(UUID teamId) {
        return joinRequestRepository.findByTeamId(teamId).stream()
                .map(joinRequestMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find join requests by player ID.
     *
     * @param playerId Player ID
     * @return List of join requests made by the specified player as DTOs
     */
    public List<JoinRequestDto> findJoinRequestsByPlayer(UUID playerId) {
        return joinRequestRepository.findByPlayerId(playerId).stream()
                .map(joinRequestMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find pending join requests by team ID.
     *
     * @param teamId Team ID
     * @return List of pending join requests for the specified team as DTOs
     */
    public List<JoinRequestDto> findPendingJoinRequestsByTeam(UUID teamId) {
        return joinRequestRepository.findByTeamIdAndStatus(teamId, RequestStatus.PENDING).stream()
                .map(joinRequestMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find pending join requests by player ID.
     *
     * @param playerId Player ID
     * @return List of pending join requests made by the specified player as DTOs
     */
    public List<JoinRequestDto> findPendingJoinRequestsByPlayer(UUID playerId) {
        return joinRequestRepository.findByPlayerIdAndStatus(playerId, RequestStatus.PENDING).stream()
                .map(joinRequestMapper::toDto)
                .collect(Collectors.toList());
    }
}