#ifndef ENUMS_H
#define ENUMS_H

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

enum class StatusCondition {
    STATUS_NORMAL,
    STATUS_POISONED,
    STATUS_BADLY_POISONED,
    STATUS_ASLEEP,
    STATUS_FROZEN,
    STATUS_BURNED,
    STATUS_PARALYZED
};

#endif
