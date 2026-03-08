package Entities.NPCs;

/**
 * Hostile interface for NPC classes that have hostile behavior
 */
public interface Hostile {
    int health = 100; // Default health for hostile entities
    int attackPower = 10; // Default attack power for hostile entities

    /**
     * Attacks a target
     * @param target the target to attack
     */
    void attack(Object target);

    /**
     * Gets the aggression level of the hostile entity
     * @return the aggression level
     */
    int getAggressionLevel();

    /**
     * Sets the aggression level of the hostile entity
     * @param level the aggression level to set
     */
    void setAggressionLevel(int level);

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
