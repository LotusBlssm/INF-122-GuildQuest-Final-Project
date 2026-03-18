package edu.uci.inf122.guildquest.entities.playablecharacters;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.*;

public class Cleric extends PlayableCharacter {
    private static Cleric instance;
    
    private final DecimalAmount healingPower;

    protected Cleric(Name name) {
        super(name, new Health(90), new Level(1), new CharacterClass("Cleric"));
        this.healingPower = new DecimalAmount(20);
    }

    public static Cleric getInstance(Name name) {
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
    public void receiveHealing(Amount amount) {
        heal(amount);
        System.out.println(getName() + " receives " + amount + " healing. Current health: " + getHealth());
    }

    @Override
    public void takeDamage(Damage damage) {
        getHealth().reduceBy(damage);
        System.out.println(getName() + " takes " + damage + " damage. Remaining health: " + getHealth());

    }

    public void heal(Amount amount, PlayableCharacter target) {
        target.heal(amount);
        System.out.println(getName() + " heals " + target.getName() + " for " + amount.getCount() + " health.");
    }
    public void attack(Entity target){
        System.out.println("Clerics are pacifists, and cannot attack");
    }

    public DecimalAmount getHealingPower() {
        return healingPower;
    }
}