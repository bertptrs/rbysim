package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.pokemon.Pokemon;
import nl.solutionweb.rbysim.types.Type;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for the Damage calculator.
 *
 * @author Bert Peters
 */
public class DamageCalculatorTest {

    @Test
    public void testIsCrit() {
        DamageCalculator instance = new DamageCalculator();
        final int iterations = 1000000;
        int normalCrit = 0;
        int highCrit = 0;
        Pokemon attacker = Pokemon.MEWTWO;
        DamagingMove normalMove = new DamagingMove(Type.FIRE, 80, 255, Move.Effect.NONE);
        DamagingMove highCritMove = new DamagingMove(Type.FIRE, 80, 255, Move.Effect.EXTRA_CRIT);
        for (int i = 0; i < iterations; i++) {
            if (instance.isCrit(normalMove, attacker)) {
                normalCrit++;
            }
            if (instance.isCrit(highCritMove, attacker)) {
                highCrit++;
            }
        }

        // Source: http://www.smogon.com/rb/articles/critical_hits
        Assert.assertEquals(0.254, normalCrit / (double) iterations, 0.001);
        // Assure that the we still have some non-crits due to the strict comparison.
        Assert.assertEquals(0.996, highCrit / (double) iterations, 0.001);
    }
}
