package nl.solutionweb.rbysim.types;

import java.util.Set;

/**
 *
 * @author Bert Peters
 */
public class TypeEffectivenessHelper {


    /**
     * Get the effectiveness of an attack on a defending type.
     *
     * @param attackType
     * @param defenderType
     * @return An effectiveness representing the type.
     */
    public TypeEffectiveness getEffectiveness(Type attackType, Type defenderType) {
        switch (attackType) {
            case BUG:
                switch (defenderType) {
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
                return defenderType == Type.DRAGON ? TypeEffectiveness.SUPER : TypeEffectiveness.NORMAL;

            case ELECTRIC:
                switch (defenderType) {
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
                switch (defenderType) {
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
                switch (defenderType) {
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
                switch (defenderType) {
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
                switch (defenderType) {
                    case NORMAL:
                    case PSYCHIC:
                        return TypeEffectiveness.IMMUNE;

                    case GHOST:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case GRASS:
                switch (defenderType) {
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
                switch (defenderType) {
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
                switch (defenderType) {
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
                switch (defenderType) {
                    case GHOST:
                        return TypeEffectiveness.IMMUNE;

                    case ROCK:
                        return TypeEffectiveness.NOT_VERY;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case POISON:
                switch (defenderType) {
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
                switch (defenderType) {
                    case PSYCHIC:
                        return TypeEffectiveness.NOT_VERY;

                    case FIGHTING:
                    case POISON:
                        return TypeEffectiveness.SUPER;

                    default:
                        return TypeEffectiveness.NORMAL;
                }

            case ROCK:
                switch (defenderType) {
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
                switch (defenderType) {
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

        throw new IllegalArgumentException("No type matchup known for " + attackType + " on " + defenderType);
    }

    public TypeEffectiveness getEffectiveness(Type attackType, Set<Type> defenderTypes) {
        TypeEffectiveness effectiveness = TypeEffectiveness.NORMAL;
        for (Type type : defenderTypes) {
            effectiveness = effectiveness.combine(getEffectiveness(attackType, type));
        }

        return effectiveness;
    }
}
