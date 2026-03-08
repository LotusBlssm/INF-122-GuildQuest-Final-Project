package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;

public class QuestEvent extends GameContent {
    // privates:
    private int questID;
    private String questTitle;
    private Realm realm;
    private List<GameCharacter> participants;

    // constructor:
    public QuestEvent(int id, String title, Realm realm) {
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

    public int getQuestID() {
        return questID;
    }

}
