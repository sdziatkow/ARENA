package arenaPerson.npc;

/**
 * Program Name:    ArenaNPC.java
 *<p>
 * Purpose:         The purpose of this class is to have a base character class
 *                  that can be used by subclasses player and npc.
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 27, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import java.util.Random;
import arenaEnum.personInfo.CharClass;
import arenaEnum.personInfo.CharType;
import arenaPerson.*;

public abstract class ArenaNPC extends ArenaPerson {
    /**
     *
    */
    
    private Random attkGen = new Random();

//CONSTRUCTORS---------------------------------------------------------------------

    protected ArenaNPC() {
        /**
         * Default constructor for class Npc.
        */

        super(CharType.NPC, CharClass.BRUTE);
    }
    
    protected ArenaNPC(CharClass charClass) {
        /**
         * Constructor for class Npc.
        */

        super(CharType.NPC, charClass);
        
    }

//GETTERS--------------------------------------------------------------------------
    
    public Random getAttkGen() {
        /**
         *
        */
        return attkGen;
    }

//STATES---------------------------------------------------------------------------
    

//DISPLAY--------------------------------------------------------------------------

}
