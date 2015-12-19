/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.solutionweb.rbysim.types;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Bert Peters <bert.ljpeters@gmail.com>
 */
@RunWith(Parameterized.class)
public class TypeXMLAdapterTest {

    @Parameterized.Parameters(name = "{index}: Type {0}")
    public static Iterable<Type[]> getParameters() {
        List<Type[]> parameters = new ArrayList<>();
        for (Type type : Type.values()) {
            parameters.add(new Type[]{type});
        }

        return parameters;
    }

    @Parameterized.Parameter
    public Type currentType;

    public TypeXMLAdapterTest() {
    }

    /**
     * Test of unmarshal method, of class TypeXMLAdapter.
     * @throws java.lang.Exception
     */
    @Test
    public void testUnmarshal() throws Exception {
        TypeXMLAdapter adapter = new TypeXMLAdapter();
        Assert.assertEquals(currentType, adapter.unmarshal(currentType.toString()));
    }

    /**
     * Test of marshal method, of class TypeXMLAdapter.
     * @throws java.lang.Exception
     */
    @Test
    public void testMarshal() throws Exception {
        TypeXMLAdapter adapter = new TypeXMLAdapter();
        Assert.assertEquals(currentType.toString(), adapter.marshal(currentType));
    }

}
