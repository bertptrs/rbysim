package nl.solutionweb.rbysim.moves;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import nl.solutionweb.rbysim.util.MathUtil;

/**
 * Representation of a move set for a pokemon, including PP.
 *
 * @author Bert Peters
 */
public class MoveSet implements Cloneable {

    /**
     * Maximum num
     */
    public static final int MAX_MOVES = 4;

    private final Map<Move, AtomicInteger> movePPs;

    /**
     * Create a move set without any moves.
     */
    public MoveSet() {
        movePPs = new HashMap<>();
    }

    /**
     * Create a move set with the moves specified.
     *
     * After creation, each move has 0 PP.
     *
     * @param moves A collection of moves to learn.
     *
     * @throws IllegalArgumentException if there are more than 4 moves in the
     * move set.
     */
    public MoveSet(Collection<? extends Move> moves) {
        if (moves.size() > MAX_MOVES) {
            throw new IllegalArgumentException("Cannot learn more than " + MAX_MOVES + " moves");
        }

        movePPs = new HashMap<>();
        for (Move move : moves) {
            movePPs.put(move, new AtomicInteger());
        }
    }

    /**
     * Add a move to the move set.
     *
     * @param move The move to add.
     * @throws IllegalStateException if the move set already contains the
     * maximum number of moves.
     */
    public void addMove(Move move) {
        if (movePPs.size() >= MAX_MOVES) {
            throw new IllegalStateException("Already knows " + MAX_MOVES + " moves.");
        }

        movePPs.put(move, new AtomicInteger());
    }

    /**
     * Use a move's PP.
     *
     * Decreases the move's pp by one. If the move has less than 1 pp left, set the pp to zero.
     *
     * @param move the move to use.
     * @throws IndexOutOfBoundsException if the move is not in the move set.
     * @throws IllegalArgumentException if the move has no pp left.
     */
    public void use(Move move) {
        AtomicInteger pp = getMovePP(move);

        int remaining = pp.decrementAndGet();
        if (remaining < 0) {
            pp.set(0);
            throw new IllegalArgumentException(move.getName() + " has no PP left.");
        }
    }

    /**
     * Get the remaining PP for a move.
     *
     * @param move the move to get the PP for.
     * @return The remaining PP.
     * @throws IndexOutOfBoundsException if the move cannot be found.
     */
    public int getPP(Move move) {
        AtomicInteger pp = getMovePP(move);

        return pp.intValue();
    }

    /**
     * Utility method to get the PP for a move, or throw an exception if it doesn't exist.
     *
     * @param move
     * @return the pp for a move.
     * @throws IndexOutOfBoundsException if there is no such move in the move set.
     */
    private AtomicInteger getMovePP(Move move) {
        AtomicInteger pp = movePPs.get(move);
        if (pp == null) {
            throw new IndexOutOfBoundsException("No such move: " + move.getName());
        }

        return pp;
    }

    /**
     * Get all moves in this move set.
     *
     * @return all moves.
     */
    public Set<Move> getMoves() {
        return movePPs.keySet();
    }

    /**
     * Get the remaining moves in this move set.
     *
     * @return All moves which have at least 1 PP left.
     */
    public Set<Move> getRemainingMoves() {
        return new HashSet(Collections2.filter(getMoves(), new Predicate<Move>() {
            @Override
            public boolean apply(Move input) {
                return getPP(input) > 0;
            }
        }));
    }

    /**
     * Restore PP for all moves to the regular maximum.
     */
    public void restorePP() {
        for (Map.Entry<Move, AtomicInteger> entry : movePPs.entrySet()) {
            entry.getValue().set(entry.getKey().getPp());
        }
    }

    /**
     * Restore PP for all moves to the maxed out maximum.
     */
    public void restoreMaxPp() {
        for (Map.Entry<Move, AtomicInteger> entry : movePPs.entrySet()) {
            entry.getValue().set(MathUtil.fracMultiply(entry.getKey().getPp(), 8, 5));
        }
    }

}
