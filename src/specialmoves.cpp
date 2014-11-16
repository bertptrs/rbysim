#include "movetypes.h"
#include "enums.h"
#include "pokemon.h"

const unsigned int SwiftMove::SWIFT_POWER = 60;
const unsigned int HyperBeamMove::HYPERBEAM_POWER = 150;

HyperBeamMove::HyperBeamMove() :
    NormalMove(Type::TYPE_NORMAL, HYPERBEAM_POWER, MAX_ACCURACY, "Hyperbeam")
{
}

Move::Result HyperBeamMove::move(const Pokemon& attacker, const Pokemon& defender) const {
    Result result = NormalMove::move(attacker, defender);

    if (result.effect != MoveEffect::MISS) {
        result.effect = MoveEffect::RECHARGE;
    }

    return result;
}

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
