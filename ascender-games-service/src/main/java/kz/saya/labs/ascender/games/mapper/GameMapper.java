package kz.saya.labs.ascender.games.mapper;

import kz.saya.labs.ascender.common.dto.GameDto;
import kz.saya.labs.ascender.games.entity.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Game entity and GameDto.
 */
@Component
public class GameMapper {

    /**
     * Convert Game entity to GameDto.
     *
     * @param game Game entity
     * @return GameDto
     */
    public GameDto toDto(Game game) {
        if (game == null) {
            return null;
        }
        
        return new GameDto(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getIcon(),
                game.getBackground(),
                game.getLogo(),
                game.getWebsite(),
                game.isScrimable()
        );
    }

    /**
     * Convert GameDto to Game entity.
     *
     * @param dto GameDto
     * @return Game entity
     */
    public Game toEntity(GameDto dto) {
        if (dto == null) {
            return null;
        }
        
        Game game = new Game();
        game.setId(dto.getId());
        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game.setIcon(dto.getIcon());
        game.setBackground(dto.getBackground());
        game.setLogo(dto.getLogo());
        game.setWebsite(dto.getWebsite());
        game.setScrimable(dto.isScrimable());
        
        return game;
    }

    /**
     * Convert list of Game entities to list of GameDtos.
     *
     * @param games List of Game entities
     * @return List of GameDtos
     */
    public List<GameDto> toDtoList(List<Game> games) {
        if (games == null) {
            return null;
        }
        
        return games.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of GameDtos to list of Game entities.
     *
     * @param dtos List of GameDtos
     * @return List of Game entities
     */
    public List<Game> toEntityList(List<GameDto> dtos) {
        if (dtos == null) {
            return null;
        }
        
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}