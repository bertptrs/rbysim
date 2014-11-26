#include "move.h"
#include "pokemon.h"
#include <cstdlib>
#include <algorithm>
#include "enums.h"
#include "movetypes.h"

const string Move::DEFAULT_NAME = "CUSTOM";
const unsigned char Move::MAX_ACCURACY = 0xff;
const shared_ptr<Move> Move::STRUGGLE(new RecoilMove(Type::TYPE_NORMAL, 50, 2, Move::MAX_ACCURACY, "Struggle"));

Move::Move(const Type& type, unsigned char accuracy, const string& name) :
    name(name), type(type), accuracy(accuracy)
{
}

bool Move::hit(const Pokemon& attacker, const Pokemon& defender) const {
    unsigned int hitNum = rand() % 256;
    unsigned int hitProb = accuracy * attacker.getBuffedStat(STAT_ACCURACY);
    hitProb /= defender.getBuffedStat(STAT_EVASION);
    return hitNum < hitProb; // The "100% moves don't always hit"-bug.
}

const string& Move::getName() const {
    return name;
}

bool Move::isSpecial() const {
    switch (type) {
        case Type::TYPE_NORMAL:
        case Type::TYPE_FIGHTING:
        case Type::TYPE_FLYING:
        case Type::TYPE_POISON:
        case Type::TYPE_GROUND:
        case Type::TYPE_ROCK:
        case Type::TYPE_BUG:
        case Type::TYPE_GHOST:
            return false;
        default:
            return true;
    }
}

StatType Move::getOffensiveStat() const {
    return isSpecial() ? STAT_SPECIAL : STAT_ATTACK;
}

StatType Move::getDefensiveStat() const {
    return isSpecial() ? STAT_SPECIAL : STAT_DEFENSE;
}

void Move::setPP(unsigned int n) {
    pp = n;
}

unsigned int Move::getPP() const {
    return pp;
}
