package edu.uci.inf122.guildquest.content;

public class WorldTime implements Comparable<WorldTime> {
    private int minutes;
    private int hours;
    private int days;

    public WorldTime(int minutes, int hours, int days) {
        this.minutes = minutes;
        this.hours = hours;
        this.days = days;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getDays() {
        return days;
    }

    public int toTotalMinutes() {
        return days * 24 * 60 + hours * 60 + minutes;
    }

    @Override
    public int compareTo(WorldTime other) {
        return Integer.compare(this.toTotalMinutes(), other.toTotalMinutes());
    }

    @Override
    public String toString() {
        return String.format("Day %d, %02d:%02d", days, hours, minutes);
    }
}
