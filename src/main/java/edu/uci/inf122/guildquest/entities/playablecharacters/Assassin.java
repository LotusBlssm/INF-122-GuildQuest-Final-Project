package edu.uci.inf122.guildquest.entities.playablecharacters;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.interfaces.CanAttack;

public class Assassin extends PlayableCharacter implements CanAttack {
    private static Assassin instance;

    private final double criticalHitChance;
    private final int attackPower;

    private Assassin(String name) {
        super(name, 80, 1, "Assassin");
        this.criticalHitChance = 0.50;
        this.attackPower = 20;
    }

    public static Assassin getInstance(String name){
        if (instance == null) {
            instance = new Assassin(name);
        }
        return instance;
    }

    @Override
    public void act() {
        // Placeholder for assassin-specific behavior.
    }

    @Override
    public void move() {
        // Placeholder for assassin-specific movement behavior.
    }

    @Override
    public void receiveHealing(int amount) {
        int healing = Math.max(0, amount);
        setHealth(getHealth() + healing);
        System.out.println(getName() + " receives " + healing + " healing. Current health: " + getHealth());
    }

    @Override
    public void takeDamage(int damage) {
        int adjustedDamage = Math.max(0, damage);
        setHealth(getHealth() - adjustedDamage);
        System.out.println(getName() + " takes " + adjustedDamage + " damage. Remaining health: " + getHealth());
    }

    @Override
    public void attack(Entity target) {
        int damageDealt = attackPower;
        if (Math.random() > criticalHitChance) {
            damageDealt *= 2;
            System.out.println(getName() + " lands a critical hit!");
        }
        dealDamage(target, damageDealt);
    }
}