package nl.solutionweb.rbysim.types;

/**
 * Different types to be had.
 *
 * @author Bert Peters
 */
public enum Type {
    NORMAL("Normal", false),
    FIGHTING("Fighting", false),
    FLYING("Flying", false),
    POISON("Poison", false),
    GROUND("Ground", false),
    ROCK("Rock", false),
    BUG("Bug", false),
    GHOST("Ghost", false),
    FIRE("Fire", true),
    WATER("Water", true),
    GRASS("Grass", true),
    ELECTRIC("Electric", true),
    PSYCHIC("Psychic", true),
    ICE("Ice", true),
    DRAGON("Dragon", true),
    /**
     * Typeless type. Used for calculating confusion damage.
     */
    TYPELESS("Typeless", false);

    private final boolean special;
    private final String name;

    private Type(String name, boolean special) {
        this.special = special;
        this.name = name;
    }

    public boolean isSpecial() {
        return special;
    }

    @Override
    public String toString() {
        return name;
    }
}
