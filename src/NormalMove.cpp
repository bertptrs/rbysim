#include "move.h"

NormalMove::NormalMove(const Types::Type& type, unsigned int power, bool highCrit, unsigned char accuracy, const string& name) :
    Move(type, accuracy, name),
    power(power),
    probability(0),
    moveEffect(Move::MoveEffect::NONE),
    highCrit(highCrit),
    selfDestruct(false)
{
}

NormalMove::NormalMove(const Types::Type& type, unsigned int power, const StatusCondition& condition, unsigned int probability, unsigned char accuracy, const string& name) :
    Move(type, accuracy, name),
    power(power),
    probability(probability),
    moveEffect(Move::MoveEffect::STATUS),
    condition(condition),
    highCrit(false),
    selfDestruct(false)
{
}

NormalMove::NormalMove(const Types::Type& type, unsigned int power, const StatType& statType, int level, unsigned int probability, unsigned char accuracy, const string& name):
    Move(type, accuracy, name),
    power(power),
    probability(probability),
    moveEffect(Move::MoveEffect::STATCHANGE),
    highCrit(false),
    selfDestruct(false)
{
    stat.stat = statType;
    stat.change = level;
}

bool NormalMove::hasEffect() const {
    return (rand() % 100U) < probability;
}

bool NormalMove::isCrit(const Pokemon& attacker) const {
    int m = highCrit ? 8 : 1;
    int r = rand() % 0xff;
    int s = attacker.getBaseStat(STAT_SPEED);

    return min(m * (s / 2), 255) < r;
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
    if (hasEffect()) {
        switch (moveEffect) {
            case MoveEffect::STATUS:
                result.effect = MoveEffect::STATUS;
                result.status = condition;
                break;

            case MoveEffect::STATCHANGE:
                result.effect = MoveEffect::STATCHANGE;
                result.statChange.stat = stat.stat;
                result.statChange.level = stat.change;
                break;

            default:
                    result.effect = MoveEffect::NONE;
                    break;
        }
    } else {
        result.effect = MoveEffect::NONE;
    }
    return result;
}

// See section 2.9 of the RBY book.
unsigned int NormalMove::calculateDamage(const Pokemon& attacker, const Pokemon& defender, Result& moveResult) const {
    bool stab = attacker.hasType(type);
    const bool crit = isCrit(attacker);
    moveResult.crit = crit;
    float effectiveness = Types::getEffectiveness(type, defender.getTypes());
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
    moveResult.damage = damage;

    return damage;
}
