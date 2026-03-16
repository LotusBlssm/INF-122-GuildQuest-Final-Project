package edu.uci.inf122.guildquest.content;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class User extends GameContent {
    private UUID userID;
    private String userName;
    private UUID currentRealmID;
    // private Settings userSetting;
    private List<GameCharacter> characters;
    private List<Campaign> campaigns;
    // private Time userTime;

    // constructor:
    public User(UUID id, String name, UUID realmID) {
        userID = id;
        userName = name;
        currentRealmID = realmID;
        characters = new ArrayList<>();
        campaigns = new ArrayList<>();
    }

    // check name
    public boolean checkName(String name) {
        return name.equals(userName);
    }

    public void setName(String name) {
        userName = name;
    }

    public String getName() {
        return userName;
    }

    public void setRealm(UUID id) {
        currentRealmID = id;
    }
}
