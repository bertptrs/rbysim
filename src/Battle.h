#ifndef BATTLE_H
#define BATTLE_H

#include <iostream>
#include <memory>

class Pokemon;
class Move;
class Strategy;

using namespace std;

class Battle {
    private:
        shared_ptr<Pokemon> p1;
        shared_ptr<Pokemon> p2;
        shared_ptr<Strategy> s1;
        shared_ptr<Strategy> s2;

        void initBattle();
        void doMove(ostream& log, shared_ptr<Pokemon> attacker, shared_ptr<Pokemon> defender, shared_ptr<Move> move);
        bool battleOver() const;

    public:
        Battle(shared_ptr<Pokemon> p1, shared_ptr<Strategy> s1, shared_ptr<Pokemon> p2, shared_ptr<Strategy> s2);

        int doBattle(ostream& log);
};

#endif
