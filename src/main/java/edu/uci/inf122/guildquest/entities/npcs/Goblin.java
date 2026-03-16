package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.*;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

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
        // TODO: Implement goblin movement logic.
    }

    @Override
    public void attack(Entity target) {
        Damage damage = new Damage(Math.max(1, attackPower));

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
    public boolean isAggressive() {
        return true;
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
    public void warCry() {
        page.print("GARBLE GARBLE");
    }
}
