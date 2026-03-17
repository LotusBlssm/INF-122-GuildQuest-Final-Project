package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.npcs.Hostile;
import edu.uci.inf122.guildquest.entities.npcs.NPC;

public class RaidClearCondition extends WinCondition {

    @Override
    public boolean isWon(MiniAdventure m) {
        for (Entity e : m.getEntities()) {
            if (e instanceof NPC npc && e instanceof Hostile) {
                if (!npc.isDead()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isLost(MiniAdventure m) {
        return false;
    }
}
