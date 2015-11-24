package nl.solutionweb.rbysim.types;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Set of types that an individual may have.
 *
 * @author Bert Peters
 */
public class TypeSet extends AbstractSet<Type> {

    private final Type primary;
    private final Type secondary;

    public TypeSet(Type type) {
        this(type, null);
    }

    public TypeSet(Type primary, Type secondary) {
        Objects.requireNonNull(primary, "Primary type may not be null");
        if (primary == secondary) {
            throw new IllegalArgumentException("Dual types must be distinct types.");
        }
        this.primary = primary;
        this.secondary = secondary;
    }


    @Override
    public Iterator<Type> iterator() {
        return new TypeSetIterator();
    }

    @Override
    public int size() {
        return secondary == null ? 1 : 2;
    }

    private class TypeSetIterator implements Iterator<Type> {

        int state = 0;

        @Override
        public boolean hasNext() {
            return state < size();
        }

        @Override
        public Type next() {
            if (state >= size()) {
                throw new NoSuchElementException();
            }
            state++;

            return state == 1 ? primary : secondary;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unmodifiable collection");
        }

    }

}
