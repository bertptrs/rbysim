#ifndef TYPES_H
#define TYPES_H

#include <set>
 
using namespace std;

enum class Type;

class Types {
    private:
        static const float NORMAL_EFFECT;
        static const float NO_EFFECT;
        static const float SUPER_EFFECT;
        static const float LITTLE_EFFECT;
    public:

        static float getEffectiveness(const Type& attack, const set<Type>& defender);
        static float getEffectiveness(const Type& attack, const Type& defense);
};

#endif
