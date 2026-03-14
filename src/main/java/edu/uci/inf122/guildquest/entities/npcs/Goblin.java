package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.*;

import static edu.uci.inf122.guildquest.ui.UserUI.page;

public class Goblin extends NPC implements Hostile {
    private Level level;
    public Goblin(Name name, Health health, Level level) {
        super(name, health);
        this.level = level;
    }

    @Override
    public void act() {

    }

    @Override
    public void move() {

    }

    @Override
    public void takeDamage(Damage damage) {
        getHealth().reduceBy(damage);
        System.out.println(getName() + " takes " + damage + " damage. Health: " + getHealth());
    }

    @Override
    public void heal(Amount amount) {
        getHealth().increaseBy(amount);
    }

    @Override
    public void attack(Entity target) {
        System.out.println(getName() + " attacks " + target.getName() + '!');
        // do something with weapon or level
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public void warCry() {
        page.print("GARBLE GARBLE");
    }
}
