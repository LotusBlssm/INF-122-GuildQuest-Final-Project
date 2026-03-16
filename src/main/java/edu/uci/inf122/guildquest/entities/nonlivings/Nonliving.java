package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.entities.Entity;

public class Nonliving extends Entity {
    private final String description;

    public Nonliving(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void act() {
        // Default no-op for static world objects.
    }

}
