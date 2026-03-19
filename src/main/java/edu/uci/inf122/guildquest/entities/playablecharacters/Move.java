package edu.uci.inf122.guildquest.entities.playablecharacters;

import edu.uci.inf122.guildquest.ui.TerminalGrid;

public interface Move {
    enum ValidMoves{
        TRAVEL,
        ATTACK,
        HEAL_OTHER,
        HEAL_SELF,
        TAKE_ITEM,
        USE_ITEM,
        REQUEST_HINT
    }
    void execute(TerminalGrid state, ValidMoves move);
}
