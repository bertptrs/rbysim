package nl.solutionweb.rbysim;

import nl.solutionweb.rbysim.types.Type;
import nl.solutionweb.rbysim.types.TypeEffectivenessHelper;

/**
 * Main simulator class.
 *
 * @author Bert Peters
 */
public class Simulator {

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.printTypeMatchup();
    }

    public void printTypeMatchup() {
        TypeEffectivenessHelper teh = new TypeEffectivenessHelper();

        for (Type attackType : Type.values()) {
            for (Type defenderType : Type.values()) {
                String representation = "  ";
                switch (teh.getEffectiveness(attackType, defenderType)) {
                    case IMMUNE:
                        representation = "0 ";
                        break;

                    case SUPER:
                        representation = "+ ";
                        break;

                    case NOT_VERY:
                        representation = "- ";
                        break;
                }
                System.out.print(representation);
            }
            System.out.println(attackType);
        }
    }
}
