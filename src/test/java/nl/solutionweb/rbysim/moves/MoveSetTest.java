/*
 */
package nl.solutionweb.rbysim.moves;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Bert Peters
 */
public class MoveSetTest {

    @Test
    public void testPPFlow() {
        Move move = Mockito.mock(Move.class);
        Mockito.when(move.getPp()).thenReturn(5);

        MoveSet instance = new MoveSet();
        instance.addMove(move);

        Assert.assertEquals(0, instance.getPP(move));

        instance.restorePP();
        Assert.assertEquals(5, instance.getPP(move));

        instance.use(move);
        Assert.assertEquals(4, instance.getPP(move));

        instance.restoreMaxPp();
        junit.framework.Assert.assertEquals(8, instance.getPP(move));

        Mockito.verify(move, Mockito.atLeastOnce()).getPp();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidMove()
    {
        Move move = Mockito.mock(Move.class);
        MoveSet instance = new MoveSet();

        instance.getPP(move);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoPPLeft()
    {
        Move move = Mockito.mock(Move.class);
        MoveSet instance = new MoveSet();
        instance.addMove(move);

        instance.use(move);
    }

}
