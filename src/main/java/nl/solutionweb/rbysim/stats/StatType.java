package nl.solutionweb.rbysim.stats;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Different stats to be had.
 *
 * @author Bert Peters
 */
@XmlType
@XmlEnum
public enum StatType {
    @XmlEnumValue("attack")
    ATTACK(0),
    @XmlEnumValue("defense")
    DEFENSE(1),
    @XmlEnumValue("speed")
    SPEED(2),
    @XmlEnumValue("special")
    SPECIAL(3),
    @XmlEnumValue("hitpoints")
    HP(4),
    @XmlEnumValue("accuracy")
    ACCURACY(5),
    @XmlEnumValue("evasion")
    EVASION(6);

    private final int index;

    private StatType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
