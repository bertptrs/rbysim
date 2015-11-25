package nl.solutionweb.rbysim.moves;

import nl.solutionweb.rbysim.types.Type;

/**
 *
 * @author Bert Peters
 */
public class DamagingMove extends Move {

    private final int power;

    public DamagingMove(Type type, int accuracy, int power, Effect effect) {
        super(type, true, accuracy, effect);
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
