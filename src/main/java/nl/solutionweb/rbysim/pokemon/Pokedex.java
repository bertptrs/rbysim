package nl.solutionweb.rbysim.pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import nl.solutionweb.rbysim.stats.BaseStats;
import nl.solutionweb.rbysim.types.Type;
import nl.solutionweb.rbysim.types.TypeSet;
import nl.solutionweb.rbysim.types.TypeXMLAdapter;

/**
 *
 * @author Bert Peters <bert.ljpeters@gmail.com>
 */
@XmlRootElement(name = "pokedex")
public class Pokedex {

    @XmlElement(name = "pokemon")
    private List<XMLPokemon> pokemon;

    public static Pokedex instance() throws IOException {
        try(InputStream datasource = Pokedex.class.getResourceAsStream("pokedex.xml")) {
            JAXBContext jaxbc = JAXBContext.newInstance(Pokedex.class);
            Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
            return (Pokedex) unmarshaller.unmarshal(datasource);
        } catch (JAXBException ex) {
            throw new IOException(ex);
        }
    }

    /**
     * Return the basestats for the named pokemon.
     *
     * @param name
     * @return The basestats.
     * @throws NoSuchPokemonException if there was no such pokemon in the
     * database.
     */
    public BaseStats getBaseStats(String name) {
        XMLPokemon p = findPokemonByName(name);
        return new BaseStats(p.hp, p.attack, p.defense, p.speed, p.special);
    }

    /**
     * Get the types for a named pokemon.
     *
     * @param name
     * @return
     * @throws NoSuchPokemonException
     */
    public Set<Type> getTypes(String name) {
        XMLPokemon p = findPokemonByName(name);

        if (p.type2 != null) {
            return new TypeSet(p.type1, p.type2);
        } else {
            return new TypeSet(p.type1);
        }
    }

    private XMLPokemon findPokemonByName(String name) {
        for (XMLPokemon p : pokemon) {
            if (p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }

        throw new NoSuchPokemonException(name);
    }

    private static class XMLPokemon implements Comparable<XMLPokemon> {

        @XmlAttribute(required = true)
        String name;
        @XmlAttribute(required = true)
        int hp;
        @XmlAttribute(required = true)
        int attack;
        @XmlAttribute(required = true)
        int defense;
        @XmlAttribute(required = true)
        int speed;
        @XmlAttribute(required = true)
        int special;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(TypeXMLAdapter.class)
        Type type1;
        @XmlAttribute
        @XmlJavaTypeAdapter(TypeXMLAdapter.class)
        Type type2;

        @Override
        public int compareTo(XMLPokemon o) {
            Objects.requireNonNull(o);

            return name.compareToIgnoreCase(o.name);
        }
    }
}
