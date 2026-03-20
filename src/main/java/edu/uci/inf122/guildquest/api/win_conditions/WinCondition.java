package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;

public abstract class WinCondition {
    boolean isLost;
    boolean inProgress;
    public abstract boolean isWon();
    public boolean isLost(){ return isLost; }
    public boolean isComplete() {return !inProgress; }
    public boolean isIncomplete() {return inProgress; }

    public abstract Text loseMessage();
    public abstract Text winMessage();
    // public abstract void updateCondition(GameContext context)
    // // potentially use State class to do this or some other
    // // encapsulating object to hold any potential params we may want to pass.
}
