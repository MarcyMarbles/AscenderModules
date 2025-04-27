package kz.saya.labs.ascender.tournament.entity;

import jakarta.persistence.*;
import kz.saya.labs.ascender.common.model.BaseEntity;
import kz.saya.labs.ascender.tournament.enums.TechResult;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Entity representing a team's score in a tournament match.
 */
@Getter
@Setter
@Entity
@Table(name = "tournament_team_scores")
public class TournamentTeamScore extends BaseEntity {
    
    @Column(name = "match_id", nullable = false)
    private UUID matchId;
    
    @Column(name = "team_id", nullable = false)
    private UUID teamId;
    
    private Integer score = 0;
    
    private Integer position; // Final position in the match (1st, 2nd, 3rd, etc.)
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechResult techResult = TechResult.NONE;
}