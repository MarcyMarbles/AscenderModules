package kz.saya.labs.ascender.team.service;

import kz.saya.labs.ascender.common.dto.TeamDto;
import kz.saya.labs.ascender.common.exception.ResourceNotFoundException;
import kz.saya.labs.ascender.team.dto.TeamCreateDto;
import kz.saya.labs.ascender.team.dto.TeamUpdateDto;
import kz.saya.labs.ascender.team.entity.Team;
import kz.saya.labs.ascender.team.mapper.TeamMapper;
import kz.saya.labs.ascender.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for managing teams.
 */
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    /**
     * Get all teams.
     *
     * @return List of all teams as DTOs
     */
    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get a team by ID.
     *
     * @param id Team ID
     * @return Team as DTO
     * @throws ResourceNotFoundException if team not found
     */
    public TeamDto getTeamById(UUID id) {
        return teamRepository.findById(id)
                .map(teamMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
    }

    /**
     * Create a new team.
     *
     * @param createDto DTO with team data
     * @param creatorId ID of the player creating the team
     * @return Created team as DTO
     */
    @Transactional
    public TeamDto createTeam(TeamCreateDto createDto, UUID creatorId) {
        Team team = teamMapper.createEntityFromDto(createDto);
        team.setCreatorId(creatorId);
        
        // Add the creator as a player in the team
        team.getPlayerIds().add(creatorId);
        
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    /**
     * Update a team.
     *
     * @param id Team ID
     * @param updateDto DTO with updated team data
     * @return Updated team as DTO
     * @throws ResourceNotFoundException if team not found
     */
    @Transactional
    public TeamDto updateTeam(UUID id, TeamUpdateDto updateDto) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
        
        team = teamMapper.updateEntityFromDto(team, updateDto);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    /**
     * Delete a team.
     *
     * @param id Team ID
     * @throws ResourceNotFoundException if team not found
     */
    @Transactional
    public void deleteTeam(UUID id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team not found with id: " + id);
        }
        teamRepository.deleteById(id);
    }

    /**
     * Add a player to a team.
     *
     * @param teamId Team ID
     * @param playerId Player ID
     * @return Updated team as DTO
     * @throws ResourceNotFoundException if team not found
     */
    @Transactional
    public TeamDto addPlayerToTeam(UUID teamId, UUID playerId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
        
        team.getPlayerIds().add(playerId);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    /**
     * Remove a player from a team.
     *
     * @param teamId Team ID
     * @param playerId Player ID
     * @return Updated team as DTO
     * @throws ResourceNotFoundException if team not found
     */
    @Transactional
    public TeamDto removePlayerFromTeam(UUID teamId, UUID playerId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
        
        team.getPlayerIds().remove(playerId);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    /**
     * Find teams by game ID.
     *
     * @param gameId Game ID
     * @return List of teams for the specified game as DTOs
     */
    public List<TeamDto> findTeamsByGame(UUID gameId) {
        return teamRepository.findByGameId(gameId).stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find teams by creator ID.
     *
     * @param creatorId Creator ID
     * @return List of teams created by the specified player as DTOs
     */
    public List<TeamDto> findTeamsByCreator(UUID creatorId) {
        return teamRepository.findByCreatorId(creatorId).stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find teams that a player is a member of.
     *
     * @param playerId Player ID
     * @return List of teams that the player is a member of as DTOs
     */
    public List<TeamDto> findTeamsByPlayer(UUID playerId) {
        return teamRepository.findByPlayerId(playerId).stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Check if a player is the creator of a team.
     *
     * @param teamId Team ID
     * @param playerId Player ID
     * @return true if the player is the creator of the team, false otherwise
     */
    public boolean isTeamCreator(UUID teamId, UUID playerId) {
        return teamRepository.findById(teamId)
                .map(team -> team.getCreatorId().equals(playerId))
                .orElse(false);
    }

    /**
     * Check if a player is a member of a team.
     *
     * @param teamId Team ID
     * @param playerId Player ID
     * @return true if the player is a member of the team, false otherwise
     */
    public boolean isTeamMember(UUID teamId, UUID playerId) {
        return teamRepository.findById(teamId)
                .map(team -> team.getPlayerIds().contains(playerId))
                .orElse(false);
    }
}