package nl.solutionweb.rbysim.stats;

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

}
