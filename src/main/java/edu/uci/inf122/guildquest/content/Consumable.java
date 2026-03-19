package edu.uci.inf122.guildquest.content;

import edu.uci.inf122.guildquest.entities.domain_primitives.Name;

import java.util.UUID;

public class Consumable extends Item {
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
}
