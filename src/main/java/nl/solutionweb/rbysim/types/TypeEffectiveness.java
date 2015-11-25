package nl.solutionweb.rbysim.types;

/**
 * Type effectiveness enum.
 *
 * @author Bert Peters
 */
public enum TypeEffectiveness {
    NOT_VERY("not very effective"), DOUBLE_NOT_VERY("not very effective"), IMMUNE("no effect"), SUPER("super effective"), DOUBLE_SUPER("super effective"), NORMAL("normally effective");
    private final String description;

    private TypeEffectiveness(String description) {
        this.description = description;
    }

    public TypeEffectiveness combine(TypeEffectiveness other) {
        if (this == IMMUNE || other == IMMUNE) {
            return IMMUNE;
        }
        switch (this) {
            case NORMAL:
                return other;
            case SUPER:
                switch (other) {
                    case SUPER:
                        return DOUBLE_SUPER;
                    case NORMAL:
                        return SUPER;
                    case NOT_VERY:
                        return NORMAL;
                }
                break;
            case NOT_VERY:
                switch (other) {
                    case SUPER:
                        return NORMAL;
                    case NORMAL:
                        return NOT_VERY;
                    case NOT_VERY:
                        return DOUBLE_NOT_VERY;
                }
                break;
        }
        throw new IllegalArgumentException("Effectiveness " + this + " cannot stack with " + other);
    }

    public String getDescription() {
        return description;
    }

    public int modifyDamage(int original) {
        switch (this) {
            case DOUBLE_NOT_VERY:
                return original / 4;

            case DOUBLE_SUPER:
                return original * 4;

            case IMMUNE:
                return 0;

            case NORMAL:
                return original;

            case NOT_VERY:
                return original / 2;

            case SUPER:
                return original * 2;
        }

        throw new UnsupportedOperationException("Cannot modify damage for " + this);
    }
}
