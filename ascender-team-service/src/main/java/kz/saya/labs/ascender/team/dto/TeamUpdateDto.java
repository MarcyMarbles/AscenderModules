package kz.saya.labs.ascender.team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

/**
 * DTO for updating an existing team with all available fields.
 */
@Data
public class TeamUpdateDto {
    
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