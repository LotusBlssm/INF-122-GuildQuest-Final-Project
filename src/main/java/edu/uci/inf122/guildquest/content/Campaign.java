package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Campaign extends GameContent {
    private UUID campaignID;
    private String campaignName;
    private List<QuestEvent> events;
    private User owner;
    private boolean archived;
    private VisibilityType visibility;

    public Campaign(UUID id, String name) {
        campaignID = id;
        campaignName = name;
        events = new ArrayList<>();
        archived = false;
        visibility = VisibilityType.PRIVATE;
    }

    public Campaign(String name) {
        this(UUID.randomUUID(), name);
    }

    // check name
    public boolean checkName(String name) {
        return name.equals(campaignName);
    }

    public String getName() {
        return campaignName;
    }

    public void setName(String name) {
        campaignName = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public VisibilityType getVisibility() {
        return visibility;
    }

    public void setVisibility(VisibilityType visibility) {
        this.visibility = visibility;
    }

    public List<QuestEvent> getQuestEvents() {
        return events;
    }

    public void addEvent(QuestEvent event) {
        events.add(event);
    }

    public void removeEvent(UUID eventId) {
        events.removeIf(e -> e.getQuestID().equals(eventId));
    }
}
