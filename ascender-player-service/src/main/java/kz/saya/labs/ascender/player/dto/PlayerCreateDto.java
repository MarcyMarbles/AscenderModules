package kz.saya.labs.ascender.player.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * DTO for creating a new player with minimal required fields.
 */
@Data
public class PlayerCreateDto {
    
    @NotBlank(message = "Calling name is required")
    private String callingName;
    
    private String fullName;
    
    @NotBlank(message = "User ID is required")
    private UUID userId;
}