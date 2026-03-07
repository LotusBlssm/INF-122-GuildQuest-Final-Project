package Entities.NPCs;

public abstract class NPC {
    private String name;
    private int health;

    public NPC(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public abstract void act();

    public abstract void move();

    public abstract void takeDamage(int damage);

    public abstract void heal(int amount);
}