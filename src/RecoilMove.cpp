#include "move.h"
#include "pokemon.h"

RecoilMove::RecoilMove(const Type& type, unsigned int power, const unsigned int recoilFraction, unsigned char accuracy, const string& name) :
    NormalMove(type, power, false, accuracy, name),
    fraction(recoilFraction)
{
}

Move::Result RecoilMove::move(const Pokemon& attacker, const Pokemon& defender) const {
    Result result;
    if (!hit(attacker, defender)) {
        result.effect = MoveEffect::MISS;
        return result;
    }
    // Calculate damage
    calculateDamage(attacker, defender, result);

    // Recoil based on actual damage done.
    if (((int) result.damage) > defender.getHP()) {
        result.damage = defender.getHP();
    }

    result.effect = MoveEffect::RECOIL;
    if (result.damage != 0) {
        result.recoil = max(result.damage / fraction, 1U);
    } else {
        result.effect = MoveEffect::MISS;
    }
    return result;

}
