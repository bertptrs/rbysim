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

enum class MoveEffect {
    MISS,
    NONE,
    STATUS,
    STATCHANGE,
    RECOIL,
    RECHARGE
};

enum class Type {
    TYPE_NORMAL,
    TYPE_FIGHTING,
    TYPE_FLYING,
    TYPE_POISON,
    TYPE_GROUND,
    TYPE_ROCK,
    TYPE_BUG,
    TYPE_GHOST,
    TYPE_FIRE,
    TYPE_WATER,
    TYPE_GRASS,
    TYPE_ELECTRIC,
    TYPE_PSYCHIC,
    TYPE_ICE,
    TYPE_DRAGON
};

#endif
