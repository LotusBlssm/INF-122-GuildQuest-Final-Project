package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;
import edu.uci.inf122.guildquest.entities.npcs.Hostile;
import edu.uci.inf122.guildquest.entities.npcs.NPC;

import java.util.List;

public class RaidClearCondition extends WinCondition {
    private final List<Entity> entities;

    public RaidClearCondition(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public boolean isWon() {
        for (Entity e : entities) {
            if (e instanceof NPC npc && e instanceof Hostile) {
                if (!npc.isDead()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isLost() {
        return false;
    }

    @Override
    public Text loseMessage() {
        return new Text("failed raid clear condition");
    }

    @Override
    public Text winMessage() {
        return new Text("succeeded raid clear condition");
    }
}
