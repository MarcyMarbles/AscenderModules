package kz.saya.labs.ascender.team.entity;

import jakarta.persistence.*;
import kz.saya.labs.ascender.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a team in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @ElementCollection
    @CollectionTable(name = "team_games", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "game_id")
    private Set<UUID> gameIds = new HashSet<>();
    
    private String logo;
    private String background;
    private String website;
    private String discord;
    private String vk;
    private String instagram;
    private String tiktok;
    
    @Column(name = "creator_id", nullable = false)
    private UUID creatorId;
    
    @ElementCollection
    @CollectionTable(name = "team_players", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "player_id")
    private Set<UUID> playerIds = new HashSet<>();
}