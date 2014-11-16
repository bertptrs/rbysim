#include "PokemonDB.h"
#include "enums.h"
#include <iostream>
#include <fstream>
#include "pokemon.h"

const map<string, Type> PokemonDB::TYPEMAP = {
    {"Grass", Type::TYPE_GRASS},
    {"Water", Type::TYPE_WATER},
    {"Fire", Type::TYPE_FIRE},
    {"Electric", Type::TYPE_ELECTRIC},
    {"Ice", Type::TYPE_ICE},
    {"Dragon", Type::TYPE_DRAGON},
    {"Psychic", Type::TYPE_PSYCHIC},
    {"Normal", Type::TYPE_NORMAL},
    {"Flying", Type::TYPE_FLYING},
    {"Fighting", Type::TYPE_FIGHTING},
    {"Rock", Type::TYPE_ROCK},
    {"Ground", Type::TYPE_GROUND},
    {"Poison", Type::TYPE_POISON},
    {"Ghost", Type::TYPE_GHOST},
    {"Bug", Type::TYPE_BUG}
};

PokemonDB::PokemonDB() {
    load();
}

BaseStats PokemonDB::getBaseStats(const string& name) const {
    auto it = baseStats.find(name);

    return it == baseStats.end() ? BaseStats({}) : it->second;
}

void PokemonDB::load() {
    ifstream dbfile("data/pokemon.csv");
    if (!dbfile.good()) {
        throw "Could not read basestats db";
    }

    string dump;
    // Ignore first line.
    getline(dbfile, dump);

    while (true) {
        string name;
        unsigned int hp, attack, defense, speed, special;
        getline(dbfile, name, ',');
        if (dbfile.eof()) {
            return; // Was the last entry
        }

        dbfile >> hp;
        dbfile.ignore();
        dbfile >> attack;
        dbfile.ignore();
        dbfile >> defense;
        dbfile.ignore();
        dbfile >> speed;
        dbfile.ignore();
        dbfile >> special;
        dbfile.ignore();

        BaseStats base = {attack, defense, speed, special, hp};
        baseStats[name] = base;

        string type;
        getline(dbfile, type, ',');
        types[name].insert(getType(type));

        if (dbfile.peek() != '\n' && dbfile.peek() != '\r') {
            getline(dbfile, type);
            if (type.length() > 0) {
                types[name].insert(getType(type));
            }
        } else {
            dbfile.get();
        }
    }
}

Type PokemonDB::getType(const string& type) const {
    auto it = TYPEMAP.find(type);
    if (it == TYPEMAP.end()) {
        string error = "Could not find type: " + type;
        throw error.c_str();
    }
    return it->second;
}

shared_ptr<Pokemon> PokemonDB::getPokemon(const string& name) const {
    BaseStats base = getBaseStats(name);
    shared_ptr<Pokemon> pokemon = make_shared<Pokemon>(base);
    pokemon->setName(name);
    for (Type type : getTypes(name)) {
        pokemon->addType(type);
    }

    return pokemon;
}

set<Type> PokemonDB::getTypes(const string& name) const {
    auto it = types.find(name);

    if (it == types.end()) {
       return {};
    } else {
        return it->second;
    }
}
