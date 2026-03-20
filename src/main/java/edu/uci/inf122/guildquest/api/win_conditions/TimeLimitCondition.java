package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;

public class TimeLimitCondition extends WinCondition {
    private int timeLimitMinutes;
    private int timeElapsedMinutes;

    public TimeLimitCondition(int timeLimitMinutes) {
        if (timeLimitMinutes <= 0) {
            throw new IllegalArgumentException("Time limit must be positive");
        }
        this.timeLimitMinutes = timeLimitMinutes;
        this.timeElapsedMinutes = 0;
    }

    @Override
    public boolean isWon() {
        return false;
    }
    @Override
    public boolean isLost() {
        return timeElapsedMinutes >= timeLimitMinutes;
    }

    @Override
    public Text loseMessage() {
        return new Text("You did not finish within the time");
    }

    @Override
    public Text winMessage() {
        return new Text("You finished within the time!");
    }

    public void updateCondition(int minutesToAdd) {
        timeElapsedMinutes += minutesToAdd;
    }

    public int getTimeLeftMinutes() {
        return timeLimitMinutes - timeElapsedMinutes;
    }

    public int getTimeElapsedMinutes() {
        return timeElapsedMinutes;
    }
}
