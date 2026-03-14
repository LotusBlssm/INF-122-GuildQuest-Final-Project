package edu.uci.inf122.guildquest.ui;

public class AdventureUI {
    private MiniAdventure adventure;

    public AdventureUI(MiniAdventure adventure) {
        this.adventure = adventure;
    }

    public void start() {
        adventure.play();
    }

    public static void displayOptions(User user){
        String prompt = """
                1 --- Play Adventure 1 (Placeholder)
                2 --- Play Adventure 2 (Placeholder)
                
                0 --- exit
                """;

        page.acceptIntUntil(prompt, 1);

        int i = -1;
        while (i != 0){
            i = page.acceptIntUntil(prompt, 2);
            switch (i){
                case 0 -> System.out.print("");
                case 1 -> {
                    // Needs Implementation
                    System.out.println("Playing adventure 1 (Placeholder)");
                }
                case 2 -> {
                    // Needs Implementation
                    System.out.println("Playing adventure 2 (Placeholder)");
                }
            }
        }   
    }
}