package edu.uci.inf122.guildquest.entities.domain_primitives;


/**
 * The type Health. Has capacity and health
 */
public class Health {
    private final static int HEALTH_DEFAULT=10;
    private final int capacity;
    private int health;

    /**
     * Instantiates a new Health at a specific health. Requires capacity
     *
     * @param h health at instantiation
     * @param c capacity
     */
    public Health(int h, int c){
        if (c <= 0){
            throw new IllegalArgumentException("Cannot have negative capacity at first.");
        }
        if (health < 0){
            throw new IllegalArgumentException("Cannot have negative health at first.");
        }
        health = h;
        capacity = c;
    }

    /**
     * Instantiates a new Health at maximum health given a capacity
     *
     * @param capacity the capacity
     */
    public Health(int capacity){
        if (capacity <= 0){
            throw new IllegalArgumentException("Cannot have negative capacity at first.");
        }
        this.capacity = capacity;
        this.health = capacity;
    }

    /**
     * reduce health by damage amount.
     *
     * @param damage the damage
     */
    public void reduceBy(Damage damage) {
        if (damage.getDamage().getCount()> health) health = 0;
        else health -= damage.getDamage().getCount();
    }

    /**
     * heal by nonnegative amount
     *
     * @param amount the amount
     */
    public void increaseBy(Amount amount) {
        if (health + amount.getCount() >= capacity) health = capacity;
        else health+=amount.getCount();
    }
    public void increaseBy(DecimalAmount amount) {
        if (health + amount.getCount() >= capacity) health = capacity;
        else health = (int) Math.floor(amount.getCount()+health);
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return health + "/" + capacity;
    }

    public static Health createDefault(){
        return new Health(HEALTH_DEFAULT, HEALTH_DEFAULT);
    }

    public boolean isFull() {
        return health==capacity;
    }
}
