package nl.solutionweb.rbysim.types;

/**
 *
 * @author Bert Peters
 */
public class TypeEffectivenessHelper {


    public TypeEffectiveness getEffectiveness(Type attack, Type defender) {
        switch (attack) {
            case BUG:
                switch (defender) {
                    case FIGHTING:
                    case FIRE:
                    case FLYING:
                    case GHOST:
                        return TypeEffectiveness.NOT_VERY;

                    case GRASS:
                    case POISON:
                    case PSYCHIC:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case DRAGON:
                return defender == Type.DRAGON ? TypeEffectiveness.SUPER : TypeEffectiveness.NORMAL;

            case ELECTRIC:
                switch (defender) {
                    case GROUND:
                        return TypeEffectiveness.IMMUNE;

                    case ELECTRIC:
                    case GRASS:
                        return TypeEffectiveness.NOT_VERY;

                    case FLYING:
                    case WATER:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case FIGHTING:
                switch (defender) {
                    case GHOST:
                        return TypeEffectiveness.IMMUNE;

                    case BUG:
                    case FLYING:
                    case POISON:
                    case PSYCHIC:
                        return TypeEffectiveness.NOT_VERY;

                    case ICE:
                    case NORMAL:
                    case ROCK:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case FIRE:
                switch (defender) {
                    case DRAGON:
                    case FIRE:
                    case ROCK:
                    case WATER:
                        return TypeEffectiveness.NOT_VERY;

                    case BUG:
                    case GRASS:
                    case ICE:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case FLYING:
                switch (defender) {
                    case ELECTRIC:
                    case ROCK:
                        return TypeEffectiveness.NOT_VERY;

                    case BUG:
                    case GRASS:
                    case FIGHTING:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case GHOST:
                switch (defender) {
                    case NORMAL:
                    case PSYCHIC:
                        return TypeEffectiveness.IMMUNE;

                    case GHOST:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case GRASS:
                switch (defender) {
                    case BUG:
                    case DRAGON:
                    case FIRE:
                    case FLYING:
                    case GRASS:
                    case POISON:
                        return TypeEffectiveness.NOT_VERY;

                    case GROUND:
                    case ROCK:
                    case WATER:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case GROUND:
                switch (defender) {
                    case FLYING:
                        return TypeEffectiveness.IMMUNE;

                    case BUG:
                    case GRASS:
                        return TypeEffectiveness.NOT_VERY;

                    case ELECTRIC:
                    case FIRE:
                    case POISON:
                    case ROCK:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case ICE:
                switch (defender) {
                    case FIRE:
                    case ICE:
                    case WATER:
                        return TypeEffectiveness.NOT_VERY;

                    case DRAGON:
                    case FLYING:
                    case GROUND:
                    case GRASS:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case NORMAL:
                switch (defender) {
                    case GHOST:
                        return TypeEffectiveness.IMMUNE;

                    case ROCK:
                        return TypeEffectiveness.NOT_VERY;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case POISON:
                switch (defender) {
                    case GROUND:
                    case POISON:
                    case ROCK:
                        return TypeEffectiveness.NOT_VERY;

                    case BUG:
                    case GRASS:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case PSYCHIC:
                switch (defender) {
                    case PSYCHIC:
                        return TypeEffectiveness.NOT_VERY;

                    case FIGHTING:
                    case POISON:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case ROCK:
                switch (defender) {
                    case FIGHTING:
                    case ROCK:
                        return TypeEffectiveness.NOT_VERY;

                    case BUG:
                    case FIRE:
                    case FLYING:
                    case ICE:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case WATER:
                switch (defender) {
                    case DRAGON:
                    case GRASS:
                    case WATER:
                        return TypeEffectiveness.NOT_VERY;

                    case FIRE:
                    case GROUND:
                    case ROCK:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }
        }

        throw new IllegalArgumentException("No type matchup known for " + attack + " on " + defender);
    }
}
