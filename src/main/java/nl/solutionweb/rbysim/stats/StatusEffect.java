package nl.solutionweb.rbysim.stats;

/**
 * Status effects to be had.
 *
 * @author Bert Peters
 */
public enum StatusEffect {
    /**
     * No status effect.
     */
    NORMAL,
    /**
     * Regular poison.
     */
    POISONED,
    /**
     * Toxic poison.
     */
    BADLY_POISONED,
    /**
     * Sleeping.
     */
    ASLEEP,
    /**
     * Frozen in ice.
     */
    FROZEN,
    /**
     * Burned. Like poisoned but with attack decrease.
     */
    BURNED,
    /**
     * Paralyzed. May not attack and speed drop.
     */
    PARALYZED,
    /**
     * Fainted.
     */
    FAINTED,
}
