package edu.uci.inf122.guildquest.ui;

// import RealmAndTime.WorldTime;

public class WorldTimeUI {
    static private final Page page = Page.getPage();
    public static WorldTime createTime(){
        page.print("Setting Start Time:\n");
        int mins = page.acceptIntUntil("Please choose number of minutes:", 59);
        int hours = page.acceptIntUntil("Please choose number of hours:", 23);
        int days = page.acceptIntUntil("Please choose the day it happened on:", Integer.MAX_VALUE);
        page.print("created time\n");
        return new WorldTime(mins, hours, days);
    }
}
