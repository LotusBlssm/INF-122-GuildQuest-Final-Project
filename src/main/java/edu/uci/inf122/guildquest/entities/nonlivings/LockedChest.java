package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.content.Item;

public class LockedChest extends Chest {
    // Note: I've kept the key as a String for temporary testing purposes, 
    //  but we can change it to Item or something else later. 
    private String key;
    private boolean locked;

    public LockedChest(String name, String description, Item contents, String key) {
        super(name, description, contents);
        this.key = key;
        this.locked = true;
    }

    public boolean unlock(String providedKey) {
        if (!locked) {
            return true;
        }

        if (providedKey != null && providedKey.equals(key)) {
            locked = false;
            open();
            return true;
        }

        System.out.println("The key doesn't fit. The chest remains locked.");
        return false;
    }

    @Override
    public void act() {
        if (locked) {
            System.out.println("The chest is locked.");
            return;
        }
        super.act();
    }
}