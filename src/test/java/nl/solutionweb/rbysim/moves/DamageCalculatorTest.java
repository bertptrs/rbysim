package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.pokemon.Pokemon;
import nl.solutionweb.rbysim.pokemon.VolatileStatus;
import nl.solutionweb.rbysim.types.Type;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author s1147919
 */
public class DamageCalculatorTest {

    @Test
    public void testIsCrit() {
        DamageCalculator instance = new DamageCalculator();
        final int iterations = 1000000;
        int normalCrit = 0;
        int highCrit = 0;
        VolatileStatus status = new VolatileStatus();
        Pokemon attacker = Pokemon.MEWTWO;
        DamagingMove normalMove = new DamagingMove(Type.FIRE, 80, 255, Move.Effect.NONE);
        DamagingMove highCritMove = new DamagingMove(Type.FIRE, 80, 255, Move.Effect.EXTRA_CRIT);
        for (int i = 0; i < iterations; i++) {
            if (instance.isCrit(normalMove, attacker, status)) {
                normalCrit++;
            }
            if (instance.isCrit(highCritMove, attacker, status)) {
                highCrit++;
            }
        }

        Assert.assertEquals(0.996, ((double) highCrit) / iterations, 0.01);
    }
}
