#include "stats.h"
#include <iostream>

const Stats Stats::PERFECT_EVS = {0xffff, 0xffff, 0xffff, 0xffff, 0xffff};

Stats::Stats(const initializer_list<unsigned int>& l)
{
    int i = 0;
    for (auto it = l.begin();
            i < STAT_COUNT && it != l.end();
            ++i, ++it) {
        stats[i] = *it;
    }
}

unsigned int Stats::getStat(const StatType& stat) const {
    return stats[stat];
}

const IVStats IVStats::PERFECT_IVS = {15, 15, 15, 15};

unsigned int IVStats::getStat(const StatType& stat) const {
    if (stat == STAT_HP) {
        // Calculate the aggregate.
        return ((Stats::getStat(STAT_ATTACK) & 1) << 3)
            | ((Stats::getStat(STAT_DEFENSE) & 1) << 2)
            | ((Stats::getStat(STAT_SPEED) & 1) << 1)
            | ((Stats::getStat(STAT_SPECIAL) & 1));
    } else {
        return Stats::getStat(stat);
    }
}

string Stats::getName(const StatType& stat) {
    switch (stat) {
        case STAT_ATTACK:
            return "Attack";
        case STAT_DEFENSE:
            return "Defense";
        case STAT_SPEED:
            return "Speed";
        case STAT_SPECIAL:
            return "Special";
        case STAT_HP:
            return "Hitpoints";
        case STAT_ACCURACY:
            return "Accuracy";
        case STAT_EVASION:
            return "Evasion";
        default:
            return "Here be dragons";
    }
}
