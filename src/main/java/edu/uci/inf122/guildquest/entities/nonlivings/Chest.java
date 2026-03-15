package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.content.Item;

public class Chest extends Nonliving {
    private boolean isOpen;
    private final Item contents;

    public Chest(String name, String description, Item contents) {
        super(name, description);
        this.contents = contents;
        this.isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Item getContents() {
        return contents;
    }

    @Override
    public void act() {
        if (isOpen) {
            System.out.println("The chest is already open.");
            return;
        }
        open();
    }

    protected void open() {
        isOpen = true;
        giveItem();
        System.out.println("You open the chest and find: " + contents);
    }

    public void giveItem() {
        // TODO: Add item handoff to the player inventory system.
    }
}