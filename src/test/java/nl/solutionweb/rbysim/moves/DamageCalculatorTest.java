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
        VolatileStatus status = new VolatileStatus();

        Pokemon startmie = mock(Pokemon.class);
        TypeSet typings = new TypeSet(Type.WATER, Type.PSYCHIC);
        when(startmie.getTypes()).thenReturn(typings);
        when(startmie.getLevel()).thenReturn(100);
        when(startmie.getUnboostedStat(StatType.SPECIAL)).thenReturn(298);

        Pokemon slowbro = mock(Pokemon.class);
        when(startmie.getLevel()).thenReturn(100);
        when(slowbro.getTypes()).thenReturn(typings);
        when(slowbro.getUnboostedStat(StatType.SPECIAL)).thenReturn(258);

        Move thunderbolt = mock(Move.class);
        when(thunderbolt.isDamaging()).thenReturn(true);
        when(thunderbolt.getPower()).thenReturn(95);
        when(thunderbolt.getType()).then(new Answer<Type> () {
            @Override
            public Type answer(InvocationOnMock invocation) throws Throwable {
                return Type.ELECTRIC;
            }
        });

        int result = instance.calculateRawDamage(thunderbolt, false, startmie, status, slowbro, status);
        // Source: https://pokemonshowdown.com/damagecalc/
        Assert.assertEquals("Raw result should match computed one.", 188, result);
    }
}
