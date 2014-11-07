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
            bool result;
            MoveEffect effect;
            unsigned int damage;
            union {
                struct {
                    int level;
                    StatType stat;
                } statChange;
                StatusCondition status;
            };
        };

        Move(const Types::Type& type, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);

        virtual Result move(const Pokemon& attacker, const Pokemon& defender) = 0;
        const string& getName() const;
};

class NormalMove : public Move {
    protected:
        unsigned int power;
        MoveEffect moveEffect;
    public:
        NormalMove(const Types::Type& type, unsigned int power, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        virtual Result move(const Pokemon& attacker, const Pokemon& defender) = 0;
};

#endif
