package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.domain_primitives.Amount;
import edu.uci.inf122.guildquest.entities.domain_primitives.Damage;
import edu.uci.inf122.guildquest.entities.domain_primitives.Health;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

public class Princess extends NPC{
    private Amount walkingSpeed;

    public Princess(Name name, Health health, Amount walkingSpeed){
        super(name, health);
        this.walkingSpeed=walkingSpeed;
    }

    @Override
    public void act() {

    }

    @Override
    public void move() {

    }

    @Override
    public void heal(Amount amount) {

    }
}
