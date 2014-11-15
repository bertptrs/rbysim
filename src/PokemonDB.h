#ifndef POKEMONDB_H
#define POKEMONDB_H
#include "stats.h"
#include <map>
#include <set>
#include <memory>

using namespace std;

enum class Type;
class Pokemon;

class PokemonDB {
    private:
        map<string, BaseStats> baseStats;
        map<string, set<Type>> types;
        static const map<string, Type> TYPEMAP;
        
        Type getType(const string& typeName) const;
        void load();

    public:
        PokemonDB();

        BaseStats getBaseStats(const string& name) const;
        shared_ptr<Pokemon> getPokemon(const string& name) const;
        set<Type> getTypes(const string& name) const;
};

#endif
