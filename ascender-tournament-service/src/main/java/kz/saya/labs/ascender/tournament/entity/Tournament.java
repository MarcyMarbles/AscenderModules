package kz.saya.labs.ascender.tournament.entity;

import jakarta.persistence.*;
import kz.saya.labs.ascender.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a tournament in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "tournaments")
public class Tournament extends BaseEntity {
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "game_id", nullable = false)
    private UUID gameId;
    
    @Enumerated(EnumType.STRING)
    private TournamentFormat format;
    
    private Integer maxTeams;
    private Double prizePool;
    private String currency;
    private Integer maxSkill;
    private boolean bStage; // bracket stage
    
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TournamentStatus status = TournamentStatus.CREATED;
    
    @ElementCollection
    @CollectionTable(name = "tournament_teams", joinColumns = @JoinColumn(name = "tournament_id"))
    @Column(name = "team_id")
    private Set<UUID> teamIds = new HashSet<>();
    
    /**
     * Enum for tournament format.
     */
    public enum TournamentFormat {
        BO1("Best of 1"),
        BO3("Best of 3"),
        BO5("Best of 5");
        
        private final String description;
        
        TournamentFormat(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * Enum for tournament status.
     */
    public enum TournamentStatus {
        CREATED,
        REGISTRATION_OPEN,
        REGISTRATION_CLOSED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }
}