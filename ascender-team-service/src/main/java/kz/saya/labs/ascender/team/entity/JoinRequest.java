package kz.saya.labs.ascender.team.entity;

import jakarta.persistence.*;
import kz.saya.labs.ascender.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Entity representing a request to join a team.
 */
@Getter
@Setter
@Entity
@Table(name = "join_requests")
public class JoinRequest extends BaseEntity {
    
    @Column(name = "team_id")
    private UUID teamId;
    
    @Column(name = "player_id", nullable = false)
    private UUID playerId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;
    
    @Column(name = "request_date", nullable = false)
    private OffsetDateTime requestDate = OffsetDateTime.now();
    
    @Column(name = "response_date")
    private OffsetDateTime responseDate;
    
    @Column(length = 500)
    private String message;
    
    @Column(name = "response_message", length = 500)
    private String responseMessage;
    
    /**
     * Enum representing the status of a join request.
     */
    public enum RequestStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}