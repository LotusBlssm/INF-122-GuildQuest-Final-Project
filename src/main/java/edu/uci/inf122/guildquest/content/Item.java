package edu.uci.inf122.guildquest.content;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

import java.util.UUID;

public abstract class Item extends Entity {
    private UUID itemID;
    private int rarity;
    private String description;

    public Item(UUID itemID, Name name, int rarity, String description) {
        this.itemID = itemID;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
    }

    public UUID getItemID() {
        return itemID;
    }

    public int getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }
}
