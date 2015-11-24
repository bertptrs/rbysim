package nl.solutionweb.rbysim.stats;

/**
 * Different stats to be had.
 *
 * @author Bert Peters
 */
public enum StatType {
    ATTACK(0),
    DEFENSE(1),
    SPEED(2),
    SPECIAL(3),
    HP(4);

    private final int index;

    private StatType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
