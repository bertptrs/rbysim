#include "types.h"
#include <limits>
#include "enums.h"

const float Types::NO_EFFECT = 0;
const float Types::NORMAL_EFFECT = 1;
const float Types::SUPER_EFFECT = 2;
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
        case Type::TYPE_NORMAL:
            switch (defense) {
                case Type::TYPE_ROCK:
                    return LITTLE_EFFECT;
                case Type::TYPE_GHOST:
                    return NO_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_FIGHTING:
            switch (defense) {
                case Type::TYPE_NORMAL:
                case Type::TYPE_ROCK:
                    return SUPER_EFFECT;
                case Type::TYPE_GHOST:
                    return NO_EFFECT;
                case Type::TYPE_FLYING:
                case Type::TYPE_POISON:
                case Type::TYPE_BUG:
                case Type::TYPE_PSYCHIC:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_FLYING:
            switch (defense) {
                case Type::TYPE_FIGHTING:
                case Type::TYPE_BUG:
                case Type::TYPE_GRASS:
                    return SUPER_EFFECT;
                case Type::TYPE_ROCK:
                case Type::TYPE_ELECTRIC:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_POISON:
            switch (defense) {
                case Type::TYPE_GROUND:
                case Type::TYPE_ROCK:
                case Type::TYPE_GHOST:
                    return LITTLE_EFFECT;
                case Type::TYPE_GRASS:
                case Type::TYPE_BUG:
                    return SUPER_EFFECT;
                default:
                    return LITTLE_EFFECT;
            }
        case Type::TYPE_GROUND:
            switch (defense) {
                case Type::TYPE_FLYING:
                    return NO_EFFECT;
                case Type::TYPE_POISON:
                case Type::TYPE_ROCK:
                case Type::TYPE_ELECTRIC:
                case Type::TYPE_FIRE:
                    return SUPER_EFFECT;
                case Type::TYPE_BUG:
                case Type::TYPE_GRASS:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_ROCK:
            switch (defense) {
                case Type::TYPE_FIGHTING:
                case Type::TYPE_GROUND:
                    return LITTLE_EFFECT;
                case Type::TYPE_FLYING:
                case Type::TYPE_BUG:
                case Type::TYPE_FIRE:
                case Type::TYPE_ICE:
                    return SUPER_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_BUG:
            switch (defense) {
                case Type::TYPE_FIGHTING:
                case Type::TYPE_FLYING:
                case Type::TYPE_GHOST:
                case Type::TYPE_FIRE:
                    return LITTLE_EFFECT;
                case Type::TYPE_POISON:
                case Type::TYPE_GRASS:
                case Type::TYPE_PSYCHIC:
                    return SUPER_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_GHOST:
            switch (defense) {
                case Type::TYPE_NORMAL:
                case Type::TYPE_PSYCHIC:
                    return NO_EFFECT;
                case Type::TYPE_GHOST:
                    return SUPER_EFFECT;
                default:
                    return NO_EFFECT;
            }
        case Type::TYPE_FIRE:
            switch (defense) {
                case Type::TYPE_ROCK:
                case Type::TYPE_FIRE:
                case Type::TYPE_WATER:
                case Type::TYPE_DRAGON:
                    return LITTLE_EFFECT;
                case Type::TYPE_BUG:
                case Type::TYPE_GRASS:
                case Type::TYPE_ICE:
                    return SUPER_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_WATER:
            switch (defense) {
                case Type::TYPE_GROUND:
                case Type::TYPE_ROCK:
                case Type::TYPE_FIRE:
                    return SUPER_EFFECT;
                case Type::TYPE_WATER:
                case Type::TYPE_GRASS:
                case Type::TYPE_DRAGON:
                    return LITTLE_EFFECT;
                default:
                    return SUPER_EFFECT;
            }
        case Type::TYPE_GRASS:
            switch (defense) {
                case Type::TYPE_FLYING:
                case Type::TYPE_POISON:
                case Type::TYPE_BUG:
                case Type::TYPE_FIRE:
                case Type::TYPE_GRASS:
                case Type::TYPE_DRAGON:
                    return LITTLE_EFFECT;
                case Type::TYPE_GROUND:
                case Type::TYPE_ROCK:
                case Type::TYPE_WATER:
                    return SUPER_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_ELECTRIC:
            switch (defense) {
                case Type::TYPE_FLYING:
                case Type::TYPE_WATER:
                    return SUPER_EFFECT;
                case Type::TYPE_GROUND:
                    return NO_EFFECT;
                case Type::TYPE_GRASS:
                case Type::TYPE_ELECTRIC:
                case Type::TYPE_DRAGON:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_PSYCHIC:
            switch (defense) {
                case Type::TYPE_FIGHTING:
                case Type::TYPE_POISON:
                    return SUPER_EFFECT;
                case Type::TYPE_PSYCHIC:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_ICE:
            switch (defense) {
                case Type::TYPE_FLYING:
                case Type::TYPE_GROUND:
                case Type::TYPE_GRASS:
                case Type::TYPE_DRAGON:
                    return SUPER_EFFECT;
                case Type::TYPE_FIRE:
                case Type::TYPE_ICE:
                    return LITTLE_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
        case Type::TYPE_DRAGON:
            switch (defense) {
                case Type::TYPE_DRAGON:
                    return SUPER_EFFECT;
                default:
                    return NORMAL_EFFECT;
            }
    }

    return numeric_limits<float>::quiet_NaN();
}
