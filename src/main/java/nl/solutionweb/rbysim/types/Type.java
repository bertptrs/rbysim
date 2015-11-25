package nl.solutionweb.rbysim.types;

/**
 * Different types to be had.
 *
 * @author Bert Peters
 */
public enum Type {
    NORMAL(false),
    FIGHTING(false),
    FLYING(false),
    POISON(false),
    GROUND(false),
    ROCK(false),
    BUG(false),
    GHOST(false),
    FIRE(true),
    WATER(true),
    GRASS(true),
    ELECTRIC(true),
    PSYCHIC(true),
    ICE(true),
    DRAGON(true);

    private final boolean special;

    private Type(boolean special) {
        this.special = special;
    }

    public boolean isSpecial() {
        return special;
    }
}
