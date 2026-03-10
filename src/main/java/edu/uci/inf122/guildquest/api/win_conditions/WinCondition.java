package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;

public abstract class WinCondition {
    boolean isLost;
    boolean inProgress;
    public abstract boolean isWon(MiniAdventure m);
    public boolean isLost(MiniAdventure m){ return isLost; }
    public boolean isComplete(MiniAdventure m) {return !inProgress; }
    public boolean isIncomplete(MiniAdventure m) {return inProgress; }
    // public abstract void updateCondition(GameContext context)
    // // potentially use State class to do this or some other
    // // encapsulating object to hold any potential params we may want to pass.
}
