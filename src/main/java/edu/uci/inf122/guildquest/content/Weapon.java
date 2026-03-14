package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public class Weapon extends Item {
    private int damage;

    public Weapon(UUID itemID, String name, int rarity, String description, int damage) {
        super(itemID, name, rarity, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
