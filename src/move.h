#ifndef MOVE_H
#define MOVE_H

#include "stats.h"
#include <memory>

using namespace std;

class Pokemon;
enum class Type;
enum class MoveEffect;

class Move {
    private:
        string name;
    protected:
        Type type;
        unsigned char accuracy;
        bool hit(const Pokemon& attacker, const Pokemon& defender) const;
        bool isSpecial() const;
        StatType getOffensiveStat() const;
        StatType getDefensiveStat() const;
    public:
        static const string DEFAULT_NAME;
        static const unsigned char MAX_ACCURACY;
        static const shared_ptr<Move> STRUGGLE;


        struct Result {
            MoveEffect effect;
            unsigned int damage;
            bool crit;
            union {
                unsigned int recoil;
                unsigned int drain;
                struct {
                    int level;
                    StatType stat;
                } statChange;
                StatusCondition status;
            };
        };

        Move(const Type& type, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);

        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const = 0;
        const string& getName() const;
};

#endif
