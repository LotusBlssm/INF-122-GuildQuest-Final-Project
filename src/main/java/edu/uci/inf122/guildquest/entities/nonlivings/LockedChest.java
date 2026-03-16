package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.content.Item;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;

public class LockedChest extends Chest {
    // Note: I've kept the key as a String for temporary testing purposes, 
    //  but we can change it to Item or something else later. 
    private String key;

    public LockedChest(Text description, Item contents, String key) {
        super(contents, description);
        this.key = key;
        this.status = new ChestStatus(ChestStatus.Status.LOCKED);
    }

    public boolean unlock(String providedKey) {
        if (status.isLocked()) {
            return true;
        }

        if (providedKey != null && providedKey.equals(key)) {
            status = new ChestStatus(ChestStatus.Status.OPEN);
            open();
            return true;
        }

        System.out.println("The key doesn't fit. The chest remains locked.");
        return false;
    }

    @Override
    public void act() {
        if (status.isLocked()) {
            System.out.println("The chest is locked.");
            return;
        }
        super.act();
    }
}