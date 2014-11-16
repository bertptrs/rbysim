#include "NormalMove.h"
#include "pokemon.h"
#include <iostream>
#include "types.h"

NormalMove::NormalMove(const Type& type, unsigned int power, unsigned char accuracy, const string& name) :
    Move(type, accuracy, name),
    power(power),
    probability(0),
    moveEffect(MoveEffect::NONE),
    highCrit(false),
    selfDestruct(false)
{
}

NormalMove::NormalMove(const Type& type, unsigned int power, const StatusCondition& condition, unsigned int probability, unsigned char accuracy, const string& name) :
    Move(type, accuracy, name),
    power(power),
    probability(probability),
    moveEffect(MoveEffect::STATUS),
    condition(condition),
    highCrit(false),
    selfDestruct(false)
{
}

NormalMove::NormalMove(const Type& type, unsigned int power, const StatType& statType, int level, unsigned int probability, unsigned char accuracy, const string& name):
    Move(type, accuracy, name),
    power(power),
    probability(probability),
    moveEffect(MoveEffect::STATCHANGE),
    highCrit(false),
    selfDestruct(false)
{
    stat.stat = statType;
    stat.change = level;
}


NormalMove::NormalMove(const Type& type, unsigned int power, MoveEffect effect, unsigned int probability, unsigned char accuracy, const string& name) :
    Move(type, accuracy, name),
    power(power),
    probability(probability),
    moveEffect(effect),
    highCrit(false),
    selfDestruct(false)
{
    switch (effect) {
        case MoveEffect::FLINCH:
        case MoveEffect::CONFUSE:
        case MoveEffect::DRAIN:
        case MoveEffect::RECHARGE:
            break;

        case MoveEffect::SELFDESTRUCT:
            selfDestruct = true;
            break;

        default:
            throw "Wrong constructor used!";
            break;
    }
}

bool NormalMove::hasEffect() const {
    return (rand() % 100U) < probability;
}

bool NormalMove::isCrit(const Pokemon& attacker) const {
    int m = highCrit ? 8 : 1;
    int r = rand() % 0xff;
    int s = attacker.getBaseStat(STAT_SPEED);

    return min(m * (s / 2), 255) > r;
}

Move::Result NormalMove::move(const Pokemon& attacker, const Pokemon& defender) const {
    Result result;
    if (!hit(attacker, defender)) {
        result.effect = MoveEffect::MISS;
        return result;
    }
    // Calculate damage
    calculateDamage(attacker, defender, result);

    // Handle move effects
    switch (moveEffect) {
        case MoveEffect::STATUS:
            if (hasEffect()) {
                result.effect = MoveEffect::STATUS;
                result.status = condition;
            }
            break;

        case MoveEffect::STATCHANGE:
            if (hasEffect()) {
                result.effect = MoveEffect::STATCHANGE;
                result.statChange.stat = stat.stat;
                result.statChange.level = stat.change;
            }
            break;

        case MoveEffect::FLINCH:
        case MoveEffect::CONFUSE:
            if (hasEffect()) {
                result.effect = moveEffect;
            }
            break;

        case MoveEffect::DRAIN:
            result.effect = MoveEffect::DRAIN;
            result.drain = max(1U, result.damage / 2);
            break;

        case MoveEffect::SELFDESTRUCT:
        case MoveEffect::RECHARGE:
            result.effect = moveEffect;
            break;

        default:
            result.effect = MoveEffect::NONE;
            break;
    }

    return result;
}

// See section 2.9 of the RBY book.
unsigned int NormalMove::calculateDamage(const Pokemon& attacker, const Pokemon& defender, Result& moveResult) const {
    bool stab = attacker.hasType(type);
    const bool crit = isCrit(attacker);
    moveResult.crit = crit;
    float effectiveness = Types::getEffectiveness(type, defender.getTypes());
    if (effectiveness == 0) {
        moveResult.effect = MoveEffect::MISS;
        return 0;
    }
    int r = (rand() % (255 - 217 + 1)) + 217;
    unsigned int level = attacker.getLevel();
    unsigned int offensive, defensive;
    // These calculations ignore badge bonus.
    if (crit) {
        level *= 2;
        // TODO: Burn modifier
        offensive = min(999U, attacker.getComputedStat(getOffensiveStat()));
        defensive = min(999U, defender.getComputedStat(getDefensiveStat()));
    } else {
        // Burn modifier is included in getBuffedStat
        offensive = min(999U, (unsigned int) attacker.getBuffedStat(getOffensiveStat()));
        defensive = min(999U, (unsigned int) defender.getBuffedStat(getDefensiveStat()));
    }

    if (defender.hasBarrier(!isSpecial())) {
        offensive /= 4;
        defensive /= 2;
    }

    if (selfDestruct) {
        defensive /= 2;
    }

    // Simultaneous reduction
    if (offensive > 0xff || defensive > 0xff) {
        offensive /= 4;
        defensive /= 4;
    }

    unsigned int damage = (unsigned int) (0.4 * (level % 0xff) + 2);
    damage *= power * offensive;
    damage /= defensive;
    damage /= 50;
    damage += 2;
    if (stab) {
        damage += damage / 2;
    }
    damage *= effectiveness;
    damage *= r;
    damage /= 0xff;

    // Always deal damage
    damage = max(damage, 1U);

    // Don't deal more damage than possible.
    if (damage >= (unsigned int) defender.getHP()) {
        damage = defender.getHP();
    }
    moveResult.damage = damage;

    return damage;
}
