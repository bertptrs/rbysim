/*
 */
package nl.solutionweb.rbysim.stats;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author s1147919
 */
public class IVStatsTest {

    public IVStatsTest() {
    }

    /**
     * Test the HP stat calculation.
     */
    @Test
    public void testGetHPStatValue() {
        IVStats iVStats = new IVStats();
        Assert.assertEquals(IVStats.MAX_VALUE, iVStats.getStatValue(StatType.HP));

        iVStats = new IVStats(4, 13, 6, 1);
        Assert.assertEquals(5, iVStats.getStatValue(StatType.HP));
    }

}
