package arenaCharacter.npc;

/**
 * Program Name:    Npc.java
 *<p>
 * Purpose:         The purpose of this program is to have a base class that all
 * 					non-playable characters will use.
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
import javafx.animation.Animation;
import movement.NpcMovement;
import window.Controller;
import worldStage.WorldStage;

import java.util.Random;


public abstract class Npc extends ArenaCharacter {
    /**
     *
    */
    
    private Random attkGen = new Random();

//CONSTRUCTORS---------------------------------------------------------------------

    protected Npc() {
        /**
         *
        */

        super(CharType.NPC, CharClass.BRUTE);
    }
    
    protected Npc(CharClass charClass, WorldStage stage) {
        /**
         *
        */

        super(CharType.NPC, charClass);
        setStage(stage);
    }

//GETTERS--------------------------------------------------------------------------
    
    public Random getAttkGen() {
        /**
         *
        */
        return attkGen;
    }
    
    public Controller getCntrl() {
    	/**
    	 * 
    	*/
    	
    	return null;
    }

//STATES---------------------------------------------------------------------------
    
    public void stateMachine() {
    	/*
    	 * 
    	*/
    	
    	switch (getCharState()) {
    	case REST:
    		break;
    	case MOVE:
    		((NpcMovement)getMvmnt()).checkNextMove();
    		if (!getAnim().getMvAnim().getStatus().equals(Animation.Status.RUNNING)) {
    			getAnim().getMvAnim().play();
    		}
    		break;
    	case ATTK:
    		break;
    	}
    	
    }

//DISPLAY--------------------------------------------------------------------------

}
