package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class QuestEvent extends GameContent {
    // privates:
    private UUID questID;
    private String questTitle;
    private Realm realm;
    private List<GameCharacter> participants;

    // constructor:
    public QuestEvent(UUID id, String title, Realm realm) {
        questID = id;
        questTitle = title;
        this.realm = realm;
        participants = new ArrayList<>();
    }

    // check name
    public boolean checkTitle(String title) {
        return title == questTitle;
    }

    public void setName(String title) {
        questTitle = title;
    }

    public UUID getQuestID() {
        return questID;
    }

}
