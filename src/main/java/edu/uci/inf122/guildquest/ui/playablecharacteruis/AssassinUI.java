package edu.uci.inf122.guildquest.ui.playablecharacteruis;

import java.util.ArrayList;
import java.util.List;

import edu.uci.inf122.guildquest.entities.playablecharacters.Assassin;

public class AssassinUI extends PlayableCharacterUI {
    private static AssassinUI singleton;
    private final Assassin character;

    private AssassinUI(Assassin character) {
        super(character);
        this.character = character;
    }

    public static AssassinUI getAssassinUI(Assassin character){
        if (singleton==null) return new AssassinUI(character);
        else return singleton;

    }

    @Override
    public void display() {
        List<String> lines = new ArrayList<>();
        lines.add("Name: " + character.getName());
        lines.add("Health: " + character.getHealth());
        lines.add("Attack Power: " + character.getAttackPower().getCount());
        lines.add("Critical Hit Chance: " + character.getCriticalHitChanceDisplay().getCount() + "%");

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