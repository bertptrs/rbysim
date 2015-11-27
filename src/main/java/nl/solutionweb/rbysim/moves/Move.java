package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.stats.StatType;
import nl.solutionweb.rbysim.stats.StatusEffect;
import nl.solutionweb.rbysim.types.Type;

/**
 *
 * @author Bert Peters
 */
public class Move {

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
    private final StatType effectStat;
    private final StatusEffect effectStatus;
    private final int power;
    private final boolean damageFixed;
    private final String name;

    public Move(Type type, boolean targetOther, int accuracy, Effect effect, boolean dealDamage, int effectProbability, int power, boolean damageFixed, String name) {
        this.type = type;
        this.targetOther = targetOther;
        this.accuracy = accuracy;
        this.effect = effect;
        this.dealDamage = dealDamage;
        this.effectProbability = effectProbability;
        this.power = power;
        this.damageFixed = damageFixed;
        this.name = name;
    }


}
