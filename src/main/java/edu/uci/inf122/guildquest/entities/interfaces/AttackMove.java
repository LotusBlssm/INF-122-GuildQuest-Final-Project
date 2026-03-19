package edu.uci.inf122.guildquest.entities.interfaces;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.playablecharacters.Move;

public interface AttackMove extends Move {
    void attack(Entity target);
}