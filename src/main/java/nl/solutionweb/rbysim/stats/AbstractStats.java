package nl.solutionweb.rbysim.stats;

import java.util.Arrays;

/**
 * Abstract base class for stats.
 *
 * This class serves as a basis to develop the other stat classes from.
 *
 * @author Bert Peters
 */
public abstract class AbstractStats {

    private final int[] statValues = new int[StatType.values().length];

    public AbstractStats() {
        for (int i = 0; i < statValues.length; i++) {
            statValues[i] = 0;
        }
    }

    public AbstractStats(int... stats) {
        this();
        for (int i = 0; i < statValues.length && i < stats.length; i++) {
            statValues[i] = stats[i];
        }
    }

    public int getStatValue(StatType stat) {
        return statValues[stat.getIndex()];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AbstractStats)) {
            return false;
        }

        AbstractStats other = (AbstractStats) obj;

        for (StatType statType : StatType.values()) {
            if (getStatValue(statType) != other.getStatValue(statType)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(statValues);
    }

}
