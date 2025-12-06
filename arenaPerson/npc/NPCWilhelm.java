package arenaPerson.npc;

/**
 * Program Name:    Wilhelm.java
 *<p>
 * Purpose:         The purpose of this program is to create a non-playable
 * 					character for ARENA.
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 23, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/


import arenaEnum.personInfo.CharClass;


public class NPCWilhelm extends ArenaNPC {
    /**
     *
    */

//CONSTRUCTORS---------------------------------------------------------------------

    public NPCWilhelm(){
        /**
         *
        */
    	
    	super(CharClass.BARBARIAN);
    	setName("Wilhelm");
    	lvl().setLvl(15);
    }

//DISPLAY--------------------------------------------------------------------------

}
