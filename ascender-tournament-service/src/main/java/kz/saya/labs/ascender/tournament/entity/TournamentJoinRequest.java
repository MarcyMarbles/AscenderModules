package kz.saya.labs.ascender.tournament.entity;

import jakarta.persistence.*;
import kz.saya.labs.ascender.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Entity representing a request to join a tournament.
 */
@Getter
@Setter
@Entity
@Table(name = "tournament_join_requests")
public class TournamentJoinRequest extends BaseEntity {
    
    @Column(name = "tournament_id", nullable = false)
    private UUID tournamentId;
    
    @Column(name = "team_id", nullable = false)
    private UUID teamId;
    
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