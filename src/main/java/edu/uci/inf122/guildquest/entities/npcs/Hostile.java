package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

import java.util.List;

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
     */
    void warCry();

    default Entity prioritizeAttack(List<Entity> nearby) {
        for (Entity e : nearby){
            if (e instanceof PlayableCharacter){
                return e;
            }
        }
        return null;
    }
}
