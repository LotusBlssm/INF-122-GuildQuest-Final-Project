package edu.uci.inf122.guildquest.entities.playablecharacters;

public class Cleric extends PlayableCharacter {
    private static Cleric instance;
    
    private final int healingPower;

    protected Cleric(String name) {
        super(name, 90, 1, "Cleric");
        this.healingPower = 20; 
    }

    public static Cleric getInstance(String name) {
        if (instance == null) {
            instance = new Cleric(name);
        }
        return instance;
    }

    @Override
    public void act() {
        // Placeholder for cleric-specific behavior.
    }

    @Override
    public void move() {
        // Placeholder for cleric-specific movement behavior.
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

    public void heal(int amount, PlayableCharacter target) {
        int healing = Math.max(0, amount);
        target.receiveHealing(healing);
        System.out.println(getName() + " heals " + target.getName() + " for " + healing + " health.");
    }
}