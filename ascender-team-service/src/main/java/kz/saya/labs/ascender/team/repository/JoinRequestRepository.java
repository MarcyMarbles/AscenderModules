package kz.saya.labs.ascender.team.repository;

import kz.saya.labs.ascender.team.entity.JoinRequest;
import kz.saya.labs.ascender.team.entity.JoinRequest.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for JoinRequest entities.
 */
@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, UUID> {
    
    /**
     * Find join requests by team ID.
     *
     * @param teamId Team ID
     * @return List of join requests for the specified team
     */
    List<JoinRequest> findByTeamId(UUID teamId);
    
    /**
     * Find join requests by player ID.
     *
     * @param playerId Player ID
     * @return List of join requests made by the specified player
     */
    List<JoinRequest> findByPlayerId(UUID playerId);
    
    /**
     * Find join requests by team ID and status.
     *
     * @param teamId Team ID
     * @param status Request status
     * @return List of join requests for the specified team with the specified status
     */
    List<JoinRequest> findByTeamIdAndStatus(UUID teamId, RequestStatus status);
    
    /**
     * Find join requests by player ID and status.
     *
     * @param playerId Player ID
     * @param status Request status
     * @return List of join requests made by the specified player with the specified status
     */
    List<JoinRequest> findByPlayerIdAndStatus(UUID playerId, RequestStatus status);
    
    /**
     * Find join requests by team ID and player ID.
     *
     * @param teamId Team ID
     * @param playerId Player ID
     * @return List of join requests for the specified team made by the specified player
     */
    List<JoinRequest> findByTeamIdAndPlayerId(UUID teamId, UUID playerId);
}