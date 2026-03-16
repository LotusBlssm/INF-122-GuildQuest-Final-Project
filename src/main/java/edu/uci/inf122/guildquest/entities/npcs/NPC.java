package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Amount;
import edu.uci.inf122.guildquest.entities.domain_primitives.Damage;
import edu.uci.inf122.guildquest.entities.domain_primitives.Health;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

public abstract class NPC extends Entity {
    protected Health health;

    public NPC(Name name, Health health) {
        super(name);
        this.health = health;
    }

    public Health getHealth() {
        return health;
    }

    public abstract void act();

    public abstract void move();

    public void takeDamage(Damage damage){
        health.reduceBy(damage);
        if (isDead()){
            System.out.println(name + "died!");
        } else
            System.out.println(getName() + " takes " + damage + " damage. Health: " + getHealth());
    }

    public abstract void heal(Amount amount);

    public boolean isDead() {return health.getHealth()==0;}
}