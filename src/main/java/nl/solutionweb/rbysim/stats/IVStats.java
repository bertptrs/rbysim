package nl.solutionweb.rbysim.stats;

/**
 * Implementation of IVs.
 *
 * @author Bert Peters
 */
public class IVStats extends AbstractStats {

    /**
     * Maximum value for an IV stat.
     */
    public static final int MAX_VALUE = 0xf;

    /**
     * Constructor for perfect IVs.
     */
    public IVStats() {
        super(MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE);
    }

    /**
     * Construct the IVs with the specified stats.
     *
     * @param stats
     */
    public IVStats(int... stats) {
        super(stats);
    }

    /**
     * Get the stat value for a specific stat.
     *
     * This method takes into account that the HP stat is derived from all the
     * others.
     *
     * @param stat
     * @return
     */
    @Override
    public int getStatValue(StatType stat) {
        if (stat == StatType.HP) {
            return (super.getStatValue(StatType.ATTACK) % 2) * 8
                    + (super.getStatValue(StatType.DEFENSE) % 2) * 4
                    + (super.getStatValue(StatType.SPEED) % 2) * 2
                    + (super.getStatValue(StatType.SPECIAL) % 2);
        } else {
            return super.getStatValue(stat);
        }
    }

}
