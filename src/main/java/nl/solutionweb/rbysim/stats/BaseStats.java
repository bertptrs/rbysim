package nl.solutionweb.rbysim.stats;

/**
 * Class for representing BaseStats.
 *
 * @author Bert Peters
 */
public class BaseStats extends AbstractStats {

    public static final int MAX_VALUE = 0xff;

    /**
     * Base stat definition for Mew.
     */
    public static final BaseStats MEW = new BaseStats(100, 100, 100, 100, 100);
    /**
     * Base stat definition for Mewtwo.
     */
    public static final BaseStats MEWTWO = new BaseStats(106, 110, 90, 130, 154);

    public BaseStats(int hp, int attack, int defense, int speed, int special) {
        super(attack, defense, speed, special, hp);
    }

}
