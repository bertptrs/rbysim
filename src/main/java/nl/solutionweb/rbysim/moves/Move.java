package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.stats.StatType;
import nl.solutionweb.rbysim.stats.StatusEffect;
import nl.solutionweb.rbysim.types.Type;

/**
 *
 * @author Bert Peters
 */
public class Move {
    /**
     * Type of the move.
     */
    private final Type type;
    /**
     * Whether or not this move targets the other player.
     */
    private final boolean targetOther;
    /**
     * The accuracy for the move.
     *
     * This stat is only used when the target is not the user.
     */
    private final int accuracy;
    /**
     * An additional effect taken for the move.
     */
    private final Effect effect;
    /**
     * Whether this move deals damage.
     */
    private final boolean dealDamage;
    /**
     * The probability of the effect occuring.
     */
    private final int effectProbability;
    /**
     * The strength of the effect.
     */
    private final int effectStrength;
    /**
     * The stat affected by the effect.
     */
    private final StatType effectStat;
    /**
     * The status possibly applied by the effect.
     */
    private final StatusEffect effectStatus;
    /**
     * The power for the specific move.
     *
     * Only relevant for attacking moves. When the move damage is fixed, this
     * number will be used as the fixed damage.
     */
    private final int power;
    /**
     * Whether the damage is fixed.
     *
     * When true, the damage calculated is always exactly the power of the move.
     */
    private final boolean damageFixed;
    /**
     * The name for the move.
     */
    private final String name;

    public Move(Type type, boolean targetOther, int accuracy, Effect effect, boolean dealDamage, int effectProbability, int effectStrength, StatType effectStat, StatusEffect effectStatus, int power, boolean damageFixed, String name) {
        this.type = type;
        this.targetOther = targetOther;
        this.accuracy = accuracy;
        this.effect = effect;
        this.dealDamage = dealDamage;
        this.effectProbability = effectProbability;
        this.effectStrength = effectStrength;
        this.effectStat = effectStat;
        this.effectStatus = effectStatus;
        this.power = power;
        this.damageFixed = damageFixed;
        this.name = name;
    }

    /**
     * Type of the move.
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Whether or not this move targets the other player.
     * @return the targetOther
     */
    public boolean targetsOther() {
        return targetOther;
    }

    /**
     * The accuracy for the move.
     *
     * This stat is only used when the target is not the user.
     * @return the accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * An additional effect taken for the move.
     * @return the effect
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Whether this move deals damage.
     * @return the dealDamage
     */
    public boolean isDamaging() {
        return dealDamage;
    }

    /**
     * The probability of the effect occuring.
     * @return the effectProbability
     */
    public int getEffectProbability() {
        return effectProbability;
    }

    /**
     * The strength of the effect.
     * @return the effectStrength
     */
    public int getEffectStrength() {
        return effectStrength;
    }

    /**
     * The stat affected by the effect.
     * @return the effectStat
     */
    public StatType getEffectStat() {
        return effectStat;
    }

    /**
     * The status possibly applied by the effect.
     * @return the effectStatus
     */
    public StatusEffect getEffectStatus() {
        return effectStatus;
    }

    /**
     * The power for the specific move.
     *
     * Only relevant for attacking moves. When the move damage is fixed, this
     * number will be used as the fixed damage.
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * Whether the damage is fixed.
     *
     * When true, the damage calculated is always exactly the power of the move.
     * @return the damageFixed
     */
    public boolean isDamageFixed() {
        return damageFixed;
    }

    /**
     * The name for the move.
     * @return the name
     */
    public String getName() {
        return name;
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

    

}
