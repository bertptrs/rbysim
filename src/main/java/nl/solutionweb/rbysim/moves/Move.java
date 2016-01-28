package nl.solutionweb.rbysim.moves;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import nl.solutionweb.rbysim.stats.StatType;
import nl.solutionweb.rbysim.stats.StatusEffect;
import nl.solutionweb.rbysim.types.Type;
import nl.solutionweb.rbysim.types.TypeXMLAdapter;

/**
 *
 * @author Bert Peters
 */
@XmlType(name = "move")
public class Move {
    /**
     * Type of the move.
     */
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(TypeXMLAdapter.class)
    private Type type;
    /**
     * Whether or not this move targets the other player.
     */
    @XmlAttribute
    private boolean targetOther = false;
    /**
     * The accuracy for the move.
     *
     * This stat is only used when the target is not the user.
     */
    @XmlAttribute
    private int accuracy = 0;
    /**
     * An additional effect taken for the move.
     */
    @XmlAttribute
    private Effect effect = Effect.NONE;
    /**
     * Whether this move deals damage.
     */
    @XmlAttribute
    private boolean dealDamage = false;
    /**
     * The probability of the effect occuring.
     */
    @XmlAttribute
    private int effectProbability = 0;
    /**
     * The strength of the effect.
     */
    @XmlAttribute
    private int effectStrength = 1;
    /**
     * The stat affected by the effect.
     */
    @XmlAttribute
    private StatType effectStat;
    /**
     * The status possibly applied by the effect.
     */
    @XmlAttribute
    private StatusEffect effectStatus = StatusEffect.NORMAL;
    /**
     * The power for the specific move.
     *
     * Only relevant for attacking moves. When the move damage is fixed, this
     * number will be used as the fixed damage.
     */
    @XmlAttribute
    private int power = 0;
    /**
     * The name for the move.
     */
    @XmlAttribute(required = true)
    private String name;

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
        return effect == Effect.FIXED_DAMAGE;
    }

    /**
     * The name for the move.
     * @return the name
     */
    public String getName() {
        return name;
    }

    @XmlType
    @XmlEnum(String.class)
    public enum Effect {
        @XmlEnumValue("miss") MISS,
        @XmlEnumValue("none") NONE,
        @XmlEnumValue("status") STATUS,
        @XmlEnumValue("statchange") STATCHANGE,
        @XmlEnumValue("recoil") RECOIL,
        @XmlEnumValue("recharge") RECHARGE,
        @XmlEnumValue("drain") DRAIN,
        @XmlEnumValue("confuse") CONFUSE,
        @XmlEnumValue("flinch") FLINCH,
        @XmlEnumValue("selfdestruct") SELFDESTRUCT,
        @XmlEnumValue("critical") EXTRA_CRIT,
        @XmlEnumValue("confuse-self") CONFUSE_SELF,
        @XmlEnumValue("fixed") FIXED_DAMAGE,
        @XmlEnumValue("heal") HEAL,
    };

}
