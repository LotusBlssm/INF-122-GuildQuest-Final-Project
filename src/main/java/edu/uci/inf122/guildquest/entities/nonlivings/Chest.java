package edu.uci.inf122.guildquest.entities.nonlivings;

public class Chest extends Nonliving {
    private boolean isOpen;
    private Item contents;

    public Chest(String name, String description, Item contents) {
        super(name, description);
        this.contents = contents;
        this.isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void act() {
        if (!isOpen) {
            isOpen = true;
            giveItem();
            System.out.println("You open the chest and find: " + contents);
        } else {
            System.out.println("The chest is already open.");
        }
    }

    public void giveItem() {
        // Logic to give the item to the player system
    }
}