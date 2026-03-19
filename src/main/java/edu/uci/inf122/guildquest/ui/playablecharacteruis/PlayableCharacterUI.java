package edu.uci.inf122.guildquest.ui.playablecharacteruis;

import edu.uci.inf122.guildquest.content.items.Item;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;
import edu.uci.inf122.guildquest.ui.InventoryUI;

public abstract class PlayableCharacterUI {
    private PlayableCharacter character;

    public PlayableCharacterUI(PlayableCharacter character) {
        this.character = character;
    }

    public abstract void display();

    public void displayInventory() {
        
        System.out.println(character.getName() + "'s Inventory:");
        if (character.getInventory().isEmpty()) {
            System.out.println("- Empty");
        } else {
            character.getInventory().forEach(item -> System.out.println("- " + item.getName()));
        }
        System.out.println();
    }

    public Item promptInventory() {
        if (character.getInventory().isEmpty()){
            return null;
        }
        return InventoryUI.queryInventory(character.getInventory());
    }
}