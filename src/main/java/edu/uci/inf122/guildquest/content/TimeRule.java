package edu.uci.inf122.guildquest.content;

public class TimeRule {
    private int offsetMinutes;
    private double multiplier;

    public TimeRule(int offsetMinutes, double multiplier) {
        if (multiplier == 0.0) {
            throw new IllegalArgumentException("multiplier must not be 0.0");
        }
        this.offsetMinutes = offsetMinutes;
        this.multiplier = multiplier;
    }

    public int getOffsetMinutes() {
        return offsetMinutes;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public int toLocalTime(int worldMinutes) {
        return (int) (worldMinutes * multiplier) + offsetMinutes;
    }

    public int toWorldTime(int localMinutes) {
        return (int) ((localMinutes - offsetMinutes) / multiplier);
    }
}
