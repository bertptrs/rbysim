package nl.solutionweb.rbysim.types;

import nl.solutionweb.rbysim.util.XMLEnumAdapter;

/**
 * Simple XML type adapter to use the Type type with.
 *
 * @author Bert Peters <bert.ljpeters@gmail.com>
 */
public class TypeXMLAdapter extends XMLEnumAdapter<Type> {

    public TypeXMLAdapter() {
        super(Type.values());
    }
}
