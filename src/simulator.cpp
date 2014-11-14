#include <iostream>
#include "pokemon.h"
#include "Battle.h"
#include "Strategy.h"
#include <memory>
#include <ctime>
#include <cstdlib>

using namespace std;

int main() {
    srand(time(NULL));

    BaseStats mew = {100, 100, 100, 100, 100};
    BaseStats mewtwo = {110, 90, 130, 154, 106};
    shared_ptr<Pokemon> p1 = make_shared<Pokemon>(mew);
    shared_ptr<Pokemon> p2 = make_shared<Pokemon>(mewtwo);
    p1->setName("Mew");
    p2->setName("Mewtwo");
    shared_ptr<Strategy> s1 = make_shared<Strategy>();
    shared_ptr<Strategy> s2 = make_shared<Strategy>();

    p1->addType(Type::TYPE_PSYCHIC);
    p2->addType(Type::TYPE_PSYCHIC);

    Battle battle(p1, s1, p2, s2);
    battle.doBattle(cout);

    return 0;
}
