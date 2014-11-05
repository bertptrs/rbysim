#include "stats.h"

IVStats::IVStats(const initializer_list<int>& l)
{
    auto it = l.begin();
    if (it != l.end()) {
        attack = *it;
        it++;
    }
    if (it != l.end()) {
        defense = *it;
        it++;
    }
    if (it != l.end()) {
        speed = *it;
        it++;
    }
    if (it != l.end()) {
        special = *it;
    }
}

unsigned int IVStats::getHP() const {
    return ((attack & 1) << 3)
        | ((defense & 1) << 2)
        | ((speed & 1) << 1)
        | (special & 1);
}

BaseStats::BaseStats(const initializer_list<int>& l) {
    auto it = l.begin();
    if (it != l.end()) {
        attack = *it;
        it++;
    }
    if (it != l.end()) {
        defense = *it;
        it++;
    }
    if (it != l.end()) {
        speed = *it;
        it++;
    }
    if (it != l.end()) {
        special = *it;
        it++;
    }
    if (it != l.end()) {
        hp = *it;
    }
}
