package edu.uci.inf122.guildquest.ui.playablecharacteruis;

import java.util.ArrayList;
import java.util.List;

import edu.uci.inf122.guildquest.entities.playablecharacters.Cleric;

public class ClericUI extends PlayableCharacterUI {
    private final Cleric character;

    public ClericUI(Cleric character) {
        super(character);
        this.character = character;
    }

    @Override
    public void display() {
        List<String> lines = new ArrayList<>();
        lines.add("Name: " + character.getName());
        lines.add("Health: " + character.getHealth());
        lines.add("Healing Power: " + character.getHealingPower().getCount());

        if (character.getWithPrincess().isWithPrincess()) {
            lines.add("Escorting Princess: true");
        }

        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }

        String border = "+" + "-".repeat(maxLength + 2) + "+";

        System.out.println(border);
        for (String line : lines) {
            System.out.println("| " + String.format("%-" + maxLength + "s", line) + " |");
        }
        System.out.println(border);
    }
}