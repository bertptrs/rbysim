package nl.solutionweb.rbysim.pokemon;

import java.io.IOException;
import java.util.Set;
import nl.solutionweb.rbysim.stats.BaseStats;
import nl.solutionweb.rbysim.types.Type;
import nl.solutionweb.rbysim.types.TypeSet;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Test;

/**
 *
 * @author Bert Peters <bert.ljpeters@gmail.com>
 */
public class PokedexTest {

    /**
     * Test of instance method, of class Pokedex.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testInstance() throws Exception {
        Pokedex result = Pokedex.instance();
        assertNotNull(result);
    }

    @Test
    public void testGetBaseStats() {
        try {
            Pokedex instance = Pokedex.instance();
            Assume.assumeNotNull(instance);

            assertEquals(BaseStats.MEWTWO, instance.getBaseStats("Mewtwo"));
        } catch (IOException ex) {
            Assume.assumeNoException(ex);
        }
    }

    @Test
    public void testGetTypes() {
        try {
            Pokedex instance = Pokedex.instance();

            Set<Type> types1 = new TypeSet(Type.WATER);
            Set<Type> types2 = new TypeSet(Type.FIRE, Type.FLYING);

            assertEquals(types1, instance.getTypes("Squirtle"));
            assertEquals(types2, instance.getTypes("Charizard"));
        } catch (IOException ex) {
            Assume.assumeNoException(ex);
        }
    }

    @Test(expected = NoSuchPokemonException.class)
    public void testLookupFailure() {
        try {
            Pokedex instance = Pokedex.instance();

            instance.getBaseStats("Nonexistant");
        } catch (IOException ex) {
            Assume.assumeNoException(ex);
        }

    }

}
