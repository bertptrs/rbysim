package nl.solutionweb.rbysim;

import nl.solutionweb.rbysim.stats.BaseStats;
import nl.solutionweb.rbysim.stats.StatType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the Pokemon class.
 *
 * @author Bert Peters
 */
public class PokemonTest {
	/**
	 * Test of getUnboostedStat method, of class Pokemon.
	 *
	 * This test verifies the maxed out stats for mewtwo.
	 */
	@Test
	public void testGetUnboostedStat() {
		Pokemon instance = new Pokemon(BaseStats.MEWTWO);
		assertEquals("HP stat", 415, instance.getUnboostedStat(StatType.HP));
		assertEquals("Attack stat", 318, instance.getUnboostedStat(StatType.ATTACK));
		assertEquals("Defense stat", 278, instance.getUnboostedStat(StatType.DEFENSE));
		assertEquals("Special stat", 406, instance.getUnboostedStat(StatType.SPECIAL));
		assertEquals("Speed stat", 358, instance.getUnboostedStat(StatType.SPEED));
	}

}
