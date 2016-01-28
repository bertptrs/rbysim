package nl.solutionweb.rbysim.moves;

import java.io.IOException;
import nl.solutionweb.rbysim.types.Type;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for the move database class.
 *
 * @author Bert Peters <bert.ljpeters@gmail.com>
 */
public class MoveDatabaseTest {

    /**
     * Assert the correct properties of Tackle and Struggle from the move pool.
     *
     * @throws IOException
     */
    @Test
    public void testGetMove() throws IOException {
        MoveDatabase instance = MoveDatabase.instance();

        Move tackle = instance.get("tackle");
        Assert.assertEquals(35, tackle.getPower());
        Assert.assertEquals(Type.NORMAL, tackle.getType());
        Assert.assertEquals(242, tackle.getAccuracy());
        Assert.assertEquals(Move.Effect.NONE, tackle.getEffect());
        Assert.assertEquals(0, tackle.getEffectProbability());

        Move struggle = instance.get("struggle");
        Assert.assertEquals(50, struggle.getPower());
        Assert.assertEquals(Type.NORMAL, struggle.getType());
        Assert.assertEquals(255, struggle.getAccuracy());
        Assert.assertEquals(2, struggle.getEffectStrength());
        Assert.assertEquals(Move.Effect.RECOIL, struggle.getEffect());
    }

}
