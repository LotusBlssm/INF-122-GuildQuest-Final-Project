package GameContents;

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

}
