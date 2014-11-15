#ifndef MOVETYPES_H
#define MOVETYPES_H
#include "move.h"

using namespace std;

class SelfDestructMove : public NormalMove {
    public:
        SelfDestructMove(const Type& move, unsigned int power, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
};

class RecoilMove : public NormalMove {
    private:
        int fraction;
    public:
        RecoilMove(const Type& type, unsigned int power, const unsigned int recoilFraction = 4, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

#endif
