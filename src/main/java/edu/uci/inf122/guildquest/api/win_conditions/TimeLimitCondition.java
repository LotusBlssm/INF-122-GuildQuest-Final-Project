package edu.uci.inf122.guildquest.api.win_conditions;

import edu.uci.inf122.guildquest.engine.MiniAdventure;

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
    public boolean isWon(MiniAdventure m) {
        return false; // time expiring is a lose condition, not a win
    }

    @Override
    public boolean isLost(MiniAdventure m) {
        return timeElapsedMinutes >= timeLimitMinutes;
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
