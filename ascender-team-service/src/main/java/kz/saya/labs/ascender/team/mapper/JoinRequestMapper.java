package kz.saya.labs.ascender.team.mapper;

import kz.saya.labs.ascender.team.dto.JoinRequestDto;
import kz.saya.labs.ascender.team.entity.JoinRequest;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between JoinRequest entities and DTOs.
 */
@Component
public class JoinRequestMapper {

    /**
     * Convert a JoinRequest entity to a JoinRequestDto.
     *
     * @param joinRequest JoinRequest entity
     * @return JoinRequestDto
     */
    public JoinRequestDto toDto(JoinRequest joinRequest) {
        if (joinRequest == null) {
            return null;
        }

        JoinRequestDto dto = new JoinRequestDto();
        dto.setId(joinRequest.getId());
        dto.setTeamId(joinRequest.getTeamId());
        dto.setPlayerId(joinRequest.getPlayerId());
        dto.setStatus(joinRequest.getStatus());
        dto.setRequestDate(joinRequest.getRequestDate());
        dto.setResponseDate(joinRequest.getResponseDate());
        dto.setMessage(joinRequest.getMessage());
        dto.setResponseMessage(joinRequest.getResponseMessage());
        
        return dto;
    }

    /**
     * Convert a JoinRequestDto to a JoinRequest entity.
     *
     * @param dto JoinRequestDto
     * @return JoinRequest entity
     */
    public JoinRequest toEntity(JoinRequestDto dto) {
        if (dto == null) {
            return null;
        }

        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setId(dto.getId());
        joinRequest.setTeamId(dto.getTeamId());
        joinRequest.setPlayerId(dto.getPlayerId());
        joinRequest.setStatus(dto.getStatus());
        joinRequest.setRequestDate(dto.getRequestDate() != null ? dto.getRequestDate() : joinRequest.getRequestDate());
        joinRequest.setResponseDate(dto.getResponseDate());
        joinRequest.setMessage(dto.getMessage());
        joinRequest.setResponseMessage(dto.getResponseMessage());
        
        return joinRequest;
    }

    /**
     * Create a new JoinRequest entity from a JoinRequestDto.
     *
     * @param dto JoinRequestDto
     * @return JoinRequest entity
     */
    public JoinRequest createEntityFromDto(JoinRequestDto dto) {
        if (dto == null) {
            return null;
        }

        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setTeamId(dto.getTeamId());
        joinRequest.setPlayerId(dto.getPlayerId());
        joinRequest.setMessage(dto.getMessage());
        
        return joinRequest;
    }

    /**
     * Update a JoinRequest entity from a JoinRequestDto.
     *
     * @param joinRequest JoinRequest entity to update
     * @param dto JoinRequestDto with new values
     * @return Updated JoinRequest entity
     */
    public JoinRequest updateEntityFromDto(JoinRequest joinRequest, JoinRequestDto dto) {
        if (joinRequest == null || dto == null) {
            return joinRequest;
        }

        if (dto.getStatus() != null) {
            joinRequest.setStatus(dto.getStatus());
            if (dto.getStatus() != JoinRequest.RequestStatus.PENDING) {
                joinRequest.setResponseDate(dto.getResponseDate() != null ? dto.getResponseDate() : joinRequest.getResponseDate());
            }
        }
        
        if (dto.getResponseMessage() != null) {
            joinRequest.setResponseMessage(dto.getResponseMessage());
        }
        
        return joinRequest;
    }
}