package kz.saya.labs.ascender.player.service;

import kz.saya.labs.ascender.common.dto.PlayerDto;
import kz.saya.labs.ascender.common.exception.ResourceNotFoundException;
import kz.saya.labs.ascender.player.dto.PlayerCreateDto;
import kz.saya.labs.ascender.player.dto.PlayerUpdateDto;
import kz.saya.labs.ascender.player.entity.Player;
import kz.saya.labs.ascender.player.mapper.PlayerMapper;
import kz.saya.labs.ascender.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for managing player profiles.
 */
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    /**
     * Get all players.
     *
     * @return List of all players as DTOs
     */
    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get a player by ID.
     *
     * @param id Player ID
     * @return Player as DTO
     * @throws ResourceNotFoundException if player not found
     */
    public PlayerDto getPlayerById(UUID id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
    }

    /**
     * Create a new player.
     *
     * @param createDto DTO with player data
     * @return Created player as DTO
     */
    @Transactional
    public PlayerDto createPlayer(PlayerCreateDto createDto) {
        Player player = playerMapper.createEntityFromDto(createDto);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    /**
     * Update a player.
     *
     * @param id Player ID
     * @param updateDto DTO with updated player data
     * @return Updated player as DTO
     * @throws ResourceNotFoundException if player not found
     */
    @Transactional
    public PlayerDto updatePlayer(UUID id, PlayerUpdateDto updateDto) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
        
        player = playerMapper.updateEntityFromDto(player, updateDto);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    /**
     * Delete a player.
     *
     * @param id Player ID
     * @throws ResourceNotFoundException if player not found
     */
    @Transactional
    public void deletePlayer(UUID id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }

    /**
     * Find players by skill level.
     *
     * @param skillLevel Skill level to search for
     * @return List of players with the specified skill level as DTOs
     */
    public List<PlayerDto> findPlayersBySkillLevel(String skillLevel) {
        return playerRepository.findBySkillLevel(skillLevel).stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find players looking for a team.
     *
     * @return List of players looking for a team as DTOs
     */
    public List<PlayerDto> findPlayersLookingForTeam() {
        return playerRepository.findByLookingForTeamTrue().stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find a player by user ID.
     *
     * @param userId User ID
     * @return Player as DTO
     * @throws ResourceNotFoundException if player not found
     */
    public PlayerDto findPlayerByUserId(UUID userId) {
        return playerRepository.findByUserId(userId)
                .map(playerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with user id: " + userId));
    }

    /**
     * Update a player's avatar.
     *
     * @param id Player ID
     * @param avatarId Avatar file ID
     * @return Updated player as DTO
     * @throws ResourceNotFoundException if player not found
     */
    @Transactional
    public PlayerDto updatePlayerAvatar(UUID id, UUID avatarId) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
        
        player.setAvatarId(avatarId);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }

    /**
     * Update a player's profile background.
     *
     * @param id Player ID
     * @param backgroundId Background file ID
     * @return Updated player as DTO
     * @throws ResourceNotFoundException if player not found
     */
    @Transactional
    public PlayerDto updatePlayerBackground(UUID id, UUID backgroundId) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
        
        player.setProfileBackgroundId(backgroundId);
        player = playerRepository.save(player);
        return playerMapper.toDto(player);
    }
}