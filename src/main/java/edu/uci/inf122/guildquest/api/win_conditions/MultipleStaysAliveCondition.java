package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;
import edu.uci.inf122.guildquest.entities.interfaces.HasHealth;

import java.util.ArrayList;
import java.util.List;

public class MultipleStaysAliveCondition extends WinCondition{
    List<StaysAliveCondition> conditions;

    private MultipleStaysAliveCondition(List<StaysAliveCondition> conditions){
        this.conditions=conditions;
    }
    public static MultipleStaysAliveCondition getFromEntities(List<HasHealth> entities){
        if (entities.isEmpty()) throw new IllegalArgumentException("Condition invalid with no entities");
        List<StaysAliveCondition> conditions = new ArrayList<>();
        for (HasHealth entity : entities ){
            conditions.add(new StaysAliveCondition(entity));
        }
        return new MultipleStaysAliveCondition(conditions);
    }
    public static MultipleStaysAliveCondition getFromConditions(List<StaysAliveCondition> conditions){
        return new MultipleStaysAliveCondition(conditions);
    }

    @Override
    public boolean isWon() {
        return false;
    }

    @Override
    public boolean isLost() {
        for (StaysAliveCondition e : conditions){
            if (e.isLost()){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isIncomplete() {
        return !isLost();
    }

    @Override
    public Text loseMessage() {
        Text ret = new Text("");
        for (StaysAliveCondition e : conditions){
            if (e.isLost()){
                ret = ret.addWithSpace(e.loseMessage());
            }
        }
        return ret;
    }

    @Override
    public Text winMessage() {
        return null;
    }
}
