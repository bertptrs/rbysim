package nl.solutionweb.rbysim.moves;

/**
 *
 * @author Bert Peters
 */
public abstract class Move {

    public enum Effect {
        MISS,
        NONE,
        STATUS,
        STATCHANGE,
        RECOIL,
        RECHARGE,
        DRAIN,
        CONFUSE,
        FLINCH,
        SELFDESTRUCT,
        OHKO,
        CONFUSE_SELF,
    };
}
