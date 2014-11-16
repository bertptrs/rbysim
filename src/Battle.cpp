#include "Battle.h"
#include "pokemon.h"
#include "move.h"
#include "enums.h"
#include "Strategy.h"

typedef shared_ptr<Move> mptr;
typedef shared_ptr<Pokemon> pptr;

Battle::Battle(shared_ptr<Pokemon> p1, shared_ptr<Strategy> s1, shared_ptr<Pokemon> p2, shared_ptr<Strategy> s2) :
    p1(p1),
    p2(p2),
    s1(s1),
    s2(s2)
{
}

void Battle::initBattle() {
    // Initialize HP
    p1->state.hp = p1->getComputedStat(STAT_HP);
    p2->state.hp = p2->getComputedStat(STAT_HP);
    // Initialize PP
    // TODO
}

int Battle::doBattle(ostream& log) {
    initBattle();
    log << "Initialized battle" << endl;
    int turn = 1;

    while (!battleOver()) {
        log << "Turn " << turn++ << endl
            << "----" << endl;

        mptr m1 = s1->getMove(*p1, *p2);
        mptr m2 = s2->getMove(*p2, *p1);

        if (!m1) m1 = Move::STRUGGLE;
        if (!m2) m2 = Move::STRUGGLE;

        unsigned int speed1 = p1->getBuffedStat(STAT_SPEED);
        unsigned int speed2 = p2->getBuffedStat(STAT_SPEED);
        speed1 += rand() % 2;

        pptr first,second;
        mptr firstMove, secondMove;

        if (speed1 > speed2) {
            first = p1;
            second = p2;
            firstMove = m1;
            secondMove = m2;
        } else {
            first = p2;
            second = p1;
            firstMove = m2;
            secondMove = m1;
        }

        doMove(log, first, second, firstMove);
        if (!battleOver()) {
            doMove(log, second, first, secondMove);
        }

        log << endl;

    }

    if (p1->state.hp <= 0) {
        if (p2->state.hp <= 0) {
            log << "It's a tie!" << endl;
            return 0;
        } else {
            log << p2->getName() << " wins" << endl;
            return 2;
        }
    } else {
        log << p1->getName() << " wins" << endl;
        return 1;
    }
}

bool Battle::battleOver() const {
    return p1->state.hp <= 0 || p2->state.hp <= 0;
}

void Battle::doMove(ostream& log, shared_ptr<Pokemon> attacker, shared_ptr<Pokemon> defender, shared_ptr<Move> move) {
    if (attacker->state.recharging) {
        attacker->state.recharging = false;
        log << attacker->getName() << " must recharge!" << endl;
    }

    Move::Result result = move->move(*attacker, *defender);

    log << attacker->getName() << " used " << move->getName() << endl;

    if (result.effect == MoveEffect::MISS) {
        log << attacker->getName() << " missed!" << endl; 
        return;
    } else if (result.crit) {
        log << "Critical hit!" << endl;
    }

    defender->state.hp -= result.damage;
    log <<  defender->getName() << " got " 
        << result.damage << " damage" << endl;

    switch (result.effect) {
        case MoveEffect::RECOIL:
            attacker->state.hp -= result.recoil;
            log << attacker->getName() << " got " 
                << result.recoil << " recoil" << endl;
            break;
        case MoveEffect::STATCHANGE:
            defender->state.buffs[result.statChange.stat] +=
                result.statChange.level;
            defender->state.buffs[result.statChange.stat] =
                max(-6, min(defender->state.buffs[result.statChange.stat], 6));
            log << defender->getName()
                << "'s " << Stats::getName(result.statChange.stat) 
                <<" changed by " << result.statChange.level
                << endl;
            break;
        case MoveEffect::STATUS:
            if (!defender->state.hasStatus()) {
                switch (result.status) {
                    case StatusCondition::STATUS_BADLY_POISONED:
                    case StatusCondition::STATUS_POISONED:
                        if (!defender->hasType(Type::TYPE_POISON))
                            defender->state.status = result.status;
                        break;

                    case StatusCondition::STATUS_FROZEN:
                        if (!defender->hasType(Type::TYPE_ICE))
                            defender->state.status = result.status;
                        break;
                    case StatusCondition::STATUS_BURNED:
                        if (!defender->hasType(Type::TYPE_FIRE))
                            defender->state.status = result.status;
                        break;
                    default:
                        defender->state.status = result.status;
                        break;
                }
            }
            break;
        case MoveEffect::RECHARGE:
            attacker->state.recharging = true;
            break;
        case MoveEffect::MISS:
        case MoveEffect::NONE:
            // Do nothing
            break;
    }
}
