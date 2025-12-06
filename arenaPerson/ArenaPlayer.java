package arenaPerson;

/**
 * Program Name:    Player.java
 *<p>
 * Purpose:         The purpose of this program is to create a playable character
 * 					for ARENA.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 05, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaEnum.personInfo.CharClass;
import arenaEnum.personInfo.CharType;

public class ArenaPlayer extends ArenaPerson{
    /**
     * class Player:
     *  The only playable character in ARENA. Controlled by the user.
    */
	
//CONSTRUCTORS---------------------------------------------------------------------
	
    public ArenaPlayer() {
        /**
         * Default Constructor for class Player
        */
    	
    	super(CharType.PLAYER, CharClass.BRUTE);
    }
    
    public ArenaPlayer(CharClass charClass){
        /**
         * Constructor for class Player.
        */
    	
    	super(CharType.PLAYER, charClass);
    	
    }
    
//GETTERS--------------------------------------------------------------------------
    
//ACTION---------------------------------------------------------------------------
    
    

}
