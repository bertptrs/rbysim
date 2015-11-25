package nl.solutionweb.rbysim.util;

/**
 * Utility class for computing in a gameboy style
 *
 * @author Bert Peters
 */
public class MathUtil {

    /**
     * Multiply an integer by a rational number.
     *
     * @param operand
     * @param numerator
     * @param denominator
     * @return
     */
    public static int fracMultiply(int operand, int numerator, int denominator) {
        return (operand * numerator) / denominator;
    }

    /**
     * Clamp a value between two bounds.
     *
     * @param value The value to be clamped.
     * @param lowerBound The lower bound, inclusive.
     * @param upperBound The upper bound, inclusive.
     * @return The clamped value.
     */
    public static int clamp(int value, int lowerBound, int upperBound) {
        return Math.max(lowerBound, Math.min(upperBound, value));
    }
}
