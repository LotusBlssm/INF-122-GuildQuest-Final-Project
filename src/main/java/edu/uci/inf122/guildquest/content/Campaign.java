package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;

public class Campaign extends GameContent {
    private int campaignID;
    private String campaignName;
    private List<QuestEvent> events;

    public Campaign(int id, String name) {
        campaignID = id;
        campaignName = name;
        events = new ArrayList<>();

    }

    // check name
    public boolean checkName(String name) {
        return name == campaignName;
    }

    public void setName(String name) {
        campaignName = name;
    }
}
