package UI;

import Core.Campaign;
import Core.QuestEvent;
import InventoryCommands.AddItemsCommand;
import InventoryCommands.RemoveItemCommand;
import RPGDomain.Character;
import RealmAndTime.Realm;
import RealmAndTime.WorldTime;
import ViewsAndTimelines.Page;

import java.util.ArrayList;
import java.util.Comparator;

public class QuestEventUI {
    private static final Page page = Page.getPage();
    public static QuestEvent chooseQuestEvent(Campaign campaign){
        // assume we include archived values
        return chooseQuestEvent(campaign, true);
    }

    public static QuestEvent chooseQuestEvent(Campaign campaign, boolean includeArchived){
        StringBuilder prompt = new StringBuilder("Please choose a QuestEvent\n");
        Campaign choice = null;
        int i = 1;
        if (campaign.getQuestEvents().isEmpty()){
            page.print("Sorry, there are no QuestEvents, please choose something else\n");
        }
        for (QuestEvent qe : campaign.getQuestEvents()){
            prompt.append(i).append(" --- ").append(qe.getTitle()).append('\n');
            ++i;
        }
        prompt.append("0 -- exit\n");
        int input = -1;
        input = page.acceptIntUntil(prompt.toString(), i-1);

        page.nextScreen();
        if (input == 0) return null;
        else return campaign.getQuestEvents().get(input-1);
    }
    public static void updateQuestEvent(Campaign c){
        QuestEvent qe = chooseQuestEvent(c);
        if (qe==null) return;
        String prompt = """
                Please edit this Quest Event: %s
                1 --- Update Title
                2 --- Update Times
                
                3 --- Add Character
                4 --- Remove Character
                
                5 --- Reward Character
                6 --- Punish Character (remove item)
                7 --- Update Realm
                
                0 --- exit
                """;
        int choice=1;
        while (choice != 0){
            choice = page.acceptIntUntil(Page.dynamicPrompt(prompt, qe.getTitle()), 7);
            page.nextScreen();
            switch (choice) {
                case 0 -> page.print("leaving\n");
                case 1-> updateTitle(qe); // done
                case 2-> updateTimesUI(qe); // not done
                case 3-> addCharacter(qe, c); // not done
                case 4-> removeCharacters(qe, c); // not done
                case 5-> rewardCharacter(qe, c);
                case 6-> punishCharacter(qe);
                case 7-> updateRealm(qe); // not done
            }
        }

    }

    public static QuestEvent createQuestEvent(Campaign c){
        String title = page.acceptStrWithValidation(
                "Please enter a Title for your QuestEvent:\n");
        WorldTime startTime = WorldTimeUI.createTime();
        Realm newRealm = RealmUI.createRealm();
        return new QuestEvent(title, startTime, newRealm,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    }
    public static void removeQuestEvent(Campaign c){
        QuestEvent qe = chooseQuestEvent(c);
        if (qe != null) c.removeEvent(qe.getEventId());

    }

    private static void updateTitle(QuestEvent qe){
        String newName = page.acceptStrWithValidation("Please choose a new name: ");
        if (newName != null)  qe.setTitle(newName);
        page.print("new name set\n");
    }
    private static void updateTimesUI(QuestEvent qe){
        String prompt = """
                1 --- set start time
                2 --- set end time
                
                0 --- go back
                """;
        int choice = page.acceptIntUntil(prompt, 2);
        switch (choice){
            case 0-> page.print("going back");
            case 1-> setStartTime(qe);
            case 2-> setEndTime(qe);
        }
    }
    private static void setStartTime(QuestEvent qe) {
        WorldTime start = WorldTimeUI.createTime();
        if ((qe.getEndTime() != null) && start.compareTo(qe.getEndTime()) > 0) {
            page.print("Start is after the end, not setting\n");
            return;
        }
        qe.setStartTime(start);
    }
    private static void setEndTime(QuestEvent qe) {
        WorldTime end = WorldTimeUI.createTime();
        if (end.compareTo(qe.getStartTime()) < 0) {
            page.print("end is before the start, not setting");
            return;
        }
        qe.setEndTime(end);
    }
    private static void updateRealm(QuestEvent qe){
        Realm r = RealmUI.createRealm();
        if (r != null) qe.setRealm(r);
    }
    private static void addCharacter(QuestEvent qe, Campaign c){
        // Query get existing characters or don't
        int choice = page.acceptIntUntil("1 --- Add existing character\n2 --- add new character\n0 --- go back\n", 2);
        if (choice ==0) return;
        Character character = null;
        if (choice == 1) {
            character = CharacterUI.chooseCharacter(c.getOwner().getCharacters());
        } else if (choice == 2){
            character = CharacterUI.createCharacter(c.getOwner());
        }
        qe.addParticipant(character);
        page.print("added " + character.getName() + "!\n");
    }
    private static void removeCharacters(QuestEvent qe, Campaign c){
        qe.getParticipants().remove(CharacterUI.chooseCharacter(c.getOwner().getCharacters()));
    }
    private static void rewardCharacter(QuestEvent qe, Campaign c){
        if (qe.getParticipants().isEmpty()){
            page.print("No characters to reward.\n");
            return;
        }
        Character character = CharacterUI.chooseCharacter(qe.getParticipants()); // only questEvent characters
        if (character == null) return;
        InventoryUI.addItem(character);
    }
    private static void punishCharacter(QuestEvent qe){
        if (qe.getParticipants().isEmpty()){
            page.print("No characters to punish.\n");
            return;
        }
        Character character = CharacterUI.chooseCharacter(qe.getParticipants()); // only questEvent characters
        if (character == null) return;
        removeItemCommand.execute(character);
    }
    public static String timelineString(Campaign c){
        c.getQuestEvents().sort(Comparator.comparing(QuestEvent::getStartTime));
        StringBuilder timeLine = new StringBuilder();
        String time;
        for (QuestEvent qe : c.getQuestEvents()){
            time = qe.getStartTime().toString() +
                    ((qe.getEndTime() == null) ? "" : " to " + qe.getEndTime().toString() + "\n");
            timeLine.append("    ").append(qe.getTitle()).append("; ").append(time).append("; Realm: ").append(qe.getRealm().toString());
        }
        return "    " + timeLine.toString().trim();
    }
}
