package edu.uci.inf122.guildquest.entities.npcs;

import edu.uci.inf122.guildquest.entities.domain_primitives.*;

import static edu.uci.inf122.guildquest.ui.UserUI.page;

public class Ferryman extends NPC {
    // Note: The destination and fare are currently placeholders.
    private XYPlace destination;
    private Amount fare;


    public Ferryman(Name name, XYPlace destination, Amount fare) {
        super(name, Health.createDefault());
        this.destination = destination;
        this.fare = fare;
    }

    public Place getDestination() {
        return destination;
    }

    public Amount getFare() {
        return fare;
    }

    public void interact() {
        page.print(name + " says: I can take you to " + destination + " for " + fare + " gold.");
    }

    @Override
    public void act() {
        interact();
    }

    @Override
    public void move() {
        System.out.println(getName() + " rows to " + destination + ".");
        //TODO: Implement actual movement logic.
    }

    @Override
    public void takeDamage(Damage damage) {
        health.reduceBy(damage);
        if (isDead()){
            System.out.println(name + "died!");
        }
    }

    @Override
    public void heal(Amount amount) {
        health.increaseBy(amount);
    }
}
