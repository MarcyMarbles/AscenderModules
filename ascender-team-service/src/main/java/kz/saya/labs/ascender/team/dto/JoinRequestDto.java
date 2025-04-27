package kz.saya.labs.ascender.team.dto;

import jakarta.validation.constraints.NotNull;
import kz.saya.labs.ascender.team.entity.JoinRequest.RequestStatus;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO for join requests.
 */
@Data
public class JoinRequestDto {
    
    private UUID id;
    
    @NotNull(message = "Team ID is required")
    private UUID teamId;
    
    @NotNull(message = "Player ID is required")
    private UUID playerId;
    
    private RequestStatus status = RequestStatus.PENDING;
    
    private OffsetDateTime requestDate;
    
    private OffsetDateTime responseDate;
    
    private String message;
    
    private String responseMessage;
}