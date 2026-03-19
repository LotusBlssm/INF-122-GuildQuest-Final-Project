package edu.uci.inf122.guildquest.content.items;

import edu.uci.inf122.guildquest.content.QuestEvent;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Amount;
import edu.uci.inf122.guildquest.entities.interfaces.Absorbable;
import edu.uci.inf122.guildquest.entities.interfaces.HasHealth;

import java.util.UUID;

public class HealingPotion extends Consumable implements SelfApplicable, Absorbable {
    private final Amount strength;
    public HealingPotion(UUID itemID, String name, int rarity, String description, QuestEvent questAssociated, int strength) {
        super(itemID, name, rarity, description, questAssociated);
        this.strength=new Amount(strength);
    }

    @Override
    public boolean use(Entity self) {
        if (self instanceof HasHealth e){
            e.getHealth().increaseBy(strength);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean use(Entity self, Entity other){
        return use(self);
    }
}
