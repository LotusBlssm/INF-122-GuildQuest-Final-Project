package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public abstract class Item {
    private UUID itemID;
    private String name;
    private int rarity;
    private String description;

    public Item(UUID itemID, String name, int rarity, String description) {
        this.itemID = itemID;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
    }

    public UUID getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUsable() {
        return false;
    }

    public String getItemType() {
        return "Item";
    }
}