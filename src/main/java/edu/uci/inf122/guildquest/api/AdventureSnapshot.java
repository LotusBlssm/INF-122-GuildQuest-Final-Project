package edu.uci.inf122.guildquest.api;

import java.util.List;

public class AdventureSnapshot {
    private String adventureId;
    private int turnNumber;
    private int realmId;
    private int timeMinutes;
    private List<EntitySnapshot> entities;

    public AdventureSnapshot(String adventureId, int turnNumber, int realmId, int timeMinutes, List<EntitySnapshot> entities) {
        this.adventureId = adventureId;
        this.turnNumber = turnNumber;
        this.realmId = realmId;
        this.timeMinutes = timeMinutes;
        this.entities = entities;
    }

    public String getAdventureId() {
        return adventureId;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public int getRealmId() {
        return realmId;
    }

    public int getTimeMinutes() {
        return timeMinutes;
    }

    public List<EntitySnapshot> getEntities() {
        return entities;
    }
}