#ifndef NORMALMOVE_H
#define NORMALMOVE_H

#include "move.h"

using namespace std;

class NormalMove : public Move {
    protected:
        unsigned int power;
        unsigned int probability;
        MoveEffect moveEffect;
        union {
            struct {
                StatType stat;
                int change;
            } stat;
            StatusCondition condition;
        };
        bool highCrit;
        bool selfDestruct;

        bool hasEffect() const;
        bool isCrit(const Pokemon& attacker) const;
        unsigned int calculateDamage(const Pokemon& attacker, const Pokemon& defender, Result& moveResult) const;

    public:
        NormalMove(const Type& type, unsigned int power, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        NormalMove(const Type& type, unsigned int power, const StatusCondition& condition, unsigned int probability, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        NormalMove(const Type& type, unsigned int power, const StatType& stat, int level, unsigned int probability, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        NormalMove(const Type& type, unsigned int power, MoveEffect effect, unsigned int probability = 0, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);

        NormalMove& withHighCrit();

        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

#endif
