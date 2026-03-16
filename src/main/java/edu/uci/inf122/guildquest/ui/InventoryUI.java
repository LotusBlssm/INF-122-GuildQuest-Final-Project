package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.content.GameCharacter;
import edu.uci.inf122.guildquest.content.Inventory;
import edu.uci.inf122.guildquest.content.Item;
import edu.uci.inf122.guildquest.content.ItemFactory;

public class InventoryUI {
    private static final Page page = Page.getPage();

    public static void manageInventory(GameCharacter character) {
        String prompt = """
                1 --- See Inventory
                2 --- Throw Away Item(s)
                3 --- Add Item(s)
                0 --- exit
                """;
        int choice = -1;
        while (choice != 0) {
            choice = page.acceptIntUntil(prompt, 3);
            page.nextScreen();
            switch (choice) {
                case 0 -> page.print("leaving inventory\n");
                case 1 -> selectInventory(character);
                case 2 -> removeItem(character);
                case 3 -> addItems(character);
            }
        }
    }

    private static int printAndQueryInventory(GameCharacter character) {
        return printAndQueryInventory(character, "");
    }

    private static int printAndQueryInventory(GameCharacter character, String extraPrompt) {
        Inventory inv = character.getInventory();
        if (inv.getItems().isEmpty()) {
            page.print("Sorry, there are no items. Please exit.\n");
            return -1;
        }
        int i = 1;
        StringBuilder prompt = new StringBuilder("Choose an item " + extraPrompt + '\n');
        prompt.append("0 --- go back\n");
        for (Item item : inv.getItems()) {
            prompt.append(i).append(" --- ").append(item.getName()).append('\n');
            ++i;
        }
        int input = page.acceptIntUntil(prompt.toString(), i - 1);
        page.nextScreen();
        return input - 1;
    }

    private static void selectInventory(GameCharacter character) {
        int choice = printAndQueryInventory(character, "to see more options");
        if (choice == -1)
            return;
        else
            displayItem(character.getInventory().getItems().get(choice));
    }

    private static void displayItem(Item item) {
        page.print(item.getName() + " --- " + item.getDescription() + " " + item.getRarity() + '\n');
    }

    public static void removeItem(GameCharacter character) {
        int valid = page.acceptIntUntil("Are you sure you want to remove an item?\n1 --- yes\n0 --- no\n", 1);
        if (valid == 0)
            return;
        int choice = printAndQueryInventory(character, "to throw away");
        if (choice == -1)
            return;
        boolean res = character.getInventory().getItems().remove(
                character.getInventory().getItems().get(choice));
        if (res == false)
            page.print("ERROR: ITEM WAS NOT REMOVED");
    }

    private static void addItems(GameCharacter character) {
        while (true) {
            if (addItem(character) == null)
                break;
        }
    }

    public static Item addItem(GameCharacter character) {
        int valid = page.acceptIntUntil("Are you sure you want to add an item?\n1 --- yes\n0 --- no\n", 1);
        if (valid == 0)
            return null;
        String name = page.acceptStrWithValidation("Enter a name for this item:\n");
        String desc = page.acceptStrWithValidation("Enter a description: \n");
        String rarity = page.acceptStrWithValidation("Enter a rarity: \n");
        int rarityInt;
        try {
            rarityInt = Integer.parseInt(rarity);
        } catch (NumberFormatException e) {
            rarityInt = 1;
        }
        Item newItem = ItemFactory.createTool(name, rarityInt, desc);
        character.getInventory().addItem(newItem);
        page.nextScreen();
        return newItem;
    }
}
