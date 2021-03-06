package nl.solutionweb.rbysim.moves;

import java.util.concurrent.ThreadLocalRandom;
import nl.solutionweb.rbysim.pokemon.Pokemon;
import nl.solutionweb.rbysim.pokemon.VolatileStatus;
import nl.solutionweb.rbysim.stats.StatType;
import nl.solutionweb.rbysim.types.TypeEffectiveness;
import nl.solutionweb.rbysim.types.TypeEffectivenessHelper;
import nl.solutionweb.rbysim.util.MathUtil;

/**
 * Calculate damage for a specific move.
 *
 * @author Bert Peters
 */
public class DamageCalculator {

    public int calculateDamage(Move move, Pokemon attacker,
            VolatileStatus attackerStatus, Pokemon defender,
            VolatileStatus defenderStatus) {
        int rawDamage = calculateRawDamage(move, attacker, attackerStatus, defender, defenderStatus);
        int modifier = ThreadLocalRandom.current().nextInt(217, 256);
        rawDamage *= modifier;

        return rawDamage / 0xff;
    }

    /**
     * Calculate the raw damage before applying the random modifier.
     *
     * This method applies the regular method of determining critical hits, so
     * it is a more accurate method of determining the expected damage for a
     * move.
     *
     * @param move The move to be used.
     * @param attacker The attacking pokemon
     * @param attackerStatus The attacker's volatile status.
     * @param defender The defending pokemon
     * @param defenderStatus The defenders volatile status.
     * @return The damage, without the random modifier.
     */
    public int calculateRawDamage(Move move, Pokemon attacker,
            VolatileStatus attackerStatus, Pokemon defender,
            VolatileStatus defenderStatus) {
        return calculateRawDamage(move, isCrit(move, attacker),
                attacker, attackerStatus, defender, defenderStatus);
    }

    /**
     * Calculate the raw damage before applying the random modifier.
     *
     * @param move The move to be used.
     * @param isCritical Whether or not this move is to be a critical hit.
     * @param attacker The attacking pokemon
     * @param attackerStatus The attacker's volatile status
     * @param defender The defending pokemon
     * @param defenderStatus The defenders volatile status.
     * @return The raw damage calculated.
     */
    public int calculateRawDamage(Move move, boolean isCritical, Pokemon attacker,
            VolatileStatus attackerStatus, Pokemon defender,
            VolatileStatus defenderStatus) {
        if (!move.isDamaging()) {
            throw new IllegalArgumentException("Can only calculate damage for damaging moves.");
        }
        final TypeEffectivenessHelper typeEffectivenessHelper = new TypeEffectivenessHelper();
        final int LC = attacker.getLevel() * (isCritical ? 2 : 1);
        int attackStat = getAttackStat(move, isCritical, attacker, attackerStatus);
        int defendStat = getDefendStat(move, isCritical, defender, defenderStatus);
        if (attackStat > 0xff || defendStat > 0xff) {
            defendStat /= 4;
            attackStat /= 4;
        }
        if (hasBarrier(move, defenderStatus)) {
            attackStat /= 4;
        }

        if (move.getEffect() == Move.Effect.SELFDESTRUCT) {
            defendStat /= 2;
        }

        int damage = MathUtil.fracMultiply(LC % 256, 2, 5) + 2;
        damage *= move.getPower();
        damage *= Math.max(attackStat, 1);
        damage /= Math.max(defendStat, 1);
        damage /= 50;
        damage = 2 + Math.min(damage, 997);
        if (attacker.getTypes().contains(move.getType())) {
            // Add stab
            damage *= 3;
            damage /= 2;
        }

        TypeEffectiveness effectiveness = typeEffectivenessHelper.getEffectiveness(move.getType(), defender.getTypes());
        damage = effectiveness.modifyDamage(damage);

        return damage;
    }

    /**
     * Check whether a pokemon has the proper barrier for a given move.
     *
     * @param move The move currently used.
     * @param status The
     * @return
     */
    private boolean hasBarrier(Move move, VolatileStatus status) {
        int turnsRemaining = move.getType().isSpecial() ? status.getLightScreenTurns() : status.getReflectTurns();
        return turnsRemaining > 0;
    }

    private int getAttackStat(Move move, boolean isCritical, Pokemon attacker, VolatileStatus attackerStatus) {
        StatType stat = move.getType().isSpecial() ? StatType.SPECIAL : StatType.ATTACK;
        return getStat(stat, isCritical, attacker, attackerStatus);
    }

    private int getDefendStat(Move move, boolean isCritical, Pokemon defender, VolatileStatus defenderStatus) {
        StatType stat = move.getType().isSpecial() ? StatType.SPECIAL : StatType.DEFENSE;
        return getStat(stat, isCritical, defender, defenderStatus);
    }

    private int getStat(StatType stat, boolean isCritical, Pokemon pokemon, VolatileStatus status) {
        int originalStat = pokemon.getUnboostedStat(stat);
        return isCritical ? originalStat : status.modifyStat(stat, originalStat);
    }

    /**
     * Compute whether or not a move should be a critical hit.
     *
     * The output of this method is random.
     *
     * @param move THe move to be used. Relevant because some moves have a
     * larger critical hit ratio.
     * @param attacker The attacking pokemon
     * @return boolean True if the move should be a critical hit, false
     * otherwise.
     */
    public boolean isCrit(Move move, Pokemon attacker) {
        int speedStat = attacker.getBaseStats().getStatValue(StatType.SPEED);
        int modifier = move.getEffect() == Move.Effect.EXTRA_CRIT ? 8 : 1;
        int rating = Math.min(0xff, modifier * (speedStat / 2));
        return rating > ThreadLocalRandom.current().nextInt(0x100);
    }

    /**
     * Calculate the probability that a given move hits.
     *
     * @param move The move used.
     * @param attackerStatus The attacker status, used for his accuracy rating.
     * @param defenderStatus The defender status, used for his evasion rating.
     * @return The probability of hitting as a fraction of 256. The probability
     * is the value returned by this method divided by 256.
     */
    public int hitProbability(Move move, VolatileStatus attackerStatus, VolatileStatus defenderStatus) {
        if (!move.targetsOther()) {
            throw new IllegalArgumentException("Self targeting moves do not have a hit probability.");
        }

        int baseAccuracy = move.getAccuracy();
        int accuracy = attackerStatus.modifyStat(StatType.ACCURACY, 255);
        int evasion = defenderStatus.modifyStat(StatType.EVASION, 255);

        return MathUtil.clamp((accuracy * baseAccuracy) / evasion, 0, 255);
    }

    /**
     * Check whether a move should hit or not.
     *
     * This method takes into account that an always-hitting move misses once
     * every 256 times.
     *
     * @param move The move used.
     * @param attackerStatus The attacker status, used for his accuracy rating.
     * @param defenderStatus The defender status, used for his evasion rating.
     * @return True if it hits, false otherwise.
     */
    public boolean isHit(Move move, VolatileStatus attackerStatus, VolatileStatus defenderStatus) {
        int hitProbability = hitProbability(move, attackerStatus, defenderStatus);
        int random = ThreadLocalRandom.current().nextInt(256);

        return random < hitProbability;
    }
}
