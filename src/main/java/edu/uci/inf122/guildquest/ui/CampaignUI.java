package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.content.Campaign;
import edu.uci.inf122.guildquest.content.QuestEvent;
import edu.uci.inf122.guildquest.content.VisibilityType;
import edu.uci.inf122.guildquest.content.User;

import java.util.List;

/**
 * The type Campaign ui.
 */
public class CampaignUI {
    private static final Page page = Page.getPage();

    /**
     * UI to create a named Campaign.
     *
     * @return the campaign
     */
    public static Campaign createCampaign() {
        page.nextScreen();
        String name = page.acceptStrWithValidation("Please name your campaign: ");
        // TODO: remove duplicates
        return new Campaign(name);
    }

    /**
     * Choose a campaign
     *
     * @param campaigns the user
     * @return the campaign
     */
    public static Campaign chooseCampaign(List<Campaign> campaigns) {
        // assume we include archived values
        return chooseCampaign(campaigns, true);
    }

    /**
     * Choose a campaign
     *
     * @param campaigns       the campaigns to choose from
     * @param includeArchived whether to include archived campaigns
     * @return the campaign chosen
     */
    public static Campaign chooseCampaign(List<Campaign> campaigns, boolean includeArchived) {
        StringBuilder prompt = new StringBuilder("Please choose a Campaign\n");
        Campaign choice = null;
        int i = 1;
        if (campaigns.isEmpty()) {
            page.print("Sorry, there are no campaigns, please choose something else\n");
            return null;
        }
        for (Campaign c : campaigns) {
            prompt.append(i).append(" --- ").append(c.getName()).append('\n');
            ++i;
        }
        prompt.append("0 -- exit\n");
        int input = -1;
        while (input == -1) {
            input = page.acceptIntUntil(prompt.toString(), i - 1);
            if (!includeArchived && input != 0 && campaigns.get(input - 1).getArchived()) {
                page.nextScreen();
                page.print("Please choose something not actively archived");
            } else
                break;
        }

        page.nextScreen();
        if (input == 0)
            return null;
        else
            return campaigns.get(input - 1);
    }

    /**
     * UI to select from a bunch of options to update a campaign and its quest
     * elements.
     *
     * @param campaigns the campaigns to update from
     */
    public static void updateCampaign(List<Campaign> campaigns) {
        Campaign c = chooseCampaign(campaigns);
        if (c == null) {
            return;
        }
        String prompt = """
                Please edit this campaign: %s
                1 --- Update Name
                2 --- Set visibility
                3 --- Share With Another User (unimplemented)

                4 --- Add QuestEvent
                5 --- Update QuestEvent
                6 --- Remove QuestEvent

                7 ---- Archive
                0 --- exit
                """;
        int choice = 1;
        while (choice != 0) {
            choice = page.acceptIntUntil(Page.dynamicPrompt(prompt, c.getName()), 7);
            page.nextScreen();
            switch (choice) {
                case 0 -> page.print("leaving\n");
                case 1 -> updateName(c);
                case 2 -> visibilityUI(c);
                case 3 -> shareWithOther(c);
                case 4 -> addQuestEvent(c);
                case 5 -> updateQuestEvent(c);
                case 6 -> removeQuestEvent(c);
                case 7 -> archiveCampaign(c);
            }
        }

    }

    /**
     * UI to update the campaign name
     *
     * @param c the campaign
     */
    private static void updateName(Campaign c) {
        String newName = page.acceptStrWithValidation("Please choose a new name: ");
        if (newName != null)
            c.setName(newName);
        page.print("new name set\n");
    }

    /**
     * Change visibility of a campaign between public and private
     *
     * @param c The Campaign
     */
    private static void visibilityUI(Campaign c) {
        VisibilityType opposite = (c.getVisibility() == VisibilityType.PRIVATE) ? VisibilityType.PUBLIC
                : VisibilityType.PRIVATE;
        String prompt = """
                Campaign is currently %s
                Would you like to change it to %s ?

                1 --- yes
                2 --- no
                0 --- exit
                """.formatted(c.getVisibility(), opposite);
        int choice = page.acceptIntUntil(prompt, 2);
        switch (choice) {
            case 2:
            case 0:
                return;
            case 1:
                c.setVisibility(opposite);
                break;
        }
    }

    /**
     * Archives a campaign.
     *
     * @param c The Campaign
     */
    private static void archiveCampaign(Campaign c) {

        if (c.getArchived() == true) {
            page.print("Sorry, this is already archived\n");
        } else {
            c.setArchived(true);
            page.print("Archived\n");
        }
    }

    /**
     * Shares a campaign with another user. Unimplemented
     *
     * @param c The campaign to associate with another user
     */
    private static void shareWithOther(Campaign c) {
        page.print("unimplemented\n");
    }

    /**
     * Opens UI to a QuestEvent for this campaign.
     *
     * @param c The Campaign
     */
    private static void addQuestEvent(Campaign c) {
        QuestEvent qe = QuestEventUI.createQuestEvent(c);
        if (qe != null)
            c.addEvent(qe);
    }

    /**
     * Updates a QuestEvent within a given campaign.
     *
     * @param c The Campaign
     */
    private static void updateQuestEvent(Campaign c) {
        page.nextScreen();
        QuestEventUI.updateQuestEvent(c);
    }

    /**
     * removes a questEvent from given campaign.
     *
     * @param c The Campaign
     */
    private static void removeQuestEvent(Campaign c) {
        page.nextScreen();
        QuestEventUI.removeQuestEvent(c);
    }

    /**
     * View the name, owner, visibility, and questevents of a given campaign
     *
     * @param c The Campaign
     */
    public static void viewCampaign(Campaign c) {
        String display = """
                Name: %s
                Owner: %s
                Visibility: %s
                QuestEvents:
                %s
                """.formatted(c.getName(),
                c.getOwner().getUsername(),
                c.getVisibility().toString(),
                QuestEventUI.timelineString(c));
        page.print(display);
    }

    /**
     * Open the UI to create a campaign for this user.
     *
     */
    private static void createCampaign(User user){
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


    public static void displayOptions(User user){
        String prompt = """
                What would you like to do to the campaigns?
                1 --- Create
                2 --- Edit
                
                0 --- exit
                """;
        int choice = -1;
        while (choice != 0){
            choice = page.acceptIntUntil(prompt, 3);
            page.nextScreen();
            switch (choice){
                case 0 -> page.print("leaving\n");
                case 1 -> createCampaign(user);
                case 2 -> updateCampaign(user);
            }
        }
    }
}
