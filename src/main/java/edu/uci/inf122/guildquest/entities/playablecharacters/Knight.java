package edu.uci.inf122.guildquest.entities.playablecharacters;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.*;
import edu.uci.inf122.guildquest.entities.interfaces.AttackMove;
import edu.uci.inf122.guildquest.ui.TerminalGrid;

import java.util.List;

public class Knight extends PlayableCharacter implements AttackMove {
    private static Knight instance;

    private final DecimalAmount damageReductionMultiplier;
    private final DecimalAmount healingMultiplier;
    private final Amount attackPower;
    private static final List<ValidMoves> moves = List.of(ValidMoves.TRAVEL, ValidMoves.ATTACK);

    private Knight(Name name) {
        super(name, new Health(100), new Level(1), new CharacterClass("Knight"));
        this.damageReductionMultiplier = new DecimalAmount(0.85);
        this.healingMultiplier = new DecimalAmount(1.15);
        this.attackPower = new Amount(15);
    }

    public static Knight getInstance(Name name) {
        if (instance == null) {
            instance = new Knight(name);
        }
        return instance;
    }

    @Override
    public void act() {
        // Placeholder for knight-specific behavior.
    }

    @Override
    public void move() {
        // Placeholder for knight-specific movement behavior.
    }

    @Override
    public void receiveHealing(Amount amount) {
        DecimalAmount modifiedAmount = amount.multiply(healingMultiplier.getCount());
        heal(modifiedAmount);
        System.out.println(getName() + " receives " + modifiedAmount + " healing. Current health: " + getHealth());
    }

    @Override
    public void takeDamage(Damage damage) {
        Damage reducedDamage = damage.multiply(damageReductionMultiplier);
        getHealth().reduceBy(reducedDamage);
        System.out.println(getName() + " takes " + reducedDamage + " damage. Remaining health: " + getHealth());
    }

    @Override
    public void attack(Entity target) {
        dealDamage(target, new Damage(attackPower));
    }

    @Override
    public List<Move.ValidMoves> getMoves(){
        return moves;
    }

    public DecimalAmount getDamageReductionMultiplier() {
        return damageReductionMultiplier;
    }

    public DecimalAmount getDamageReductionDisplay() {
        DecimalAmount reduction = new DecimalAmount(1 - damageReductionMultiplier.getCount());
        reduction = reduction.multiply(100);
        return reduction;
    }

    public DecimalAmount getHealingMultiplier() {
        return healingMultiplier;
    }

    public Amount getAttackPower() {
        return attackPower;
    }

    public DecimalAmount getDamageReductionMultiplierDisplay() {
        return damageReductionMultiplier;
    }

    @Override
    public void execute(TerminalGrid state, ValidMoves move) {

    }

}