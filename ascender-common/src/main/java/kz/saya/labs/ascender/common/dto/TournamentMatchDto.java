package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for TournamentMatch entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentMatchDto {
    private UUID id;
    private UUID tournamentId;
    private List<TournamentTeamScoreDto> teamScores;
    private Integer round;
    private String matchNumber;
    private String status; // SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    private OffsetDateTime scheduledTime;
    private OffsetDateTime actualStartTime;
    private OffsetDateTime endTime;
    private UUID matchHistoryId;
}