#include "RandomStrategy.h"
#include "move.h"
#include "pokemon.h"
#include <cstdlib>
#include <vector>

shared_ptr<Move> RandomStrategy::getMove(const Pokemon& attacker, const Pokemon&) {
    const set<shared_ptr<Move>> moveSet = attacker.getMoves();
    vector<shared_ptr<Move>> moves(moveSet.begin(), moveSet.end());
    set<int> tried;

    while (tried.size() < moves.size()) {
        int index;
        do {
            index = rand() % moves.size();
        } while (tried.count(index));

        int pp = attacker.getPP(moves[index]);
        if (pp > 0) {
            return moves[index];
        } else {
            tried.insert(index);
        }
    }

    return shared_ptr<Move>(nullptr);
}
