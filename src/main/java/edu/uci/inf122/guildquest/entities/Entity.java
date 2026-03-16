package edu.uci.inf122.guildquest.entities;

import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

public abstract class Entity {
    protected Name name;
    public Entity(Name name){
        this.name=name;
    }

    public abstract void act();
    public Name getName() {return name;}
}
