#include "movetypes.h"

Move::Result OHKOMove::move(const Pokemon& attacker, const Pokemon& defender) const {
    Result result;
    if (!hit(attacker, defender)) {
        result.effect = MoveEffect::MISS;
    } else {
        result.effect = MoveEffect::OHKO;
    }

    return result;
}
