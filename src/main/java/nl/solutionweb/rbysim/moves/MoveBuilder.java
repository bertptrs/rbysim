package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.stats.StatType;
import nl.solutionweb.rbysim.stats.StatusEffect;
import nl.solutionweb.rbysim.types.Type;


public class MoveBuilder {

    private final Type type;
    private boolean targetOther = true;
    private int accuracy = 255;
    private Move.Effect effect = Move.Effect.NONE;
    private boolean dealDamage = false;
    private int effectProbability = 0;
    private int effectStrength = 1;
    private StatType effectStat = StatType.ATTACK;
    private StatusEffect effectStatus = StatusEffect.NORMAL;
    private int power = 0;
    private boolean damageFixed = false;
    private final String name;
    private int pp = 5;

    public MoveBuilder(String name, Type type) {
        this.type = type;
        this.name = name;
    }

    public MoveBuilder setTargetOther(boolean targetOther) {
        this.targetOther = targetOther;
        return this;
    }

    public MoveBuilder setAccuracy(int accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public MoveBuilder setEffect(Move.Effect effect) {
        this.effect = effect;
        return this;
    }

    public MoveBuilder setDealDamage(boolean dealDamage) {
        this.dealDamage = dealDamage;
        return this;
    }

    public MoveBuilder setEffectProbability(int effectProbability) {
        this.effectProbability = effectProbability;
        return this;
    }

    public MoveBuilder setEffectStrength(int effectStrength) {
        this.effectStrength = effectStrength;
        return this;
    }

    public MoveBuilder setEffectStat(StatType effectStat) {
        this.effectStat = effectStat;
        return this;
    }

    public MoveBuilder setEffectStatus(StatusEffect effectStatus) {
        this.effectStatus = effectStatus;
        return this;
    }

    public MoveBuilder setPower(int power) {
        this.power = power;
        if (power > 0) {
            setDealDamage(true);
        }
        return this;
    }

    public MoveBuilder setDamageFixed(boolean damageFixed) {
        this.damageFixed = damageFixed;
        return this;
    }

    public MoveBuilder setPP(int pp) {
        this.pp = pp;
        return this;
    }

    public Move createMove() {
        return new Move(type, targetOther, accuracy, effect, dealDamage, effectProbability, effectStrength, effectStat, effectStatus, power, damageFixed, name, pp);
    }

}
