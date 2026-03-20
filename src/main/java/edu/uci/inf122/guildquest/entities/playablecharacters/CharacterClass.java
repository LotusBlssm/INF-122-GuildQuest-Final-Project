package edu.uci.inf122.guildquest.entities.playablecharacters;

import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

public class CharacterClass {
    private Name name;
    public CharacterClass(Name name){
        this.name=name;
    }
    public CharacterClass(String name){
        this.name = new Name(name);
    }

    public Name getName() {
        return name;
    }

    public String toString() {
        return name.toString();
    }
}
