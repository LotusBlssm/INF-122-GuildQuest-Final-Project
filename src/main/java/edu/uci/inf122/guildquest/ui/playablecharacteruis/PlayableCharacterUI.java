package edu.uci.inf122.guildquest.ui.playablecharacteruis;

import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

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
}