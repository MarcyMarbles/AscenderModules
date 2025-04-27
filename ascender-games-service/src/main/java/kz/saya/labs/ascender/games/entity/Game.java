package kz.saya.labs.ascender.games.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kz.saya.labs.ascender.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a game in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    private String name;
    private String description;
    private String icon;
    private String background;
    private String logo;
    private String website;
    private boolean scrimable = true;
}