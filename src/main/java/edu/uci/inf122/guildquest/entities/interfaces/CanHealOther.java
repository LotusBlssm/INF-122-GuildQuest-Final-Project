package edu.uci.inf122.guildquest.entities.interfaces;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Amount;
import edu.uci.inf122.guildquest.entities.domain_primitives.DecimalAmount;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

public interface CanHealOther {
    public void heal(Amount amount, PlayableCharacter other);
    public Amount getHealAmount();
}
