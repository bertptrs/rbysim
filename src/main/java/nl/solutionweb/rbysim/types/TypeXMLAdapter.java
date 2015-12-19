package nl.solutionweb.rbysim.types;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Simple XML type adapter to use the Type type with.
 *
 * @author Bert Peters <bert.ljpeters@gmail.com>
 */
public class TypeXMLAdapter extends XmlAdapter<String, Type> {

    private final Map<String, Type> typeNameMapping;

    public TypeXMLAdapter() {
        typeNameMapping = new HashMap<>();
        for (Type type : Type.values()) {
            typeNameMapping.put(type.toString(), type);
        }
    }

    @Override
    public Type unmarshal(String v) throws Exception {
        if (typeNameMapping.containsKey(v)) {
            return typeNameMapping.get(v);
        } else {
            throw new UnsupportedOperationException("Cannot unmarshal " + v);
        }
    }

    @Override
    public String marshal(Type v) throws Exception {
        return v.toString();
    }
}
