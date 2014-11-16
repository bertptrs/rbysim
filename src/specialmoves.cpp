#include "movetypes.h"
#include "enums.h"
#include "pokemon.h"

const unsigned int SwiftMove::SWIFT_POWER = 60;

SwiftMove::SwiftMove() :
    NormalMove(Type::TYPE_NORMAL, SWIFT_POWER, 0, "Swift")
{
}

Move::Result SwiftMove::move(const Pokemon& attacker, const Pokemon& defender) const {
    Result result;
    calculateDamage(attacker, defender, result);
    result.effect = MoveEffect::NONE;

    return result;
}
