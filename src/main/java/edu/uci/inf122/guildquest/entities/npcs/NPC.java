package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Amount;
import edu.uci.inf122.guildquest.entities.domain_primitives.Damage;
import edu.uci.inf122.guildquest.entities.domain_primitives.Health;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

public abstract class NPC extends Entity {
    protected Health health;

    public NPC(Name name, Health health) {
        this.name = name;
        this.health = health;
    }

    public Health getHealth() {
        return health;
    }

    public abstract void act();

    public abstract void move();

    public abstract void takeDamage(Damage damage);

    public abstract void heal(Amount amount);
}