package nl.solutionweb.rbysim.pokemon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nl.solutionweb.rbysim.stats.StatType;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Bert Peters
 */
@RunWith(Parameterized.class)
public class VolatileStatusTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> items = new ArrayList<>();
        for (StatType stat : StatType.values()) {
            items.add(new Object[] {stat});
        }

        return items;
    }

    private final StatType stat;

    public VolatileStatusTest(StatType stat) {
        this.stat = stat;
    }

    /**
     * Assert that updating the modified status works as expected.
     */
    @Test
    public void testBoundsCheck() {
        VolatileStatus status = new VolatileStatus();
        // Check that  we can increase a stat normally.
        assertTrue(status.modifyStatBoostLevel(stat, 3));
        assertEquals(1, status.getLastChange(stat));
        assertEquals(3, status.getStatBoostLevel(stat));

        // Check if we can decrease a stat normally.
        assertTrue(status.modifyStatBoostLevel(stat, -2 * 10));
        assertEquals(-1, status.getLastChange(stat));
        assertEquals(-6, status.getStatBoostLevel(stat));

        // Check our bounds clamping.
        assertFalse(status.modifyStatBoostLevel(stat, -1));
        assertEquals(-1, status.getLastChange(stat));
        assertEquals(-6, status.getStatBoostLevel(stat));
    }

    @Test
    public void testCorrectlyInitialized() {
        VolatileStatus status = new VolatileStatus();

        assertEquals("Initial boost should be zero", 0, status.getStatBoostLevel(stat));
        assertEquals("Last boost direction should be zero.", 0, status.getLastChange(stat));
        assertFalse(status.isConfused());
        assertFalse(status.isRecharging());
        assertEquals(0, status.getSleepingTurns());
        assertEquals(0, status.getTrappedTurns());
    }

}
