package GameContentManagers;

import GameContents.Campaign;
import GameContents.User;

import java.util.List;
import java.util.ArrayList;

public class CampaignManager {
    private static CampaignManager campaignManager;
    private List<Campaign> campaigns;

    private CampaignManager() {
        campaigns = new ArrayList<>();
    }

    public static CampaignManager getCampaignManager() {
        if (campaignManager == null) {
            campaignManager = new CampaignManager();
        }
        return campaignManager;
    }

    // add new user
    public boolean addCampaign(Campaign newCamp) {
        if (campaigns.contains(newCamp)) {
            return false;
        }
        campaigns.add(newCamp);
        return true;
    }

    // remove user
    public boolean removeCampaign(String name) {
        for (Campaign camp : campaigns) {
            if (camp.checkName(name)) {
                campaigns.remove(camp);
                return true;
            }
        }
        return false;
    }

    // update user name
    public boolean updateCampaignName(String oldName, String newName) {
        for (Campaign camp : campaigns) {
            if (camp.checkName(oldName)) {
                camp.setName(newName);
                return true;
            }
        }
        return false;
    }
}
