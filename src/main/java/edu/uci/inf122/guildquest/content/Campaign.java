package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Campaign extends GameContent {
    private UUID campaignID;
    private String campaignName;
    private List<QuestEvent> events;

    public Campaign(UUID id, String name) {
        campaignID = id;
        campaignName = name;
        events = new ArrayList<>();

    }

    // check name
    public boolean checkName(String name) {
        return name.equals(campaignName);
    }

    public void setName(String name) {
        campaignName = name;
    }
}
