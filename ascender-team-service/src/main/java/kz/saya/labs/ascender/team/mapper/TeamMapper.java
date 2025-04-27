package kz.saya.labs.ascender.team.mapper;

import kz.saya.labs.ascender.common.dto.TeamDto;
import kz.saya.labs.ascender.team.dto.TeamCreateDto;
import kz.saya.labs.ascender.team.dto.TeamUpdateDto;
import kz.saya.labs.ascender.team.entity.Team;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * Mapper for converting between Team entities and DTOs.
 */
@Component
public class TeamMapper {

    /**
     * Convert a Team entity to a TeamDto.
     *
     * @param team Team entity
     * @return TeamDto
     */
    public TeamDto toDto(Team team) {
        if (team == null) {
            return null;
        }

        TeamDto dto = new TeamDto();
        dto.setId(team.getId());
        dto.setCreatedTs(team.getCreatedTs());
        dto.setUpdatedTs(team.getUpdatedTs());
        dto.setDeletedTs(team.getDeletedTs());
        dto.setStartDateTs(team.getStartDateTs());
        dto.setEndDateTs(team.getEndDateTs());
        dto.setName(team.getName());
        dto.setDescription(team.getDescription());
        dto.setGameIds(team.getGameIds());
        dto.setLogo(team.getLogo());
        dto.setBackground(team.getBackground());
        dto.setWebsite(team.getWebsite());
        dto.setDiscord(team.getDiscord());
        dto.setVk(team.getVk());
        dto.setInstagram(team.getInstagram());
        dto.setTiktok(team.getTiktok());
        dto.setCreatorId(team.getCreatorId());
        dto.setPlayerIds(team.getPlayerIds());
        
        return dto;
    }

    /**
     * Convert a TeamDto to a Team entity.
     *
     * @param dto TeamDto
     * @return Team entity
     */
    public Team toEntity(TeamDto dto) {
        if (dto == null) {
            return null;
        }

        Team team = new Team();
        team.setId(dto.getId());
        team.setCreatedTs(dto.getCreatedTs());
        team.setUpdatedTs(dto.getUpdatedTs());
        team.setDeletedTs(dto.getDeletedTs());
        team.setStartDateTs(dto.getStartDateTs());
        team.setEndDateTs(dto.getEndDateTs());
        team.setName(dto.getName());
        team.setDescription(dto.getDescription());
        
        if (dto.getGameIds() != null) {
            team.setGameIds(dto.getGameIds());
        } else {
            team.setGameIds(new HashSet<>());
        }
        
        team.setLogo(dto.getLogo());
        team.setBackground(dto.getBackground());
        team.setWebsite(dto.getWebsite());
        team.setDiscord(dto.getDiscord());
        team.setVk(dto.getVk());
        team.setInstagram(dto.getInstagram());
        team.setTiktok(dto.getTiktok());
        team.setCreatorId(dto.getCreatorId());
        
        if (dto.getPlayerIds() != null) {
            team.setPlayerIds(dto.getPlayerIds());
        } else {
            team.setPlayerIds(new HashSet<>());
        }
        
        return team;
    }

    /**
     * Create a new Team entity from a TeamCreateDto.
     *
     * @param createDto TeamCreateDto
     * @return Team entity
     */
    public Team createEntityFromDto(TeamCreateDto createDto) {
        if (createDto == null) {
            return null;
        }

        Team team = new Team();
        team.setName(createDto.getName());
        team.setDescription(createDto.getDescription());
        
        if (createDto.getGameIds() != null) {
            team.setGameIds(createDto.getGameIds());
        } else {
            team.setGameIds(new HashSet<>());
        }
        
        team.setLogo(createDto.getLogo());
        team.setBackground(createDto.getBackground());
        team.setWebsite(createDto.getWebsite());
        team.setDiscord(createDto.getDiscord());
        team.setVk(createDto.getVk());
        team.setInstagram(createDto.getInstagram());
        team.setTiktok(createDto.getTiktok());
        team.setPlayerIds(new HashSet<>());
        
        return team;
    }

    /**
     * Update a Team entity from a TeamUpdateDto.
     *
     * @param team Team entity to update
     * @param updateDto TeamUpdateDto with new values
     * @return Updated Team entity
     */
    public Team updateEntityFromDto(Team team, TeamUpdateDto updateDto) {
        if (team == null || updateDto == null) {
            return team;
        }

        team.setName(updateDto.getName());
        team.setDescription(updateDto.getDescription());
        
        if (updateDto.getGameIds() != null) {
            team.setGameIds(updateDto.getGameIds());
        }
        
        team.setLogo(updateDto.getLogo());
        team.setBackground(updateDto.getBackground());
        team.setWebsite(updateDto.getWebsite());
        team.setDiscord(updateDto.getDiscord());
        team.setVk(updateDto.getVk());
        team.setInstagram(updateDto.getInstagram());
        team.setTiktok(updateDto.getTiktok());
        
        return team;
    }
}