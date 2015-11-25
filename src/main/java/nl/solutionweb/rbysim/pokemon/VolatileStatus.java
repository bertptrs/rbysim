package nl.solutionweb.rbysim.pokemon;

import java.util.HashMap;
import java.util.Map;
import nl.solutionweb.rbysim.stats.StatType;

/**
 * Container for the volatile state of a pokemon.
 *
 * @author Bert Peters
 */
public final class VolatileStatus {

    public static int MAX_BOOST_LEVEL = 6;

    private final Map<StatType, Integer> statModifiers = new HashMap<>();

    private final Map<StatType, Integer> lastChanges = new HashMap<>();

    /**
     * Whether or not we are currently confused.
     */
    private boolean confused;

    /**
     * The number of turns sleep is remaining.
     */
    private int sleepingTurns;

    /**
     * The number of turns we are still partially trapped.
     */
    private int trappedTurns;

    /**
     * Whether or not we are recharging.
     */
    private boolean recharging;
    /**
     * How long our light screen remains.
     */
    private int lightScreenTurns;
    /**
     * How long our reflect remains.
     */
    private int reflectTurns;

    public VolatileStatus() {
        reset();
    }

    public void reset() {
        statModifiers.clear();
        lastChanges.clear();
        confused = false;
        sleepingTurns = 0;
        recharging = false;
        trappedTurns = 0;
        reflectTurns = 0;
        lightScreenTurns = 0;
    }

    public int getStatBoostLevel(StatType stat) {
        if (statModifiers.containsKey(stat)) {
            return statModifiers.get(stat);
        } else {
            return 0;
        }
    }

    /**
     * Update the specified stat modifier with the given amount.
     *
     * This method updates the stat modifier, but respects the maximum boost
     * level.
     *
     * @param stat The stat modifier to change.
     * @param amount The number to change the stat with.
     * @return True if the stat was actually changed, false otherwise.
     */
    public boolean modifyStatBoostLevel(StatType stat, int amount) {
        final int currentLevel = getStatBoostLevel(stat);
        int newLevel = currentLevel + amount;

        // Clamp the value in the allowed range.
        newLevel = Math.max(Math.min(newLevel, MAX_BOOST_LEVEL), -MAX_BOOST_LEVEL);
        if (newLevel != currentLevel) {
            statModifiers.put(stat, newLevel);
            lastChanges.put(stat, amount);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the direction a stat last changed in.
     *
     * @param stat The stat to check.
     * @return -1, 0, or 1 when the stat was lowered, not changed, or raised.
     */
    public int getLastChange(StatType stat) {
        Integer lastChange = this.lastChanges.get(stat);
        if (lastChange == null) {
            return 0;
        } else {
            return (int) Math.signum(lastChange);
        }
    }

    /**
     * @return the confused
     */
    public boolean isConfused() {
        return confused;
    }

    /**
     * @param confused the confused to set
     */
    public void setConfused(boolean confused) {
        this.confused = confused;
    }

    /**
     * @return the sleepingTurns
     */
    public int getSleepingTurns() {
        return sleepingTurns;
    }

    /**
     * @param sleepingTurns the sleepingTurns to set
     */
    public void setSleepingTurns(int sleepingTurns) {
        this.sleepingTurns = sleepingTurns;
    }

    /**
     * @return the trappedTurns
     */
    public int getTrappedTurns() {
        return trappedTurns;
    }

    /**
     * @param trappedTurns the trappedTurns to set
     */
    public void setTrappedTurns(int trappedTurns) {
        this.trappedTurns = trappedTurns;
    }

    /**
     * @return the recharging
     */
    public boolean isRecharging() {
        return recharging;
    }

    /**
     * @param recharging the recharging to set
     */
    public void setRecharging(boolean recharging) {
        this.recharging = recharging;
    }

    public int modifyStat(StatType stat, int originalValue) {
        int stage = getStatBoostLevel(stat);
        int modifiedStat;

        if (stage > 0) {
            modifiedStat = (originalValue * (2 + stage)) / 2;
        } else {
            modifiedStat = (originalValue * 2) / (2 - stage);
        }

        return Math.min(999, Math.max(1, modifiedStat));
    }

    /**
     * @return the lightScreenTurns
     */
    public int getLightScreenTurns() {
        return lightScreenTurns;
    }

    /**
     * @param lightScreenTurns the lightScreenTurns to set
     */
    public void setLightScreenTurns(int lightScreenTurns) {
        this.lightScreenTurns = lightScreenTurns;
    }

    /**
     * @return the reflectTurns
     */
    public int getReflectTurns() {
        return reflectTurns;
    }

    /**
     * @param reflectTurns the reflectTurns to set
     */
    public void setReflectTurns(int reflectTurns) {
        this.reflectTurns = reflectTurns;
    }
}
