package edu.uci.inf122.guildquest.entities.playablecharacters;

import edu.uci.inf122.guildquest.content.Inventory;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.npcs.NPC;

public abstract class PlayableCharacter extends Entity {
    private int health;
    private final int level;
    private final String characterClass;
    private final Inventory inventory;

    public PlayableCharacter(String name, int health, int level, String characterClass) {
        this.name = name;
        setHealth(health);
        this.level = level;
        this.characterClass = characterClass;
        this.inventory = new Inventory();
    }

    @Override
    public abstract void act();

    public abstract void move();

    public abstract void receiveHealing(int amount);

    public abstract void takeDamage(int damage);

    protected void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    protected void dealDamage(Entity target, int damage) {
        if (target instanceof PlayableCharacter playableTarget) {
            throw new IllegalArgumentException("Cannot attack another player character.");
            return;
        }

        if (target instanceof NPC npcTarget) {
            npcTarget.takeDamage(damage);
        }

        catch (Exception e) {
            System.out.println("An error occurred while trying to deal damage: " + e.getMessage());
        }
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
