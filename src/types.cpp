#include "types.h"
#include <limits>

const float Types::NO_EFFECT = 0;
const float Types::NORMAL_EFFECT = 1;
const float Types::SUPER_EFECT = 2;
const float Types::LITTLE_EFFECT = 0.5f;

float Types::getEffectiveness(const Type& attack, const set<Type>& defender)
{
    float effectiveness = 1;
    for (auto type : defender) {
        effectiveness *= getEffectiveness(attack, type);
    }

    return effectiveness;
}

float Types::getEffectiveness(const Type& attack, const Type& defense)
{
    switch (attack)
    {
        case TYPE_NORMAL:
            switch (defense) {
                case TYPE_ROCK:
                    return LITTLE_EFFECT;
                case TYPE_GHOST:
                    return NO_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_FIGHTING:
            switch (defense) {
                case TYPE_NORMAL:
                case TYPE_ROCK:
                    return SUPER_EFECT;
                case TYPE_GHOST:
                    return NO_EFFECT;
                case TYPE_FLYING:
                case TYPE_POISON:
                case TYPE_BUG:
                case TYPE_PSYCHIC:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_FLYING:
            switch (defense) {
                case TYPE_FIGHTING:
                case TYPE_BUG:
                case TYPE_GRASS:
                    return SUPER_EFECT;
                case TYPE_ROCK:
                case TYPE_ELECTRIC:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_POISON:
            switch (defense) {
                case TYPE_GROUND:
                case TYPE_ROCK:
                case TYPE_GHOST:
                    return LITTLE_EFFECT;
                case TYPE_GRASS:
                case TYPE_BUG:
                    return SUPER_EFECT;
                default:
                    return LITTLE_EFFECT;
            }
        case TYPE_GROUND:
            switch (defense) {
                case TYPE_FLYING:
                    return NO_EFFECT;
                case TYPE_POISON:
                case TYPE_ROCK:
                case TYPE_ELECTRIC:
                case TYPE_FIRE:
                    return SUPER_EFECT;
                case TYPE_BUG:
                case TYPE_GRASS:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_ROCK:
            switch (defense) {
                case TYPE_FIGHTING:
                case TYPE_GROUND:
                    return LITTLE_EFFECT;
                case TYPE_FLYING:
                case TYPE_BUG:
                case TYPE_FIRE:
                case TYPE_ICE:
                    return SUPER_EFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_BUG:
            switch (defense) {
                case TYPE_FIGHTING:
                case TYPE_FLYING:
                case TYPE_GHOST:
                case TYPE_FIRE:
                    return LITTLE_EFFECT;
                case TYPE_POISON:
                case TYPE_GRASS:
                case TYPE_PSYCHIC:
                    return SUPER_EFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_GHOST:
            switch (defense) {
                case TYPE_NORMAL:
                case TYPE_PSYCHIC:
                    return NO_EFFECT;
                case TYPE_GHOST:
                    return SUPER_EFECT;
                default:
                    return NO_EFFECT;
            }
        case TYPE_FIRE:
            switch (defense) {
                case TYPE_ROCK:
                case TYPE_FIRE:
                case TYPE_WATER:
                case TYPE_DRAGON:
                    return LITTLE_EFFECT;
                case TYPE_BUG:
                case TYPE_GRASS:
                case TYPE_ICE:
                    return SUPER_EFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_WATER:
            switch (defense) {
                case TYPE_GROUND:
                case TYPE_ROCK:
                case TYPE_FIRE:
                    return SUPER_EFECT;
                case TYPE_WATER:
                case TYPE_GRASS:
                case TYPE_DRAGON:
                    return LITTLE_EFFECT;
                default:
                    return SUPER_EFECT;
            }
        case TYPE_GRASS:
            switch (defense) {
                case TYPE_FLYING:
                case TYPE_POISON:
                case TYPE_BUG:
                case TYPE_FIRE:
                case TYPE_GRASS:
                case TYPE_DRAGON:
                    return LITTLE_EFFECT;
                case TYPE_GROUND:
                case TYPE_ROCK:
                case TYPE_WATER:
                    return SUPER_EFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_ELECTRIC:
            switch (defense) {
                case TYPE_FLYING:
                case TYPE_WATER:
                    return SUPER_EFECT;
                case TYPE_GROUND:
                    return NO_EFFECT;
                case TYPE_GRASS:
                case TYPE_ELECTRIC:
                case TYPE_DRAGON:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_PSYCHIC:
            switch (defense) {
                case TYPE_FIGHTING:
                case TYPE_POISON:
                    return SUPER_EFECT;
                case TYPE_PSYCHIC:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_ICE:
            switch (defense) {
                case TYPE_FLYING:
                case TYPE_GROUND:
                case TYPE_GRASS:
                case TYPE_DRAGON:
                    return SUPER_EFECT;
                case TYPE_FIRE:
                case TYPE_ICE:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case TYPE_DRAGON:
            switch (defense) {
                case TYPE_DRAGON:
                    return SUPER_EFECT;
                default:
                    return NORMAL_EFFECT;
            }
    }

    return numeric_limits<float>::quiet_NaN();
}
