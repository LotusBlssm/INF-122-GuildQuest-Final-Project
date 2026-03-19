package edu.uci.inf122.guildquest.content.items;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;
import edu.uci.inf122.guildquest.entities.interfaces.Absorbable;

import java.util.UUID;

public class Tool extends Item implements Absorbable {

    public Tool(UUID itemID, String name, int rarity, String description) {
        super(itemID, new Name(name), rarity, description);
    }

    public void interact() {
    }

    @Override
    public void act() {

    }

    @Override
    public boolean use(Entity user, Entity target) {
        return false;
    }
}
