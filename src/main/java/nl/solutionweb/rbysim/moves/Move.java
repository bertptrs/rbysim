package nl.solutionweb.rbysim.moves;

import java.util.Objects;
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

    public static final Move STRUGGLE = new Move(Type.NORMAL, true, 255, Effect.RECOIL, true, 255, 2, null, StatusEffect.NORMAL, 0, true, "struggle", 5, 0);
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
    private boolean targetOther = true;
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

    /**
     * The base amount of PP for this move.
     *
     * Should be divisible by 5.
     */
    @XmlAttribute
    private int pp = 5;

    /**
     * The priority for the move.
     */
    @XmlAttribute
    private int priority = 0;

    public int getPriority() {
        return priority;
    }

    private Move() {
    }

    public Move(Type type, boolean targetOther, int accuracy, Effect effect, boolean dealDamage, int effectProbability, int effectStrength, StatType effectStat, StatusEffect effectStatus, int power, boolean damageFixed, String name, int pp, int priority) {
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
        this.pp = pp;
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

    /**
     * @return the pp
     */
    public int getPp() {
        return pp;
    }

    @XmlType
    @XmlEnum(String.class)
    public static enum Effect {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.type);
        hash = 11 * hash + (this.targetOther ? 1 : 0);
        hash = 11 * hash + this.accuracy;
        hash = 11 * hash + Objects.hashCode(this.effect);
        hash = 11 * hash + (this.dealDamage ? 1 : 0);
        hash = 11 * hash + this.effectProbability;
        hash = 11 * hash + this.effectStrength;
        hash = 11 * hash + Objects.hashCode(this.effectStat);
        hash = 11 * hash + Objects.hashCode(this.effectStatus);
        hash = 11 * hash + this.power;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.pp;
        hash = 11 * hash + this.priority;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Move other = (Move) obj;
        if (this.targetOther != other.targetOther) {
            return false;
        }
        if (this.accuracy != other.accuracy) {
            return false;
        }
        if (this.dealDamage != other.dealDamage) {
            return false;
        }
        if (this.effectProbability != other.effectProbability) {
            return false;
        }
        if (this.effectStrength != other.effectStrength) {
            return false;
        }
        if (this.power != other.power) {
            return false;
        }
        if (this.pp != other.pp) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.effect != other.effect) {
            return false;
        }
        if (this.effectStat != other.effectStat) {
            return false;
        }
        if (this.effectStatus != other.effectStatus) {
            return false;
        }
        if (this.priority != other.priority) {
            return false;
        }
        return true;
    }
}
