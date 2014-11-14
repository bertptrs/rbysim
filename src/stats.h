#ifndef STATS_H
#define STATS_H

#include <initializer_list>
#include "enums.h"

using namespace std;

class Stats {
    private:
        unsigned int stats[STAT_COUNT];
    public:
        static const Stats PERFECT_EVS;
        Stats(const initializer_list<unsigned int>& l);
        virtual unsigned int getStat(const StatType& stat) const;
};

typedef Stats BaseStats;
typedef Stats EVStats;

class IVStats : public Stats {
    public:
        using Stats::Stats;
        
        static const IVStats PERFECT_IVS;
        virtual unsigned int getStat(const StatType& stat) const;
};

#endif
