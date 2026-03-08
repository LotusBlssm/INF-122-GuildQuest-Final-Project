package GameContents;

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
}
