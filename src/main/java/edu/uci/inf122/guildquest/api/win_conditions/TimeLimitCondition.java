package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;

import java.sql.Time;

public class TimeLimitCondition extends WinCondition {
    private Time timeLeft;
    private Time timeSpent;
    public TimeLimitCondition(Time timeLeft){
        this.timeSpent=timeLeft;
        this.timeSpent=new Time(0);
    }
    @Override
    public boolean isWon(MiniAdventure m) {
        return false;
    }
    public void updateCondition(Time timeSpent){
        // support functionality for both adding time and decrementing time
        long timeDiff = timeLeft.getTime() - timeSpent.getTime();
        timeLeft = new Time(timeLeft.getTime() - timeDiff);
        this.timeSpent = new Time(timeSpent.getTime() + timeDiff);
    }

    public Time getTimeSpent() {
        return timeSpent;
    }
    public Time getTimeLeft() {
        return timeLeft;
    }
}
