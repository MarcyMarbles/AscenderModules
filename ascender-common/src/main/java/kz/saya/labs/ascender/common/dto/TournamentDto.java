package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object for Tournament entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentDto {
    private UUID id;
    private String name;
    private String description;
    private UUID gameId;
    private String format; // BO1, BO3, BO5
    private Integer maxTeams;
    private Double prizePool;
    private String currency;
    private Integer maxSkill;
    private boolean bStage; // bracket stage
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String status; // CREATED, REGISTRATION_OPEN, REGISTRATION_CLOSED, IN_PROGRESS, COMPLETED, CANCELLED
    private Set<UUID> teamIds;
}