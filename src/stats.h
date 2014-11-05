#ifndef STATS_H
#define STATS_H

#include <initializer_list>

using namespace std;

// Probably wasteful to store them as 4 ints when they fit in one.
// Then again, this is a simulator running on a way more powerful machine.
class IVStats {
    public:
        IVStats(const initializer_list<int>& l);
        unsigned int attack;
        unsigned int defense;
        unsigned int speed;
        unsigned int special;

        // HP is an aggregate of the others.
        unsigned int getHP() const;
};

class BaseStats {
    public:
        BaseStats(const initializer_list<int>& l);
        unsigned int attack;
        unsigned int defense;
        unsigned int speed;
        unsigned int special;
        unsigned int hp;
};

class EVStats : public BaseStats {
    using BaseStats::BaseStats;
};

#endif
