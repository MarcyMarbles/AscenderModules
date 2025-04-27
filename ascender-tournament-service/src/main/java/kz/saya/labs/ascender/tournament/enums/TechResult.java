package kz.saya.labs.ascender.tournament.enums;

/**
 * Enum representing technical results for tournament matches.
 */
public enum TechResult {
    NONE("No technical result"),
    WIN("Technical win"),
    LOSS("Technical loss"),
    DRAW("Technical draw");

    private final String description;

    TechResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}