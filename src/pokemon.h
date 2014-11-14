#ifndef POKEMON_H
#define POKEMON_H

#include "stats.h"
#include "types.h"
#include <map>
#include <set>
#include <memory>
#include "move.h"

using namespace std;

class Move;

class PokemonState {
    public:
        PokemonState();
        StatusCondition status;
        unsigned int poisonCounter;
        unsigned int sleepCounter;
        map<StatType, int> buffs;
        bool confused;
        bool recharging;
        bool charging;
        shared_ptr<Move> chargingMove;
        bool invincible;
        bool flinched;
        bool reflect;
        bool lightScreen;

        bool hasStatus() const;
};

class Pokemon {
    private:
        EVStats evs;
        BaseStats baseStats;
        IVStats ivs;
        unsigned int level;
        PokemonState state;
        set<Types::Type> type;
        
        int getBuffLevel(const StatType& stat) const;
        float getBuffFactor(const StatType& stat) const;

    public:
        Pokemon(const BaseStats& baseStats, const EVStats& evs = Stats::PERFECT_EVS, const IVStats& ivs = IVStats::PERFECT_IVS, unsigned int level = 100);
        unsigned int getComputedStat(const StatType& stat) const;
        float getBuffedStat(const StatType& stat) const;
        unsigned int getBaseStat(const StatType& stat) const;
        bool hasType(Types::Type type) const;
        const set<Types::Type>& getTypes() const;
        unsigned int getLevel() const;
        bool hasBarrier(bool physical) const;
};

#endif
