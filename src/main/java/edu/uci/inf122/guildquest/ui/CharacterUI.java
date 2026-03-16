package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.content.Campaign;
import edu.uci.inf122.guildquest.content.GameCharacter;
import edu.uci.inf122.guildquest.content.User;

import java.util.List;

public class CharacterUI {
    private static final Page page = Page.getPage();

    public static void displayOptions(User user) {
        String prompt = """
                What would you like to do to a character?
                1 --- Create
                2 --- Edit
                3 --- Remove

                0 --- exit
                """;
        int choice = -1;
        while (choice != 0) {
            choice = page.acceptIntUntil(prompt, 3);
            page.nextScreen();
            switch (choice) {
                case 0 -> page.print("leaving\n");
                case 1 -> createCharacter(user);
                case 2 -> editCharacter(user);
                case 3 -> removeCharacter(user);
            }
        }
    }

    public static GameCharacter createCharacter(User user) {
        String name = page.acceptStrWithValidation("Please name your character: ");
        page.nextScreen();
        String charClass = page.acceptStrWithValidation("Please write your class: ");
        page.nextScreen();
        GameCharacter character = new GameCharacter(name, charClass);
        user.addCharacter(character);
        page.print("Created character " + character.getName() + "!\n");
        return character;
    }

    private static void editCharacter(User user) {
        GameCharacter character = chooseCharacter(user.getCharacters());
        if (character == null) {
            return;
        }
        String prompt = """
                Options for %s:
                1 --- Rename
                2 --- Manage Inventory (unimplementd)
                0 --- exit
                """;
        int choice = page.acceptIntUntil(Page.dynamicPrompt(prompt, character.getName()), 2);
        switch (choice) {
            case 0 -> page.print("leaving\n");
            case 1 -> renameCharacter(character);
            case 2 -> InventoryUI.manageInventory(character); // unimplemented

        }
    }

    private static void renameCharacter(GameCharacter ch) {
        String newName = page.acceptStrWithValidation("Please enter a name:\n");
        ch.setName(newName);
        page.print("renamed\n");
    }

    public static void removeCharacter(User user) {
        GameCharacter character = chooseCharacter(user.getCharacters());
        if (character == null)
            return;
        user.getCharacters().remove(character);
        for (Campaign c : user.getCampaigns()) {
            c.getQuestEvents().forEach(qe -> qe.getParticipants().remove(character));
        }
        page.print("removed character\n");
    }

    public static GameCharacter chooseCharacter(List<GameCharacter> characters) {
        StringBuilder prompt = new StringBuilder("Please choose a Character\n");
        int i = 1;
        if (characters.isEmpty()) {
            page.print("Sorry, there are no Characters, please choose something else\n");
        }
        for (GameCharacter ch : characters) {
            prompt.append(i).append(" --- ").append(ch.getName()).append('\n');
            ++i;
        }
        prompt.append("0 -- exit\n");
        int input = page.acceptIntUntil(prompt.toString(), i - 1);

        page.nextScreen();
        if (input == 0)
            return null;
        else
            return characters.get(input - 1);
    }
}
