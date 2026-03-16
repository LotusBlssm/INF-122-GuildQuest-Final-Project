package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public class GameCharacter extends GameContent {
    // privates:
    private String characterName;
    private String characterClass;
    private int characterLevel;
    private UUID userID;
    private Inventory inventory;

    // constructor:
    public GameCharacter(String charName, String charClass, int charLevel, UUID userID) {
        characterName = charName;
        characterClass = charClass;
        characterLevel = charLevel;
        this.userID = userID;
        this.inventory = new Inventory();
    }

    public GameCharacter(String charName, String charClass) {
        this(charName, charClass, 1, null);
    }

    // check name
    public boolean checkName(String name) {
        return name.equals(characterName);
    }

    public String getName() {
        return characterName;
    }

    public void setName(String name) {
        characterName = name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public UUID getUserID() {
        return userID;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
