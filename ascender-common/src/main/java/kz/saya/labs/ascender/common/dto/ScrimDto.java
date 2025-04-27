package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object for Scrim entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrimDto {
    private UUID id;
    private String name;
    private String description;
    private UUID scrimRequestId;
    private int matchNumber;
    private String status;
    private String gameMode;
    private String map;
    private String result;
    private String duration;
    private String matchId;
    private String gameId;
    private UUID creatorId;
    private Set<UUID> teamIds;
    private Set<UUID> playerIds;
    private UUID winnerTeamId;
}