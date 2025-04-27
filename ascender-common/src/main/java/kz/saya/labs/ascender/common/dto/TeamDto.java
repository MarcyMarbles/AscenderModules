package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object for Team entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private UUID id;
    private OffsetDateTime createdTs;
    private OffsetDateTime updatedTs;
    private OffsetDateTime deletedTs;
    private OffsetDateTime startDateTs;
    private OffsetDateTime endDateTs;
    
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
    
    private UUID creatorId;
    private Set<UUID> playerIds;
}