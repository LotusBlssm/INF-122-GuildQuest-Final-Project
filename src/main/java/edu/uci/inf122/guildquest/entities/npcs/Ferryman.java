package edu.uci.inf122.guildquest.entities.npcs;

public class Ferryman extends NPC {
    // Note: The destination and fare are currently placeholders. 
    private String destination;
    private int fare;

    public Ferryman(String name, String destination, int fare) {
        super(name);
        this.destination = destination;
        this.fare = fare;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    @Override
    public void interact() {
        System.out.println(name + " says: I can take you to " + destination + " for " + fare + " gold.");
    }
}
