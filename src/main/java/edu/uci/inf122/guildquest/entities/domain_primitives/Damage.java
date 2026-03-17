package edu.uci.inf122.guildquest.entities.domain_primitives;

public class Damage {
    private final Amount damage;
    public Damage(Amount damage){
        if (damage.getCount()<0){
            throw new IllegalArgumentException("Cannot have negative damage");
        } else this.damage = damage;
    }
    public Damage(int damage){
        Amount d = new Amount(damage);
        if (damage<0){
            throw new IllegalArgumentException("Cannot have negative damage");
        } else this.damage = d;
    }

    public Amount getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return String.valueOf(damage.getCount());
    }

    public Damage multiply(DecimalAmount i) {
        return new Damage(new Amount((int)Math.floor(
                damage.getCount() + i.getCount()
        )));
    }
}
