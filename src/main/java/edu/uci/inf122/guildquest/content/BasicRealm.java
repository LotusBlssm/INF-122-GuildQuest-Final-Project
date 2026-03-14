package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public class BasicRealm implements Realm {
    private UUID realmID;
    private String name;
    private String description;
    private TimeRule timeRule;
    private Grid grid;

    public BasicRealm(UUID realmID, String name, String description, TimeRule timeRule, Grid grid) {
        this.realmID = realmID;
        this.name = name;
        this.description = description;
        this.timeRule = timeRule;
        this.grid = grid;
    }

    @Override
    public UUID getRealmID() {
        return realmID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public TimeRule getTimeRule() {
        return timeRule;
    }

    @Override
    public Grid getGrid() {
        return grid;
    }
}
