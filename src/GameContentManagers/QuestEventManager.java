package GameContentManagers;

import java.util.List;
import java.util.ArrayList;

import GameContents.GameCharacter;
import GameContents.QuestEvent;

public class QuestEventManager {
    private int campaignID;
    private List<QuestEvent> questEvents;

    // no singleton pattern
    public QuestEventManager(int id) {
        campaignID = id;
        questEvents = new ArrayList<>();
    }

    // add new user
    public boolean addQuest(QuestEvent newQuest) {
        if (questEvents.contains(newQuest)) {
            return false;
        }
        questEvents.add(newQuest);
        return true;
    }

    // remove user
    public boolean removeQuest(String name) {
        for (QuestEvent quest : questEvents) {
            if (quest.checkTitle(name)) {
                questEvents.remove(quest);
                return true;
            }
        }
        return false;
    }

    // update user name
    public boolean updateQuestName(String oldTitle, String newTitle) {
        for (QuestEvent quest : questEvents) {
            if (quest.checkTitle(oldTitle)) {
                quest.setName(newTitle);
                return true;
            }
        }
        return false;
    }

    // public QuestEvent getCurrentQuest(String title) {
    // for (QuestEvent quest : questEvents) {
    // if (quest.checkTitle(title)) {
    // return quest;
    // }
    // }
    // return null;
    // }

}
