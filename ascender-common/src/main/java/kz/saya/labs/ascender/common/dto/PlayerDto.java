package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object for Player entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private UUID id;
    private String callingName;
    private String fullName;
    private String email;
    private String steamId;
    private String discordId;
    private String twitchUsername;
    private String youtubeChannel;
    private String bio;
    private LocalDate birthDate;
    private String country;
    private String city;
    private String language;
    private String skillLevel;
    private Integer totalMatchesPlayed;
    private Integer totalWins;
    private Double winRate;
    private Set<UUID> preferredGameIds;
    private Set<String> achievements;
    private boolean lookingForTeam;
    private String availability;
    private String timezone;
    private UUID userId;
}