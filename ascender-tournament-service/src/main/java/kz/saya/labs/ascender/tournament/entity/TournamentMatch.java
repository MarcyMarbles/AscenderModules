package kz.saya.labs.ascender.tournament.entity;

import jakarta.persistence.*;
import kz.saya.labs.ascender.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Entity representing a match in a tournament.
 */
@Getter
@Setter
@Entity
@Table(name = "tournament_matches")
public class TournamentMatch extends BaseEntity {
    
    @Column(name = "tournament_id", nullable = false)
    private UUID tournamentId;
    
    @OneToMany(mappedBy = "matchId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TournamentTeamScore> teamScores = new ArrayList<>();
    
    private Integer round; // Round number in the tournament
    private String matchNumber; // Identifier for the match (e.g., "Quarter-final 1")
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status = MatchStatus.SCHEDULED;
    
    private OffsetDateTime scheduledTime;
    private OffsetDateTime actualStartTime;
    private OffsetDateTime endTime;
    
    @Column(name = "match_history_id")
    private UUID matchHistoryId; // Link to match history if available
    
    /**
     * Enum for match status.
     */
    public enum MatchStatus {
        SCHEDULED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }
    
    /**
     * Get the winner of the match.
     * 
     * @return UUID of the winning team, or null if there is no winner
     */
    public UUID getWinner() {
        if (status != MatchStatus.COMPLETED || teamScores.isEmpty()) {
            return null;
        }
        
        // Check for technical wins first
        Optional<TournamentTeamScore> techWinner = teamScores.stream()
                .filter(ts -> ts.getTechResult() == kz.saya.labs.ascender.tournament.enums.TechResult.WIN)
                .findFirst();
        
        if (techWinner.isPresent()) {
            return techWinner.get().getTeamId();
        }
        
        // If no technical winner, get the team with the highest score
        return teamScores.stream()
                .max(Comparator.comparing(TournamentTeamScore::getScore))
                .map(TournamentTeamScore::getTeamId)
                .orElse(null);
    }
    
    /**
     * Check if the match is a draw.
     * 
     * @return true if the match is a draw, false otherwise
     */
    public boolean isDraw() {
        if (status != MatchStatus.COMPLETED || teamScores.isEmpty()) {
            return false;
        }
        
        // Check if any team has a technical win
        boolean hasTechWin = teamScores.stream()
                .anyMatch(ts -> ts.getTechResult() == kz.saya.labs.ascender.tournament.enums.TechResult.WIN);
        
        if (hasTechWin) {
            return false;
        }
        
        // Check if all teams have the same score
        Integer firstScore = teamScores.get(0).getScore();
        return teamScores.stream()
                .allMatch(ts -> ts.getScore().equals(firstScore));
    }
    
    /**
     * Add a team to the match.
     * 
     * @param teamId ID of the team to add
     */
    public void addTeam(UUID teamId) {
        TournamentTeamScore score = new TournamentTeamScore();
        score.setMatchId(this.getId());
        score.setTeamId(teamId);
        teamScores.add(score);
    }
    
    /**
     * Update the score for a team.
     * 
     * @param teamId ID of the team
     * @param score New score
     */
    public void updateScore(UUID teamId, Integer score) {
        teamScores.stream()
                .filter(ts -> ts.getTeamId().equals(teamId))
                .findFirst()
                .ifPresent(ts -> ts.setScore(score));
    }
    
    /**
     * Set a technical result for a team.
     * 
     * @param teamId ID of the team
     * @param result Technical result
     */
    public void setTechResult(UUID teamId, kz.saya.labs.ascender.tournament.enums.TechResult result) {
        teamScores.stream()
                .filter(ts -> ts.getTeamId().equals(teamId))
                .findFirst()
                .ifPresent(ts -> ts.setTechResult(result));
    }
}