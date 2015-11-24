package nl.solutionweb.rbysim.types;

import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for the TypeSet class.
 *
 * @author Bert Peters
 */
public class TypeSetTest {

    /**
     * Test of iterator method, of class TypeSet.
     */
    @Test
    public void testIterator() {
        TypeSet instance = new TypeSet(Type.NORMAL, Type.GHOST);
        Iterator<Type> result = instance.iterator();
        Assert.assertTrue(result.hasNext());
        Assert.assertEquals(Type.NORMAL, result.next());
        Assert.assertTrue(result.hasNext());
        Assert.assertEquals(Type.GHOST, result.next());
        Assert.assertFalse(result.hasNext());
    }

    /**
     * Test of size method, of class TypeSet.
     */
    @Test
    public void testSize() {
        TypeSet instance = new TypeSet(Type.FIRE);
        Assert.assertEquals(1, instance.size());
        TypeSet instance2 = new TypeSet(Type.NORMAL, Type.DRAGON);
        Assert.assertEquals(2, instance2.size());
    }

}
