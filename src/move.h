#ifndef MOVE_H
#define MOVE_H

#include "pokemon.h"
#include "stats.h"

using namespace std;

class Move {
    private:
        string name;
    protected:
        Types::Type type;
        unsigned char accuracy;
        bool hit(const Pokemon& attacker, const Pokemon& defender) const;
        bool isSpecial() const;
        StatType getOffensiveStat() const;
        StatType getDefensiveStat() const;
    public:
        static const string DEFAULT_NAME;
        static const unsigned char MAX_ACCURACY;

        enum class MoveEffect {
            MISS,
            NONE,
            STATUS,
            STATCHANGE
        };
        struct Result {
            MoveEffect effect;
            unsigned int damage;
            unsigned int recoil;
            bool crit;
            union {
                struct {
                    int level;
                    StatType stat;
                } statChange;
                StatusCondition status;
            };
        };

        Move(const Types::Type& type, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);

        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const = 0;
        const string& getName() const;
};

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
        NormalMove(const Types::Type& type, unsigned int power, bool highCrit = false, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        NormalMove(const Types::Type& type, unsigned int power, const StatusCondition& condition, unsigned int probability, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        NormalMove(const Types::Type& type, unsigned int power, const StatType& stat, int level, unsigned int probability, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);

        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

#endif
