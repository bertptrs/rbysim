#include "pokemon.h"
#include <cmath>
#include <algorithm>
#include <limits>

PokemonState::PokemonState() :
    status(STATUS_NORMAL),
    statusCounter(0),
    confused(false),
    recharging(false),
    flinched(false),
    digging(false),
    flying(false),
    reflectDuration(0),
    lightScreenDuration(0)
{
}

bool PokemonState::hasStatus() const {
    return status != STATUS_NORMAL;
}

Pokemon::Pokemon(const BaseStats& baseStats, const EVStats& evs, const IVStats& ivs, unsigned int level) :
    evs(evs),
    baseStats(baseStats),
    ivs(ivs),
    level(level)
{
}

unsigned int Pokemon::getComputedStat(const StatType& stat) const {
    unsigned int ev = evs.getStat(stat);
    unsigned int base = baseStats.getStat(stat);
    unsigned int iv = ivs.getStat(stat);
    unsigned int cl = stat == STAT_HP ? level + 10 : 5;

    unsigned int statValue = 1 + (unsigned int) sqrt(max(ev - 1, 0U));
    statValue /= 4;
    statValue = (min(statValue, 63U) + 2 * (iv + base)) * level;
    statValue /= 100;
    statValue += cl;

    return statValue;
}

int Pokemon::getBuffLevel(const StatType& stat) const {
    auto it = state.buffs.find(stat);
    return it == state.buffs.end() ? 0 : it->second;
}

// See 2.5 of RBY book.
float Pokemon::getBuffFactor(const StatType& stat) const {
    int buffLevel = getBuffLevel(stat);
    if (buffLevel > 0) {
        return (2 + buffLevel) / 2.0f;
    } else {
        return 2.0f / (2 - buffLevel);
    }
}

float Pokemon::getBuffedStat(const StatType& stat) const {
    float buff = getBuffFactor(stat);
    if (stat == STAT_EVASION || stat == STAT_ACCURACY) {
        return buff;
    }

    if (stat == STAT_SPEED && state.status == STATUS_PARALYZED) {
        buff /= 4;
    } else if (stat == STAT_ATTACK && state.status == STATUS_BURNED) {
        buff /= 2;
    }

    float finalStat = buff * getComputedStat(stat);
    return floor(max(1.0f, min(999.0f, finalStat)));
}

unsigned int Pokemon::getBaseStat(const StatType& stat) const {
    return baseStats.getStat(stat);
}

const set<Types::Type>& Pokemon::getTypes() const {
    return type;
}

unsigned int Pokemon::getLevel() const {
    return level;
}

bool Pokemon::hasType(Types::Type requested) const  {
    return type.count(requested) > 0;
}

bool Pokemon::hasBarrier(bool physical) const {
    return (physical ? state.reflectDuration : state.lightScreenDuration) > 0;
}
