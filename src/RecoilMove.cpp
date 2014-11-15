#include "movetypes.h"
#include "move.h"
#include "pokemon.h"

RecoilMove::RecoilMove(const Type& type, unsigned int power, const unsigned int recoilFraction, unsigned char accuracy, const string& name) :
    NormalMove(type, power, false, accuracy, name),
    fraction(recoilFraction)
{
}

Move::Result RecoilMove::move(const Pokemon& attacker, const Pokemon& defender) const {
    Result result = NormalMove::move(attacker, defender);

    if (result.effect != MoveEffect::MISS) {
        result.effect = MoveEffect::RECOIL;
        result.recoil = max(result.damage / fraction, 1U);
    }
    
    return result;

}
