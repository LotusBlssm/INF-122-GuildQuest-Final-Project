package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.entities.domain_primitives.Text;

public class GetToTargetXYCondition extends WinCondition {
    protected int x;
    protected int y;
    protected boolean isThere;

    public GetToTargetXYCondition(int x, int y){
        this.x=x;
        this.y=y;
        isThere=false;
    }

    public void updateCondition(int curX, int curY){
        isThere = curX==x && curY==y;
    }

    @Override
    public boolean isWon() {
        return isThere;
    }
    @Override
    public boolean isLost() {
        return isComplete() && !isWon();
    }
    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public Text loseMessage() {
        return new Text("Did not arrive to target position.");
    }

    @Override
    public Text winMessage() {
        return new Text("Got to target position!");
    }
}
