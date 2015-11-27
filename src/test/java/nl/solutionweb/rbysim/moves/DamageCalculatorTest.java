package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.pokemon.Pokemon;
import nl.solutionweb.rbysim.pokemon.VolatileStatus;
import nl.solutionweb.rbysim.stats.StatType;
import nl.solutionweb.rbysim.types.Type;
import nl.solutionweb.rbysim.types.TypeSet;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Test cases for the Damage calculator.
 *
 * @author Bert Peters
 */
public class DamageCalculatorTest {

    @Test
    public void testIsCrit() {
        DamageCalculator instance = new DamageCalculator();
        final int iterations = 10000000;
        int normalCrit = 0;
        int highCrit = 0;
        Pokemon attacker = Pokemon.MEWTWO;
        Move normalMove = new MoveBuilder("", Type.FIRE).createMove();
        Move highCritMove = new MoveBuilder("", Type.FIRE).setEffect(Move.Effect.EXTRA_CRIT).createMove();
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

    @Test
    public void testCalculateRawDamage() {
        DamageCalculator instance = new DamageCalculator();
        VolatileStatus startmieStatus = mock(VolatileStatus.class);
        when(startmieStatus.modifyStat(eq(StatType.SPECIAL), anyInt())).thenReturn(298);

        VolatileStatus slowbroStatus = mock(VolatileStatus.class);
        when(slowbroStatus.modifyStat(eq(StatType.SPECIAL), anyInt())).thenReturn(999);

        Pokemon startmie = mock(Pokemon.class);
        TypeSet typings = new TypeSet(Type.WATER, Type.PSYCHIC);
        when(startmie.getTypes()).thenReturn(typings);
        when(startmie.getLevel()).thenReturn(100);
        when(startmie.getUnboostedStat(StatType.SPECIAL)).thenReturn(298);

        Pokemon slowbro = mock(Pokemon.class);
        when(slowbro.getTypes()).thenReturn(typings);
        when(slowbro.getUnboostedStat(StatType.SPECIAL)).thenReturn(258);

        Move thunderbolt = mock(Move.class);
        when(thunderbolt.isDamaging()).thenReturn(true);
        when(thunderbolt.getPower()).thenReturn(95);
        when(thunderbolt.getType()).then(new Answer<Type>() {
            @Override
            public Type answer(InvocationOnMock invocation) throws Throwable {
                return Type.ELECTRIC;
            }
        });

        int result = instance.calculateRawDamage(thunderbolt, false, startmie, startmieStatus, slowbro, slowbroStatus);
        // Source: The RBY book.
        Assert.assertEquals("Raw result should match computed one.", 50, result);
        int critResult = instance.calculateRawDamage(thunderbolt, true, startmie, startmieStatus, slowbro, slowbroStatus);
        Assert.assertEquals("Critical hit result should ignore status changes.", 364, critResult);
    }

    @Test
    public void testCalculateHitProbability() {
        DamageCalculator instance = new DamageCalculator();
        VolatileStatus attackerStatus = mock(VolatileStatus.class);
        when(attackerStatus.modifyStat(eq(StatType.ACCURACY), anyInt())).thenReturn(255, 127, 255, 127, 255, 127);

        VolatileStatus defenderStatus = mock(VolatileStatus.class);
        when(defenderStatus.modifyStat(eq(StatType.EVASION), anyInt())).thenReturn(255, 255, 510, 510, 255, 255);

        Move move = mock(Move.class);
        when(move.getAccuracy()).thenReturn(255, 255, 255, 255, 127, 127);
        when(move.targetsOther()).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return Boolean.TRUE;
            }
        });

        final int[] expectedResults = new int[]{255, 127, 127, 63, 127, 63};
        int[] actualResults = new int[expectedResults.length];
        for (int i = 0; i < expectedResults.length; i++) {
            actualResults[i] = instance.hitProbability(move, attackerStatus, defenderStatus);
        }

        Assert.assertArrayEquals(expectedResults, actualResults);
    }
}
