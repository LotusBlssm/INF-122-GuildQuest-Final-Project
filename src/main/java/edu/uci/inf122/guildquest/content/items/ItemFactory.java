package edu.uci.inf122.guildquest.content.items;

import edu.uci.inf122.guildquest.content.QuestEvent;

import java.util.UUID;

public class ItemFactory {

    public static Weapon createWeapon(String name, int rarity, String description, int damage) {
        return new Weapon(UUID.randomUUID(), name, rarity, description, damage);
    }

    public static Tool createTool(String name, int rarity, String description) {
        return new Tool(UUID.randomUUID(), name, rarity, description);
    }

    public static QuestItem createQuestItem(String name, int rarity, String description, QuestEvent questAssociated) {
        return new QuestItem(UUID.randomUUID(), name, rarity, description, questAssociated);
    }

    public static HealingPotion createHealingPotion(String name, int rarity, String description, QuestEvent questAssociated, int strength) {
        return new HealingPotion(UUID.randomUUID(), name, rarity, description, questAssociated, strength);
    }
}
