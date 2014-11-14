#include "movetypes.h"

SelfDestructMove::SelfDestructMove(const Types::Type& type, unsigned int power, unsigned char accuracy, const string& name) :
    NormalMove(type, power, false, accuracy, name)
{
    selfDestruct = true;
}
