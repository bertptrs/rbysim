package nl.solutionweb.rbysim.stats;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Status effects to be had.
 *
 * @author Bert Peters
 */
@XmlType
@XmlEnum
public enum StatusEffect {
    /**
     * No status effect.
     */
    @XmlEnumValue("normal")
    NORMAL,
    /**
     * Regular poison.
     */
    @XmlEnumValue("poison")
    POISONED,
    /**
     * Toxic poison.
     */
    @XmlEnumValue("toxic")
    BADLY_POISONED,
    /**
     * Sleeping.
     */
    @XmlEnumValue("sleep")
    ASLEEP,
    /**
     * Frozen in ice.
     */
    @XmlEnumValue("freeze")
    FROZEN,
    /**
     * Burned. Like poisoned but with attack decrease.
     */
    @XmlEnumValue("burn")
    BURNED,
    /**
     * Paralyzed. May not attack and speed drop.
     */
    @XmlEnumValue("paralyze")
    PARALYZED,
    /**
     * Fainted.
     */
    @XmlEnumValue("faint")
    FAINTED,;
}
