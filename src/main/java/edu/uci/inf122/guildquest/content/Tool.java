package edu.uci.inf122.guildquest.content;

import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

import java.util.UUID;

public class Tool extends Item {

    public Tool(UUID itemID, String name, int rarity, String description) {
        super(itemID, new Name(name), rarity, description);
    }

    public void interact() {
    }

    @Override
    public void act() {

    }
}
