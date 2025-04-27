package kz.saya.labs.ascender.player.mapper;

import kz.saya.labs.ascender.common.dto.PlayerDto;
import kz.saya.labs.ascender.player.dto.PlayerCreateDto;
import kz.saya.labs.ascender.player.dto.PlayerUpdateDto;
import kz.saya.labs.ascender.player.entity.Player;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * Mapper for converting between Player entities and DTOs.
 */
@Component
public class PlayerMapper {

    /**
     * Convert a Player entity to a PlayerDto.
     *
     * @param player Player entity
     * @return PlayerDto
     */
    public PlayerDto toDto(Player player) {
        if (player == null) {
            return null;
        }

        PlayerDto dto = new PlayerDto();
        dto.setId(player.getId());
        dto.setCallingName(player.getCallingName());
        dto.setFullName(player.getFullName());
        dto.setEmail(player.getEmail());
        dto.setSteamId(player.getSteamId());
        dto.setDiscordId(player.getDiscordId());
        dto.setTwitchUsername(player.getTwitchUsername());
        dto.setYoutubeChannel(player.getYoutubeChannel());
        dto.setBio(player.getBio());
        dto.setBirthDate(player.getBirthDate());
        dto.setCountry(player.getCountry());
        dto.setCity(player.getCity());
        dto.setLanguage(player.getLanguage());
        dto.setSkillLevel(player.getSkillLevel());
        dto.setTotalMatchesPlayed(player.getTotalMatchesPlayed());
        dto.setTotalWins(player.getTotalWins());
        dto.setWinRate(player.getWinRate());
        dto.setPreferredGameIds(player.getPreferredGameIds());
        dto.setAchievements(player.getAchievements());
        dto.setLookingForTeam(player.isLookingForTeam());
        dto.setAvailability(player.getAvailability());
        dto.setTimezone(player.getTimezone());
        dto.setUserId(player.getUserId());
        
        return dto;
    }

    /**
     * Convert a PlayerDto to a Player entity.
     *
     * @param dto PlayerDto
     * @return Player entity
     */
    public Player toEntity(PlayerDto dto) {
        if (dto == null) {
            return null;
        }

        Player player = new Player();
        player.setId(dto.getId());
        player.setCallingName(dto.getCallingName());
        player.setFullName(dto.getFullName());
        player.setEmail(dto.getEmail());
        player.setSteamId(dto.getSteamId());
        player.setDiscordId(dto.getDiscordId());
        player.setTwitchUsername(dto.getTwitchUsername());
        player.setYoutubeChannel(dto.getYoutubeChannel());
        player.setBio(dto.getBio());
        player.setBirthDate(dto.getBirthDate());
        player.setCountry(dto.getCountry());
        player.setCity(dto.getCity());
        player.setLanguage(dto.getLanguage());
        player.setSkillLevel(dto.getSkillLevel());
        player.setTotalMatchesPlayed(dto.getTotalMatchesPlayed());
        player.setTotalWins(dto.getTotalWins());
        player.setWinRate(dto.getWinRate());
        
        if (dto.getPreferredGameIds() != null) {
            player.setPreferredGameIds(dto.getPreferredGameIds());
        } else {
            player.setPreferredGameIds(new HashSet<>());
        }
        
        if (dto.getAchievements() != null) {
            player.setAchievements(dto.getAchievements());
        } else {
            player.setAchievements(new HashSet<>());
        }
        
        player.setLookingForTeam(dto.isLookingForTeam());
        player.setAvailability(dto.getAvailability());
        player.setTimezone(dto.getTimezone());
        player.setUserId(dto.getUserId());
        
        return player;
    }

    /**
     * Create a new Player entity from a PlayerCreateDto.
     *
     * @param createDto PlayerCreateDto
     * @return Player entity
     */
    public Player createEntityFromDto(PlayerCreateDto createDto) {
        if (createDto == null) {
            return null;
        }

        Player player = new Player();
        player.setCallingName(createDto.getCallingName());
        player.setFullName(createDto.getFullName());
        player.setUserId(createDto.getUserId());
        player.setPreferredGameIds(new HashSet<>());
        player.setAchievements(new HashSet<>());
        
        return player;
    }

    /**
     * Update a Player entity from a PlayerUpdateDto.
     *
     * @param player Player entity to update
     * @param updateDto PlayerUpdateDto with new values
     * @return Updated Player entity
     */
    public Player updateEntityFromDto(Player player, PlayerUpdateDto updateDto) {
        if (player == null || updateDto == null) {
            return player;
        }

        player.setCallingName(updateDto.getCallingName());
        player.setFullName(updateDto.getFullName());
        player.setEmail(updateDto.getEmail());
        player.setSteamId(updateDto.getSteamId());
        player.setDiscordId(updateDto.getDiscordId());
        player.setTwitchUsername(updateDto.getTwitchUsername());
        player.setYoutubeChannel(updateDto.getYoutubeChannel());
        player.setBio(updateDto.getBio());
        player.setBirthDate(updateDto.getBirthDate());
        player.setCountry(updateDto.getCountry());
        player.setCity(updateDto.getCity());
        player.setLanguage(updateDto.getLanguage());
        player.setAvatarId(updateDto.getAvatarId());
        player.setProfileBackgroundId(updateDto.getProfileBackgroundId());
        player.setSkillLevel(updateDto.getSkillLevel());
        player.setTotalMatchesPlayed(updateDto.getTotalMatchesPlayed());
        player.setTotalWins(updateDto.getTotalWins());
        player.setWinRate(updateDto.getWinRate());
        
        if (updateDto.getPreferredGameIds() != null) {
            player.setPreferredGameIds(updateDto.getPreferredGameIds());
        }
        
        if (updateDto.getAchievements() != null) {
            player.setAchievements(updateDto.getAchievements());
        }
        
        player.setLookingForTeam(updateDto.isLookingForTeam());
        player.setAvailability(updateDto.getAvailability());
        player.setTimezone(updateDto.getTimezone());
        
        return player;
    }
}