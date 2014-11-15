#ifndef MOVETYPES_H
#define MOVETYPES_H
#include "move.h"

using namespace std;

/*!
 * A class for moves with fixed damage, such as Dragon Rage.
 */
class FixedDamageMove : public Move {
    private:
        unsigned int damage;

    public:
        FixedDamageMove(const Type& type, unsigned int damage, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

/*!
 * A class for moves that only have a special effect and no damage, such as supersonic.
 */
class EffectMove : public Move {
    private:
        bool target;
        MoveEffect effect;
        union {
            StatusCondition status;
            struct {
                StatType stat;
                int change;
            } statChange;
        };

    public:
        //! Constructor for stat changes.
        EffectMove(const Type& type, bool target, StatType stat, int level, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        //! Constructor for status infliction
        EffectMove(const Type& type, StatusCondition status, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        //! Constructor for confusion
        EffectMove(const Type& type, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);

        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

/*!
 * A class for moves that get the self destruct defensive drop.
 */
class SelfDestructMove : public NormalMove {
    public:
        SelfDestructMove(const Type& move, unsigned int power, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
};

/*!
 * A class for moves with recoil, such as Take Down
 */
class RecoilMove : public NormalMove {
    private:
        int fraction;
    public:
        RecoilMove(const Type& type, unsigned int power, const unsigned int recoilFraction = 4, unsigned char accuracy = MAX_ACCURACY, const string& name = DEFAULT_NAME);
        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

/*!
 * A class for moves that need to charge for a turn, such as Fly and Solarbeam.
 */
class ChargingMove : public NormalMove {
    private:
        bool invincible;

    public:
        ChargingMove(const Type& type, unsigned int power, bool invincible, unsigned char accuracy, const string& name = DEFAULT_NAME);

        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

/*!
 * A class for the move Swift, which does NOT check accuracy.
 */
class SwiftMove : public NormalMove {
    private:
        static const unsigned int SWIFT_POWER;

    public:
        SwiftMove();
        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

/*!
 * A class for the move Hyperbeam.
 */
class HyperBeamMove : public NormalMove {
    private:
        static const unsigned int HYPERBEAM_POWER;

    public:
        HyperBeamMove();
        virtual Result move(const Pokemon& attacker, const Pokemon& defender) const;
};

#endif
