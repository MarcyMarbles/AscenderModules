package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for tournament-related JoinRequest entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentJoinRequestDto {
    private UUID id;
    private UUID tournamentId;
    private UUID teamId;
    private String status; // PENDING, ACCEPTED, REJECTED
    private OffsetDateTime requestDate;
    private OffsetDateTime responseDate;
    private String message;
    private String responseMessage;
}