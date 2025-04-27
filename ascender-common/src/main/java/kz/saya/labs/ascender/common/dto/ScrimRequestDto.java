package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for ScrimRequest entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrimRequestDto {
    private UUID id;
    private String name;
    private String description;
    private UUID gameId;
    private UUID teamId;
    private String status;
    private OffsetDateTime acceptedAt;
    private UUID acceptedById;
}