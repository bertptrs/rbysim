#include "RandomStrategy.h"
#include "move.h"
#include "pokemon.h"
#include <cstdlib>
#include <vector>

shared_ptr<Move> RandomStrategy::getMove(const Pokemon& attacker, const Pokemon&) {
    const set<shared_ptr<Move>> moveSet = attacker.getMoves();
    vector<shared_ptr<Move>> validMoves;
    for (auto move : moveSet) {
        int pp = attacker.getPP(move);
        if (pp > 0) {
            validMoves.push_back(move);
        }
    }
    if (validMoves.size() > 0) {
        int index = rand() % validMoves.size();
        return validMoves[index];
    } else {
        return shared_ptr<Move>(nullptr);
    }

    return shared_ptr<Move>(nullptr);
}
