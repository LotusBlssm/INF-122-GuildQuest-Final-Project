package edu.uci.inf122.guildquest.content.items;

import edu.uci.inf122.guildquest.content.QuestEvent;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

import java.util.UUID;

public abstract class Consumable extends Item{
    private QuestEvent questAssociated;

    public Consumable(UUID itemID, String name, int rarity, String description, QuestEvent questAssociated) {
        super(itemID, new Name(name), rarity, description);
        this.questAssociated = questAssociated;
    }

    public QuestEvent getQuestAssociated() {
        return questAssociated;
    }

    @Override
    public void act() {

    }

    public abstract boolean use(Entity user, Entity target);
}
