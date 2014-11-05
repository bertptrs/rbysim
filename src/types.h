#ifndef TYPES_H
#define TYPES_H

#include<utility>

using namespace std;

class Types {
    private:
        static const float NORMAL_EFFECT;
        static const float NO_EFFECT;
        static const float SUPER_EFECT;
        static const float LITTLE_EFFECT;
    public:
        enum Type {
            TYPE_NULL,
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

        static float getEffectiveness(const Type& attack, const pair<Type, Type>& defender);
        static float getEffectiveness(const Type& attack, const Type& defense);
};

#endif
