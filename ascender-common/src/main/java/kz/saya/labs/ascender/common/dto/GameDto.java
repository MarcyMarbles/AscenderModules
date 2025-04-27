package kz.saya.labs.ascender.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object for Game entities.
 * Used for communication between microservices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private UUID id;
    private String name;
    private String description;
    private String icon;
    private String background;
    private String logo;
    private String website;
    private boolean scrimable = true;
}