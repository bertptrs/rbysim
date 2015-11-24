package nl.solutionweb.rbysim.types;

/**
 * Type effectiveness enum.
 *
 * @author Bert Peters
 */
public enum TypeEffectiveness {
    NOT_VERY(0.5f, "not very effective"), DOUBLE_NOT_VERY(0.25f, "not very effective"), IMMUNE(0, "no effect"), SUPER(2, "super effective"), DOUBLE_SUPER(4, "super effective"), NORMAL(1, "normally effective");
    private final float modifier;
    private final String description;

    private TypeEffectiveness(float modifier, String description) {
        this.modifier = modifier;
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

    public float getModifier() {
        return modifier;
    }

    public String getDescription() {
        return description;
    }

}
