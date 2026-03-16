package edu.uci.inf122.guildquest.entities.domain_primitives;

public class Place {
    private Name name;
    public Place(Name name){
        this.name=name;
    }
    @Override
    public String toString() {
        return name.toString();
    }
}
