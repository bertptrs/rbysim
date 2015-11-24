/*
 */
package nl.solutionweb.rbysim.types;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for the type effectiveness.
 *
 * @author Bert Peters
 */
public class TypeEffectivenessTest {

    /**
     * Test recombination of type effectivenesses.
     */
    @Test
    public void testCombine() {
        // Check identity
        assertEquals(TypeEffectiveness.NORMAL, TypeEffectiveness.NORMAL.combine(TypeEffectiveness.NORMAL));

        // Check cancellation
        assertEquals(TypeEffectiveness.NORMAL, TypeEffectiveness.NOT_VERY.combine(TypeEffectiveness.SUPER));

        // Check stacking
        assertEquals(TypeEffectiveness.DOUBLE_SUPER, TypeEffectiveness.SUPER.combine(TypeEffectiveness.SUPER));
    }

    /**
     * Test proper invalid state handling for stacking double effects.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCombineFailure() {
        TypeEffectiveness.DOUBLE_NOT_VERY.combine(TypeEffectiveness.NORMAL);
    }

}
