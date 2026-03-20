package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.content.User;

import java.sql.Time;
import java.util.List;

import edu.uci.inf122.guildquest.adventures.EscortAdventure;
import edu.uci.inf122.guildquest.api.win_conditions.TimeLimitCondition;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
import edu.uci.inf122.guildquest.engine.MiniAdventure;

import edu.uci.inf122.guildquest.ui.Page;

public class AdventureUI {
    private MiniAdventure adventure;

    public AdventureUI(MiniAdventure adventure) {
        this.adventure = adventure;
    }

    public void start() {
        adventure.play();
    }

    public static void displayOptions(User user){
        Page page = Page.getPage();
        page.nextScreen();
        String prompt = """
                What adventure would you like to play?

                1 --- Play Escort Adventure
                2 --- Play Timed Raid Adventure
                
                0 --- exit
                """;

        int i = -1;
        while (i != 0){
            i = page.acceptIntUntil(prompt, 2);
            switch (i){
                case 0 -> System.out.print("");
                case 1 -> {
                    // Needs Implementation
                    System.out.println("Playing Escort Adventure");

                    List<WinCondition> winConditions = List.of(new TimeLimitCondition(new Time(2)));

                    MiniAdventure adventure = new EscortAdventure(
                        null,
                        null,
                        winConditions,
                        null);
                    AdventureUI ui = new AdventureUI(adventure);
                    
                    page.nextScreen();
                    ui.start();
                }
                case 2 -> {
                    // Needs Implementation
                    System.out.println("Playing adventure 2 (Placeholder)");
                }
            }
        }   
    }
}