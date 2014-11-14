#ifndef POKEMON_H
#define POKEMON_H

#include <map>
#include <set>
#include <memory>
#include "stats.h"
#include "types.h"

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
        map<shared_ptr<Move>, int> pp;
        int hp;

        bool hasStatus() const;
};

class Pokemon {
    friend class Battle;
    private:
        EVStats evs;
        BaseStats baseStats;
        IVStats ivs;
        unsigned int level;
        PokemonState state;
        set<Types::Type> type;
        set<shared_ptr<Move>> moves;
        string name;
        
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

        void addType(const Types::Type& type);
        string getName() const;
        void setName(const string& name);

        int getHP() const;
};

#endif
