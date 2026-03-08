package edu.uci.inf122.guildquest.entities.npcs;

public class Goblin extends NPC implements Hostile {

    public Goblin(String name, int health, int level) {
        super(name, health, level);
    }

    @Override
    public void attack() {
        System.out.println(getName() + " attacks!");
    }

    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        System.out.println(getName() + " takes " + damage + " damage. Health: " + getHealth());
    }
}
