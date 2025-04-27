package kz.saya.labs.ascender.player.entity;

import jakarta.persistence.*;
import kz.saya.labs.ascender.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a player profile in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    
    @Column(nullable = false)
    private String callingName;
    
    private String fullName;
    private String email;
    private String steamId;
    private String discordId;
    private String twitchUsername;
    private String youtubeChannel;
    
    @Column(length = 1000)
    private String bio;
    
    private LocalDate birthDate;
    private String country;
    private String city;
    private String language;
    
    @Column(name = "avatar_id")
    private UUID avatarId;
    
    @Column(name = "profile_background_id")
    private UUID profileBackgroundId;
    
    private String skillLevel;
    private Integer totalMatchesPlayed;
    private Integer totalWins;
    private Double winRate;
    
    @ElementCollection
    @CollectionTable(name = "player_preferred_games", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "game_id")
    private Set<UUID> preferredGameIds = new HashSet<>();
    
    @ElementCollection
    @CollectionTable(name = "player_achievements", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "achievement")
    private Set<String> achievements = new HashSet<>();
    
    private boolean lookingForTeam;
    private String availability;
    private String timezone;
    
    @Column(name = "user_id", nullable = false)
    private UUID userId;
}