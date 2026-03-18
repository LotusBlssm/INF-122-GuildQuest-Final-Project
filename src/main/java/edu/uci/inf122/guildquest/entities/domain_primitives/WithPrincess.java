package edu.uci.inf122.guildquest.entities.domain_primitives;

/**
 * Domain primitive wrapper for whether a playable character is escorting the princess.
 */
public class WithPrincess {
    private final boolean withPrincess;

    public WithPrincess(boolean withPrincess) {
        this.withPrincess = withPrincess;
    }

    public boolean isWithPrincess() {
        return withPrincess;
    }

    public WithPrincess startEscorting() {
        return new WithPrincess(true);
    }

    public WithPrincess stopEscorting() {
        return new WithPrincess(false);
    }
}