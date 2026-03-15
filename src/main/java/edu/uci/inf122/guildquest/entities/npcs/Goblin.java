package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.interfaces.CanAttack;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

public class Goblin extends NPC implements isHostile, CanAttack {
    private int attackPower = 15; // Default attack power, can be overridden by subclasses

    public Goblin(String name, int health) {
        super(name, health);
    }


    @Override
    public void act() {
        // TODO: Implement goblin behavior logic.
    }

    @Override
    public void move() {
        // TODO: Implement goblin movement logic.
    }

    @Override
    public void attack(Entity target) {
        int damage = Math.max(1, attackPower);

        if (target instanceof PlayableCharacter playableTarget) {
            playableTarget.takeDamage(damage);
            return;
        }

        if (target instanceof NPC npcTarget) {
            npcTarget.takeDamage(damage);
            return;
        }

        System.out.println(getName() + " cannot damage that target.");
    }

    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        System.out.println(getName() + " takes " + damage + " damage. Health: " + getHealth());
    }

    @Override
    public void warCry() {
        System.out.println(getName() + " screeches menacingly!");
    }
}
