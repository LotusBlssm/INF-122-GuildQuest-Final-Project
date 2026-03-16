package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;

public abstract class NPC extends Entity {
    private int health;

    public NPC(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    public abstract void act();

    public abstract void move();

    public abstract void takeDamage(int damage);
}