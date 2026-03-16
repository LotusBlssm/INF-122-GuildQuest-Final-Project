package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;

import java.util.UUID;

public class Nonliving extends Entity {
    private final Text description;
    private final UUID id;

    public Nonliving(Name name, Text description) {
        super(name);
        id = UUID.randomUUID();
        this.description = description;
    }

    public Text getDescription() {
        return description;
    }

    @Override
    public void act() {
        // Default no-op for static world objects.
    }

}
