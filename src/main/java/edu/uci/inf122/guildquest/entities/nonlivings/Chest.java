package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.content.Item;

public class Chest extends Nonliving {
    private class OpenStatus{
        enum Status {CLOSED, OPEN, LOOTED}
        Status status;
        public OpenStatus(Status status){
            this.status = status;
        }
        public boolean isOpen() {return status==Status.OPEN;}
        public boolean isClosed() {return status==Status.CLOSED;}
        public boolean isLooted() {return status==Status.LOOTED;}

    }
    private OpenStatus openStatus;
    private Item contents;

    public Chest(String name, String description, Item contents) {
        this.contents = contents;
        this.openStatus = new OpenStatus(OpenStatus.Status.CLOSED);
    }

    public boolean isOpen() {
        return openStatus.isOpen();
    }

    public void act() {
        open();
    }

    /**
     * Attempt to open the chest. Does not grab the item.
     *
     */
    public void open(){
        if (openStatus.isLooted()){
            System.out.println("The chest is already open and looted.");
        }
        else if (openStatus.isOpen()){
            System.out.println("You open the chest and see: " + contents);
        }
        else if (openStatus.isClosed()) {
            openStatus = new OpenStatus(OpenStatus.Status.OPEN);
            System.out.println("You open the chest and see: " + contents);
        }
    }

    /**
     * attempt to take from the chest
     *
     * @param c the character
     */
    public void take(Character c){
        if (openStatus.isClosed()){
            System.out.println("Closed, please open first");
        }
        else if (openStatus.isOpen()){
            System.out.println("You take: " + contents.getName());
            giveItem(c);
        }
        else if (openStatus.isLooted()){
            System.out.println("There is nothing to take in this chest.");
        }
    }

    /**
     * Logic to give the item to the player system
     *
     * @param c the character to receive the contents
     */
    public void giveItem(Character c) {
        // c.acceptItem(contents);
        contents = null;
    }
}