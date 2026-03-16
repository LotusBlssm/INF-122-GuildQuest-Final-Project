package edu.uci.inf122.guildquest.entities.nonlivings;

import edu.uci.inf122.guildquest.content.Item;
import edu.uci.inf122.guildquest.entities.domain_primitives.Name;
import edu.uci.inf122.guildquest.entities.domain_primitives.Text;

public class Chest extends Nonliving {
    protected ChestStatus status;
    private Item contents;

    public Chest(Name name, Item contents, Text description) {
        super(name, description);
        this.contents = contents;
        this.status = new ChestStatus(ChestStatus.Status.CLOSED);
    }

    public boolean isOpen() {
        return status.isOpen();
    }

    public Item getContents() {
        return contents;
    }

    @Override
    public void act() {
        open();
    }

    /**
     * Attempt to open the chest. Does not grab the item.
     *
     */
    public void open(){
        if (status.isLooted()){
            System.out.println("The chest is already open and looted.");
        }
        else if (status.isOpen()){
            System.out.println("You open the chest and see: " + contents);
        }
        else if (status.isClosed()) {
            status = new ChestStatus(ChestStatus.Status.OPEN);
            System.out.println("You open the chest and see: " + contents);
        }
    }

    /**
     * attempt to take from the chest
     *
     * @param c the character
     */
    public void take(Character c){
        if (status.isClosed()){
            System.out.println("Closed, please open first");
        }
        else if (status.isOpen()){
            System.out.println("You take: " + contents.getName());
            giveItem(c);
        }
        else if (status.isLooted()){
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