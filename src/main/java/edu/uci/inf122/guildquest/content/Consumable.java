package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public class Consumable extends Item {
    private QuestEvent questAssociated;

    public Consumable(UUID itemID, String name, int rarity, String description, QuestEvent questAssociated) {
        super(itemID, name, rarity, description);
        this.questAssociated = questAssociated;
    }

    public QuestEvent getQuestAssociated() {
        return questAssociated;
    }

    @Override
    public boolean isUsable() {
        return true;
    }

    @Override
    public String getItemType() {
        return "Consumable";
    }
}