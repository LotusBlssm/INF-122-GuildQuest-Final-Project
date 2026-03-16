package edu.uci.inf122.guildquest.entities.domain_primitives;

public class Damage {
    private final Amount damage;
    public Damage(Amount damage){
        if (damage.getCount()<0){
            throw new IllegalArgumentException("Cannot have negative damage");
        } else this.damage = damage;
    }

    public Amount getDamage() {
        return damage;
    }

    public Damage multiply(DecimalAmount damageReductionMultiplier) {
        return new Damage(new Amount((int)Math.floor(
                damage.getCount() + damageReductionMultiplier.getCount()
        )));
    }
}
