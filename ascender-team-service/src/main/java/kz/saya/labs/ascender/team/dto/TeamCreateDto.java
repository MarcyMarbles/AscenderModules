package kz.saya.labs.ascender.team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

/**
 * DTO for creating a new team with minimal required fields.
 */
@Data
public class TeamCreateDto {
    
    @NotBlank(message = "Team name is required")
    private String name;
    
    private String description;
    
    private Set<UUID> gameIds;
    
    private String logo;
    private String background;
    private String website;
    private String discord;
    private String vk;
    private String instagram;
    private String tiktok;
}