package arenaCharacter.npc;

/**
 * Program Name:    Wilhelm.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 23, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.*;
import movement.NpcMovement;
import sprite.charSprite.NpcTestSprite;
import window.Controller;
import worldStage.WorldStage;

import java.util.Random;

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
