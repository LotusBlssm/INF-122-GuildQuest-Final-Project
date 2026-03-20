package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;

public class KillTargetCondition extends WinCondition {
    private int targetsLeft;

    public KillTargetCondition(int initialNumberTargets){
        targetsLeft=initialNumberTargets;
    }
    @Override
    public boolean isWon() {
        return targetsLeft==0;
    }

    @Override
    public Text loseMessage() {
        return null;
    }

    @Override
    public Text winMessage() {
        return null;
    }

    public void updateCondition(int newNumTargets){
        targetsLeft=newNumTargets;
    }

    public int getTargetsLeft() {
        return targetsLeft;
    }
}
