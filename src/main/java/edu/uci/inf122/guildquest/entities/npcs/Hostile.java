package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;

/**
 * Hostile interface for NPC classes that have hostile behavior
 */
public interface Hostile {
    int attackPower = 10; // Default attack power for hostile entities

    /**
     * Attacks a target
     * @param target the target to attack
     */
    void attack(Entity target);

    /**
     * Checks if the entity is currently aggressive
     * @return true if aggressive, false otherwise
     */
    boolean isAggressive();

    /**
     * Prints a war cry to intimidate opponents
     * @return true if aggressive, false otherwise
     */
    void warCry();
}
