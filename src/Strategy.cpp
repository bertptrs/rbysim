#include "Strategy.h"
#include "pokemon.h"
#include "move.h"

shared_ptr<Move> Strategy::getMove(const Pokemon&, const Pokemon&) {
    return shared_ptr<Move>(nullptr);
}
