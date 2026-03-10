package edu.uci.inf122.guildquest.entities;

public abstract class Entity {
    protected String name;

    public abstract void act();
    public String getName() {return name;}
}
