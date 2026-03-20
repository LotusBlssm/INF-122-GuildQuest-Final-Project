package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;
import edu.uci.inf122.guildquest.entities.interfaces.HasHealth;

import java.util.List;

public class StaysAliveCondition extends WinCondition{
    HasHealth entity;

    public StaysAliveCondition(HasHealth entity){
        if (entity==null) throw new IllegalArgumentException("no null arguments");
        this.entity=entity;
    }

    @Override
    public boolean isWon() {
        return false;
    }

    @Override
    public boolean isLost() {
        return entity.getHealth().isDead();
    }
    @Override
    public boolean isIncomplete() {
        return !isLost();
    }

    @Override
    public Text loseMessage() {
        return new Text(entity.getName() + " died");
    }

    @Override
    public Text winMessage() {
        return new Text(entity.getName() + " Stayed alive!");
    }
}
