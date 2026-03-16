package edu.uci.inf122.guildquest.entities.playablecharacters;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.interfaces.CanAttack;

public class Knight extends PlayableCharacter implements CanAttack {
    private static Knight instance;

    private final double damageReductionMultiplier;
    private final double healingMultiplier;
    private final int attackPower;

    private Knight(String name) {
        super(name, 100, 1, "Knight");
        this.damageReductionMultiplier = 0.85;
        this.healingMultiplier = 1.15;
        this.attackPower = 15;
    }

    public static Knight getInstance(String name) {
        if (instance == null) {
            instance = new Knight(name);
        }
        return instance;
    }

    @Override
    public void act() {
        // Placeholder for knight-specific behavior.
    }

    @Override
    public void move() {
        // Placeholder for knight-specific movement behavior.
    }

    @Override
    public void receiveHealing(int amount) {
        int increasedHealing = (int) (Math.max(0, amount) * healingMultiplier);
        setHealth(getHealth() + increasedHealing);
        System.out.println(getName() + " receives " + increasedHealing + " healing. Current health: " + getHealth());
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = (int) (Math.max(0, damage) * damageReductionMultiplier);
        setHealth(getHealth() - reducedDamage);
        System.out.println(getName() + " takes " + reducedDamage + " damage. Remaining health: " + getHealth());
    }

    @Override
    public void attack(Entity target) {
        dealDamage(target, attackPower);
    }
}