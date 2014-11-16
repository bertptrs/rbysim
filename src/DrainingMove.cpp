#include "movetypes.h"
#include <algorithm>

DrainingMove::DrainingMove(const Type& type, unsigned int power, unsigned char accuracy, const string& name) :
    NormalMove(type, power, accuracy, name)
{
}

Move::Result DrainingMove::move(const Pokemon& attacker, const Pokemon& defender) const {
    Result result = NormalMove::move(attacker, defender);
    if (result.effect != MoveEffect::MISS) {
        result.effect = MoveEffect::DRAIN;
        result.drain = max(result.damage / 2, 1U);
    }

    return result;
}
