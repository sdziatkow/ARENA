package arenaCharacter.npc;

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

import movement.NpcMovement;
import sprite.charSprite.NpcTestSprite;
import worldStage.WorldStage;

import animate.NpcAnimate;


public class Wilhelm extends Npc {
    /**
     *
    */

//CONSTRUCTORS---------------------------------------------------------------------

    public Wilhelm(WorldStage stage) {
        /**
         *
        */
    	
    	super(CharClass.BARBARIAN, stage);
    	
    	setName("Wilhelm");
    	setSprite(new NpcTestSprite());
    	setMvmnt(new NpcMovement(this));
    	setAnim(new NpcAnimate(getSprite(), getMvmnt()));

    }

//GETTERS--------------------------------------------------------------------------

//STATES---------------------------------------------------------------------------
    
    public void attk() {
    	/*
    	 * 
    	*/
    	
    }

//DISPLAY--------------------------------------------------------------------------

}
