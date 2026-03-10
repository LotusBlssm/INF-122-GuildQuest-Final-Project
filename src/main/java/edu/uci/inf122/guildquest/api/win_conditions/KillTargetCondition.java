package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;

public class KillTargetCondition extends WinCondition {
    private int targetsLeft;

    public KillTargetCondition(int initialNumberTargets){
        targetsLeft=initialNumberTargets;
    }
    @Override
    public boolean isWon(MiniAdventure m) {
        return targetsLeft==0;
    }

    public void updateCondition(int newNumTargets){
        targetsLeft=newNumTargets;
    }

    public int getTargetsLeft() {
        return targetsLeft;
    }
}
