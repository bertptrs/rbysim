#ifndef POKEMON_H
#define POKEMON_H

#include "stats.h"
#include "types.h"
#include <map>

using namespace std;

enum StatusCondition {
    STATUS_NORMAL,
    STATUS_POISONED,
    STATUS_BADLY_POISONED,
    STATUS_ASLEEP,
    STATUS_FROZEN,
    STATUS_BURNED,
    STATUS_PARALYZED
};

class PokemonState {
    public:
        PokemonState();
        StatusCondition status;
        unsigned int statusCounter;
        map<StatType, int> buffs;
        bool confused;
        bool recharging;
        bool flinched;
        bool digging;
        bool flying;

        bool hasStatus() const;
};

class Pokemon {
    private:
        EVStats evs;
        BaseStats baseStats;
        IVStats ivs;
        unsigned int level;
        PokemonState state;
        
        int getBuffLevel(const StatType& stat) const;
        float getBuffFactor(const StatType& stat) const;

    public:
        Pokemon(const EVStats& evs, const BaseStats& baseStats, const IVStats& ivs = IVStats::PERFECT, unsigned int level = 100);
        unsigned int getComputedStat(const StatType& stat) const;
        float getBuffedStat(const StatType& stat) const;
};

#endif
