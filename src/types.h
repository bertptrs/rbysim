#ifndef TYPES_H
#define TYPES_H

#include <set>

using namespace std;

class Types {
    private:
        static const float NORMAL_EFFECT;
        static const float NO_EFFECT;
        static const float SUPER_EFFECT;
        static const float LITTLE_EFFECT;
    public:
        enum Type {
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

        static float getEffectiveness(const Type& attack, const set<Type>& defender);
        static float getEffectiveness(const Type& attack, const Type& defense);
};

#endif
