package GameContents;

import java.util.List;
import java.util.ArrayList;

public class User extends GameContent {
    private int userID;
    private String userName;
    private int currentRealmID;
    // private Settings userSetting;
    private List<GameCharacter> characters;
    private List<Campaign> campaigns;
    // private Time userTime;

    // constructor:
    public User(int id, String name, int realmID) {
        userID = id;
        userName = name;
        currentRealmID = realmID;
        characters = new ArrayList<>();
        campaigns = new ArrayList<>();
    }

    // check name
    public boolean checkName(String name) {
        return name == userName;
    }

    public void setName(String name) {
        userName = name;
    }

    public void setRealm(int id) {
        currentRealmID = id;
    }
}
