#ifndef RANDOMSTRATEGY_H
#define RANDOMSTRATEGY_H

#include "Strategy.h"

class RandomStrategy {
    public:
        virtual shared_ptr<Move> getMove(const Pokemon& attacker, const Pokemon& defender);
};

#endif
