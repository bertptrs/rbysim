package nl.solutionweb.rbysim.pokemon;

/**
 *
 * @author Bert Peters <bert.ljpeters@gmail.com>
 */
public class NoSuchPokemonException extends IllegalArgumentException {

    public NoSuchPokemonException(String name) {
        super("No such pokemon: " + name);
    }
}
