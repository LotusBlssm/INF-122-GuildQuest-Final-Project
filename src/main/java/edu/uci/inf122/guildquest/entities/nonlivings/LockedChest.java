package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.content.Item;

public class LockedChest extends Chest {
    // Note: I've kept the key as a String for temporary testing purposes, 
    //  but we can change it to Item or something else later. 
    private Key key;

    public LockedChest(String name, String description, Item contents, Key key) {
        super(name, description, contents);
        this.key = key;
    }

    public boolean unlock(Key providedKey, Character c) {
        if (providedKey.equals(key)) {
            open();
            take(c);
            return true;
        } else {
            System.out.println("The key doesn't fit. The chest remains locked.");
            return false;
        }
    }
}