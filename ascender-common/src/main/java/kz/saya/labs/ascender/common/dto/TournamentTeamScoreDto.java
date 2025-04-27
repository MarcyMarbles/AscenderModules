package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object for TournamentTeamScore entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentTeamScoreDto {
    private UUID id;
    private UUID matchId;
    private UUID teamId;
    private Integer score;
    private Integer position;
    private String techResult; // NONE, WIN, LOSS, DRAW
}