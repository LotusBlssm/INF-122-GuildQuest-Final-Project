package edu.uci.inf122.guildquest.entities.domain_primitives;

public class Damage {
    private final int damage;
    public Damage(int damage){
        if (damage<0){
            throw new IllegalArgumentException("Cannot have negative damage");
        } else this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
