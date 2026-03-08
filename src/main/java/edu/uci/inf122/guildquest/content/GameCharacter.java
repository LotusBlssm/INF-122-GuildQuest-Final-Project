package edu.uci.inf122.guildquest.content;

public class GameCharacter extends GameContent {
    // privates:
    private String characterName;
    private String characterClass;
    private int characterLevel;
    private int userID;
    // private Inventory inventory;

    // constructor:
    public GameCharacter(String charName, String charClass, int charLevel, int userID) {
        characterName = charName;
        characterClass = charClass;
        characterLevel = charLevel;
        this.userID = userID;
    }

    // check name
    public boolean checkName(String name) {
        return name == characterName;
    }

    public void setName(String name) {
        characterName = name;
    }
}
