package nl.solutionweb.rbysim.util;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Utility class to create unmarshallers for various enums.
 *
 * @author Bert Peters
 * @param <E> The enum type for which this is a marshaller.
 */
public class XMLEnumAdapter<E extends Enum> extends XmlAdapter<String, E> {

    private final Map<String, E> mapping;

    public XMLEnumAdapter(E[] values) {
        mapping = new HashMap<>();
        for (E value : values) {
            mapping.put(value.toString(), value);
        }
    }

    @Override
    public E unmarshal(String v) throws Exception {
        if (mapping.containsKey(v)) {
            return mapping.get(v);
        }
        throw new UnsupportedOperationException("Cannot unmarshal " + v);
    }

    @Override
    public String marshal(E v) throws Exception {
        return v.toString();
    }
}
