package UI;

import Core.Campaign;
import Core.User;
import ViewsAndTimelines.Page;

import java.util.*;
import java.util.stream.IntStream;

/**
 * The type User ui.
 */
public class UserUI {
    private enum OptionEnum{
        Exit(0), CreateC(1), EditC(2), ViewC(3), CharMan(4), Settings(5);
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

    private static  void openSettings() {
        page.print("unimplemented\n");
    }
    /**
     * Open the UI to create a campaign for this user.
     *
     */
    private static  void createCampaign(User user){
        Campaign campaign = CampaignUI.createCampaign();
        campaign.setOwner(user);
        user.addCampaign(campaign);
        page.nextScreen();
    }
    /**
     * open UI to edit this user's campaign
     *
     */
    private static  void updateCampaign(User user) {
        page.nextScreen();
        CampaignUI.updateCampaign(user.getCampaigns());
    }
    /**
     * open UI to delete a campaign from thi suser
     *
     */
    private static  void removeCampaign(User user) {
        Campaign c = CampaignUI.chooseCampaign(user.getCampaigns(), false);
        if (c == null) return;
        user.removeCampaign(c);
    }

    /**
     * Display options.
     *
     * @param user the user
     */
    public static void displayOptions(User user){
        String prompt = "Welcome, " + user.getUsername() + "!\n";
        prompt +=  """
                    1 --- Create Campaign
                    2 --- Edit Campaign(s)
                    3 --- View Campaign(s)
                    
                    4 --- Character Manager
                    
                    5 --- Settings (unimplemented)
                    
                    0 --- exit
                    """;
        int i = -1;
        while (i != 0){
            i = page.acceptIntUntil(prompt,5);
            OptionEnum choice = OptionEnum.get(i);
            switch (choice) {
                case CreateC -> createCampaign(user);
                case EditC -> editCampaign(user);
                case ViewC -> viewCampaign(user);
                case CharMan -> characterManager(user);
                case Settings -> openSettings();
                case Exit -> System.out.print("");
                default -> throw new Error("Invalid Choice, should not be possible");
            }
        }
    }
    /**
     * View the campaigns this user has, then choose to do something to those ecampaigns.
     *
     */
    private static void viewCampaign(User user){
        Campaign c = CampaignUI.chooseCampaign(user.getCampaigns());
        if (c == null) return;
        CampaignUI.viewCampaign(c);
    }
    /**
     * open UI to edit a campaign.
     *
     */
    private static void editCampaign(User user){
        int choice = page.acceptIntUntil("1 --- Update Campaign\n2 --- Remove Campaign\n0 --- go back\n", 2);
        if (choice == 1) updateCampaign(user);
        else if (choice == 2) removeCampaign(user);
    }
}
