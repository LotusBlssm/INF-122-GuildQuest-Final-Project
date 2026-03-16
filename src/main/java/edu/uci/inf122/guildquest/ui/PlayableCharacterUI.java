package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

public class PlayableCharacterUI {
    private PlayableCharacter character;

    public PlayableCharacterUI(PlayableCharacter character) {
        this.character = character;
    }

    public void display() {
        int maxLength = Math.max(
            "Name: ".length() + character.getName().length(),
            Math.max(
                "Health: ".length() + String.valueOf(character.getHealth()).length(),
                Math.max(
                    "Level: ".length() + String.valueOf(character.getLevel()).length(),
                    "Class: ".length() + character.getCharacterClass().length()
                )
            )
        );

        String border = "+" + "-".repeat(maxLength + 2) + "+";

        System.out.println(border);
        System.out.println("| " + String.format("%-" + (maxLength) + "s", "Name: " + character.getName()) + " |");
        System.out.println("| " + String.format("%-" + (maxLength) + "s", "Health: " + character.getHealth()) + " |");
        System.out.println("| " + String.format("%-" + (maxLength) + "s", "Level: " + character.getLevel()) + " |");
        System.out.println("| " + String.format("%-" + (maxLength) + "s", "Class: " + character.getCharacterClass()) + " |");
        System.out.println(border);
    }
}