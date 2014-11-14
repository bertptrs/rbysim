#include "Strategy.h"
#include "pokemon.h"
#include "move.h"

shared_ptr<Move> Strategy::getMove(const Pokemon& attacker, const Pokemon& defender) {
    return shared_ptr<Move>(nullptr);
}
