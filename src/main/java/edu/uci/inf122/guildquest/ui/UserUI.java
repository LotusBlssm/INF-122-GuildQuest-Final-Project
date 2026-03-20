package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.content.Campaign;
import edu.uci.inf122.guildquest.content.User;

import java.util.*;
import java.util.stream.IntStream;

/**
 * The type User ui.
 */
public class UserUI {
    private enum OptionEnum{
        Exit(0), AdventureMan(1), CampaignMan(2), CharMan(3); // Add save data to a json file
        OptionEnum(int i) {
        }
        static OptionEnum get(int i) {
            return OptionEnum.values()[i];
        }
    }
    /**
     * The constant page.
     */
    public static Page page = Page.getPage();

    private final static Map<String, User> allUsers = new HashMap<>();

    /**
     * Gets user.
     */
    public static void getUser() {
        User user = null;
        while (user == null) {
            user = login();
            if (user == null) {
                System.out.println("Goodbye!");
                break;
            }
            displayOptions(user);
            user = null;
            // new page.
            System.out.println("\n\n\n\n\n\n\nPlease select another user");
        }
    }

    /**
     * Create a user or access a user.
     *
     * @return the user
     */
    public static User login(){
        String guildQuestASCII = 
            "  ____       _ _     _     ____                  _   \n" +
            " / ___|_   _(_) | __| |   / __ \\ _   _  ___  ___| |_ \n" +
            "| |  _| | | | | |/ _` |  / / _` | | | |/ _ \\/ __| __|\n" +
            "| |_| | |_| | | | (_| | | (_| | |_| |  __/\\__ \\ |_ \n" +
            " \\____|\\__,_|_|_|\\__,_|  \\__\\_|_|\\__,_|\\___||___/\\__|";

        System.out.println("Welcome to");
        System.out.println(guildQuestASCII + "\n \n");

        String prompt = """
                1 --- Create User
                2 --- Access User
                0 --- exit
                """;
        int choice = page.acceptIntUntil(prompt, 2);
        return switch (choice) {
            case 1 -> createUserUI();
            case 2 -> accessUser();
            case 0 -> null;
            default -> throw new Error("Invalid Choice, should not be possible");
        };
    }
    /**
     * Open the options to edit the characters of this user.
     *
     */
    private static void characterManager(User user) {
        CharacterUI.displayOptions(user);
    }

    /**
     * Open the options to edit the characters of this user.
     *
     */
    private static void campaignManager(User user) {
        CampaignUI.displayOptions(user);
    }
    /**
     * open the UI to create a user.
     *
     * @return the user
     */
    private static User createUserUI(){
//        Page page = new Page();
        String username = null;
        while (username == null){
            username = page.acceptStrWithValidation("enter username:\n");
            if (allUsers.containsKey(username)){
                page.print("already in use, please use another.\n");
                username = null;
            } else page.nextScreen();
        }
        User newUser = User.createUser(username);
        allUsers.put(username, newUser);
        // TODO: implement user settings here
        return newUser;
    }
    /**
     * UI to Access a user if there are some
     *
     * @return the user
     */
    private static User accessUser(){
        if (allUsers.isEmpty()){
            page.print("Sorry, no users, please create a user\n");
            return createUserUI();
        } else{
            // TODO: create list of users
            StringBuilder prompt = new StringBuilder("0 --- Exit\n");
            List<Integer> intArr = IntStream.rangeClosed(1, allUsers.size()).boxed().toList();
            int i = 1;
            for (User u : allUsers.values()){
                prompt.append(i).append(" --- ").append(u.getUsername()).append('\n');
                ++i;
            }
            int choice = page.acceptIntUntil(prompt.toString(), i-1);
            if (choice == 0) return null;
            User user = (User) allUsers.values().toArray()[choice-1];
            page.nextScreen();
            displayOptions(user);

        }
        return null;
    }

    private static void openSettings() {
        page.print("unimplemented\n");
    }

    /**
     * Open the UI to play an adventure for this user.
     *
     */
    private static void playAdventure(User user){
        AdventureUI.displayOptions(user);
    }


    /**
     * Display options.
     *
     * @param user the user
     */
    public static void displayOptions(User user){
        String prompt = "Welcome, " + user.getUsername() + "!\n";
        prompt +=  """
                    1 --- Play Adventure
                    2 --- Campaign Manager
                    3 --- Character Manager
                    
                    0 --- exit
                    """;
        int i = -1;
        while (i != 0){
            i = page.acceptIntUntil(prompt,5);
            OptionEnum choice = OptionEnum.get(i);
            switch (choice) {
                case AdventureMan -> playAdventure(user);
                case CampaignMan -> campaignManager(user);
                case CharMan -> characterManager(user);
                case Exit -> System.out.print("");
                default -> throw new Error("Invalid Choice, should not be possible");
            }
        }
    }
}
