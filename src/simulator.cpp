#include <iostream>
#include "Battle.h"
#include "Strategy.h"
#include <memory>
#include <ctime>
#include <cstdlib>
#include "PokemonDB.h"
#include "pokemon.h"
#include "RandomStrategy.h"
#include <random>

using namespace std;

class Pokemon;

void initRNG() {
    // Got tired of seeing the same battle twice,
    // so here we seed the RNG properly.
    random_device rd;
    srand(rd());
}

int main() {
    initRNG();

    try {
        PokemonDB pokemonDB;

        shared_ptr<Pokemon> p1 = pokemonDB.getPokemon("Mew");
        shared_ptr<Pokemon> p2 = pokemonDB.getPokemon("Mewtwo");
        shared_ptr<Strategy> s1 = make_shared<RandomStrategy>();
        shared_ptr<Strategy> s2 = make_shared<RandomStrategy>();

        Battle battle(p1, s1, p2, s2);
        battle.doBattle(cout);

        return 0;
    } catch (const char * ex) {
        cout << ex << endl;
        return 1;
    }
}
