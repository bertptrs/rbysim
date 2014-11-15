#include <iostream>
#include "Battle.h"
#include "Strategy.h"
#include <memory>
#include <ctime>
#include <cstdlib>
#include "PokemonDB.h"
#include "pokemon.h"

using namespace std;

class Pokemon;

int main() {
    srand(time(NULL));

    try {
        PokemonDB pokemonDB;

        shared_ptr<Pokemon> p1 = pokemonDB.getPokemon("Mew");
        shared_ptr<Pokemon> p2 = pokemonDB.getPokemon("Mewtwo");
        shared_ptr<Strategy> s1 = make_shared<Strategy>();
        shared_ptr<Strategy> s2 = make_shared<Strategy>();

        Battle battle(p1, s1, p2, s2);
        battle.doBattle(cout);

        return 0;
    } catch (const char * ex) {
        cout << ex << endl;
        return 1;
    }
}
