package nl.solutionweb.rbysim.types;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bert Peters
 */
public class TypeEffectivenessHelperTest {

    /**
     * Test of getEffectiveness method, of class TypeEffectivenessHelper.
     */
    @Test
    public void testGetEffectiveness_Type_Type() {
        TypeEffectivenessHelper typeHelper = new TypeEffectivenessHelper();
        assertEquals(TypeEffectiveness.IMMUNE, typeHelper.getEffectiveness(Type.NORMAL, Type.GHOST));
        assertEquals(TypeEffectiveness.SUPER, typeHelper.getEffectiveness(Type.ROCK, Type.FIRE));
        assertEquals(TypeEffectiveness.NOT_VERY, typeHelper.getEffectiveness(Type.ELECTRIC, Type.GRASS));
        assertEquals(TypeEffectiveness.NORMAL, typeHelper.getEffectiveness(Type.POISON, Type.WATER));
    }

    /**
     * Test of getEffectiveness method, of class TypeEffectivenessHelper.
     */
    @Test
    public void testGetEffectiveness_Type_Set() {
        TypeEffectivenessHelper typeHelper = new TypeEffectivenessHelper();
        assertEquals(TypeEffectiveness.DOUBLE_SUPER, typeHelper.getEffectiveness(Type.ROCK, new TypeSet(Type.FIRE, Type.FLYING)));
        assertEquals(TypeEffectiveness.NOT_VERY, typeHelper.getEffectiveness(Type.ROCK, new TypeSet(Type.GROUND)));
        assertEquals(TypeEffectiveness.NORMAL, typeHelper.getEffectiveness(Type.WATER, new TypeSet(Type.ROCK, Type.WATER)));
    }

}
