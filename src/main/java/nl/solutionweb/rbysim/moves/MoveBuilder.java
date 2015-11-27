package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.types.Type;


public class MoveBuilder {

    private final Type type;
    private boolean targetOther = true;
    private int accuracy = 255;
    private Move.Effect effect = Move.Effect.NONE;
    private boolean dealDamage = false;
    private int effectProbability = 0;
    private int power = 0;
    private boolean damageFixed = false;
    private final String name;

    public MoveBuilder(String name, Type type) {
        this.name = name;
        this.type = type;
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

    public MoveBuilder setPower(int power) {
        this.power = power;
        return this;
    }

    public MoveBuilder setDamageFixed(boolean damageFixed) {
        this.damageFixed = damageFixed;
        return this;
    }

    public Move createMove() {
        return new Move(type, targetOther, accuracy, effect, dealDamage, effectProbability, power, damageFixed, name);
    }

}
