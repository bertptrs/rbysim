package nl.solutionweb.rbysim.pokemon;

import nl.solutionweb.rbysim.stats.BaseStats;
import nl.solutionweb.rbysim.stats.EVStats;
import nl.solutionweb.rbysim.stats.IVStats;
import nl.solutionweb.rbysim.stats.StatType;
import nl.solutionweb.rbysim.stats.StatusEffect;

/**
 *
 * @author Bert Peters
 */
public class Pokemon {

	public static final int MAX_LEVEL = 100;

	private final EVStats evs;

	private final IVStats ivs;

	private final BaseStats baseStats;

	private final int level;

    private StatusEffect statusEffect;

	/**
	 * Construct a pokemon with the specified parameters.
	 *
	 * @param baseStats
	 * @param level
	 * @param ivs
	 * @param evs
	 */
	public Pokemon(BaseStats baseStats, int level, IVStats ivs, EVStats evs) {
		this.baseStats = baseStats;
		this.level = level;
		this.ivs = ivs;
		this.evs = evs;
        this.statusEffect = StatusEffect.NORMAL;
	}

	/**
	 * Construct a pokemon of maximum level, ivs, and evs.
	 *
	 * @param baseStats
	 */
	public Pokemon(BaseStats baseStats) {
		this(baseStats, MAX_LEVEL, new IVStats(), new EVStats());
	}

	/**
	 * Compute a specific stat before applying stat modifiers.
	 *
	 * @param stat The stat to compute. Calculation slightly differs between
	 * hitpoints and the rest.
	 * @return An integer value representing the unmodified stat.
	 */
	public int getUnboostedStat(StatType stat) {
		final int Cl = stat == StatType.HP ? level + 10 : 5;
		int statValue = 1 + (int) Math.sqrt(Math.max(evs.getStatValue(stat), 0));
		statValue /= 4;
		statValue = Math.min(statValue, 63);
		statValue += 2 * (ivs.getStatValue(stat) + baseStats.getStatValue(stat));
		statValue *= level;
		statValue /= 100;

		return statValue + Cl;
	}

    public boolean hasStatusEffect() {
        return statusEffect != StatusEffect.NORMAL;
    }

    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    public void setStatusEffect(StatusEffect newStatus) {
        statusEffect = newStatus;
    }
}
