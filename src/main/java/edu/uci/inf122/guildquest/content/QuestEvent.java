package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class QuestEvent extends GameContent {
    // privates:
    private UUID questID;
    private String questTitle;
    private Realm realm;
    private WorldTime startTime;
    private WorldTime endTime;
    private List<GameCharacter> participants;

    // constructor:
    public QuestEvent(UUID id, String title, Realm realm) {
        questID = id;
        questTitle = title;
        this.realm = realm;
        participants = new ArrayList<>();
    }

    public QuestEvent(String title, WorldTime startTime, Realm realm,
            List<GameCharacter> participants, List<?> rewards, List<?> punishments) {
        this.questID = UUID.randomUUID();
        this.questTitle = title;
        this.startTime = startTime;
        this.realm = realm;
        this.participants = participants != null ? participants : new ArrayList<>();
    }

    // check name
    public boolean checkTitle(String title) {
        return title.equals(questTitle);
    }

    public void setName(String title) {
        questTitle = title;
    }

    public UUID getQuestID() {
        return questID;
    }

    public UUID getEventId() {
        return questID;
    }

    public String getTitle() {
        return questTitle;
    }

    public void setTitle(String title) {
        questTitle = title;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public WorldTime getStartTime() {
        return startTime;
    }

    public void setStartTime(WorldTime startTime) {
        this.startTime = startTime;
    }

    public WorldTime getEndTime() {
        return endTime;
    }

    public void setEndTime(WorldTime endTime) {
        this.endTime = endTime;
    }

    public List<GameCharacter> getParticipants() {
        return participants;
    }

    public void addParticipant(GameCharacter participant) {
        participants.add(participant);
    }
}
