package nl.solutionweb.rbysim.stats;

/**
 * Simple container for the EV stats.
 *
 * @author Bert Peters
 */
public class EVStats extends AbstractStats {

    /**
     * Maximum value for the EV stat.
     */
    public static final int MAX_VALUE = 0xffff;

    public EVStats() {
        super(MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE);
    }

}
