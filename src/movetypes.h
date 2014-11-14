#ifndef MOVETYPES_H
#define MOVETYPES_H

#include "move.h"

using namespace std;

class SelfDestructMove : public NormalMove {
    public:
        SelfDestructMove(const Type& move, unsigned int power, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
};

#endif
