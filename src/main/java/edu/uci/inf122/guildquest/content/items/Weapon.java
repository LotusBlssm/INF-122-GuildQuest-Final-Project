package edu.uci.inf122.guildquest.content.items;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;
import edu.uci.inf122.guildquest.entities.interfaces.Absorbable;

import java.util.UUID;

public class Weapon extends Item implements Absorbable {
    private int damage;

    public Weapon(UUID itemID, String name, int rarity, String description, int damage) {
        super(itemID, new Name(name), rarity, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void act() {

    }

    @Override
    public boolean use(Entity user, Entity target) {
        return false;
    }
}
