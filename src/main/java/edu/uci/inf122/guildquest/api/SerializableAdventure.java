package edu.uci.inf122.guildquest.api;

public interface SerializableAdventure {
    AdventureSnapshot save();
    void load(AdventureSnapshot snapshot);
}