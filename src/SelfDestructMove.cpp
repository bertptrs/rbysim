#include "movetypes.h"

SelfDestructMove::SelfDestructMove(const Type& type, unsigned int power, unsigned char accuracy, const string& name) :
    NormalMove(type, power, false, accuracy, name)
{
    selfDestruct = true;
}
