package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.types.Type;

/**
 *
 * @author Bert Peters
 */
public abstract class Move {

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the targetOther
     */
    public boolean targetsOther() {
        return targetOther;
    }

    /**
     * @return the accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * @return the effect
     */
    public Effect getEffect() {
        return effect;
    }

    public enum Effect {
        MISS,
        NONE,
        STATUS,
        STATCHANGE,
        RECOIL,
        RECHARGE,
        DRAIN,
        CONFUSE,
        FLINCH,
        SELFDESTRUCT,
        OHKO,
        EXTRA_CRIT,
        CONFUSE_SELF,
    };

    private final Type type;
    private final boolean targetOther;
    private final int accuracy;
    private final Effect effect;

    public Move(Type type, boolean targetOther, int accuracy, Effect effect) {
        this.type = type;
        this.targetOther = targetOther;
        this.accuracy = accuracy;
        this.effect = effect;
    }
}
