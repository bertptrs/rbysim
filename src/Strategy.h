#ifndef STRATEGY_H
#define STRATEGY_H

#include <memory>

using namespace std;

class Pokemon;
class Move;

class Strategy {
    public:
        shared_ptr<Move> getMove(const Pokemon& attacker, const Pokemon& defender);
};

#endif
