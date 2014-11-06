#ifndef STATS_H
#define STATS_H

#include <initializer_list>

using namespace std;

enum StatType {
    STAT_ATTACK = 0,
    STAT_DEFENSE,
    STAT_SPEED,
    STAT_SPECIAL,
    STAT_HP,
    STAT_COUNT, // Number of stats actually occuring in stats objects.
    STAT_EVASION,
    STAT_ACCURACY
};

class Stats {
    private:
        unsigned int stats[STAT_COUNT];
    public:
        Stats(const initializer_list<unsigned int>& l);
        virtual unsigned int getStat(const StatType& stat) const;
};

typedef Stats BaseStats;
typedef Stats EVStats;

class IVStats : public Stats {
    public:
        using Stats::Stats;
        
        static const IVStats PERFECT;
        virtual unsigned int getStat(const StatType& stat) const;
};

#endif
