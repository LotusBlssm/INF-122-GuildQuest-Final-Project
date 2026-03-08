package GameContents;

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
}
