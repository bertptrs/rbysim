#include "move.h"
#include <cstdlib>

const string Move::DEFAULT_NAME = "CUSTOM";
const unsigned char Move::MAX_ACCURACY = 0xff;

Move::Move(const Types::Type& type, unsigned char accuracy, const string& name) :
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
