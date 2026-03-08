package edu.uci.inf122.guildquest.entities.nonlivings;

public class LockedChest extends Chest {
    // Note: I've kept the key as a String for temporary testing purposes, 
    //  but we can change it to Item or something else later. 
    private String key;

    public LockedChest(String name, String description, Item contents, String key) {
        super(name, description, contents);
        this.key = key;
    }

    public boolean unlock(String providedKey) {
        if (providedKey.equals(key)) {
            open();
            return true;
        } else {
            System.out.println("The key doesn't fit. The chest remains locked.");
            return false;
        }
    }
}