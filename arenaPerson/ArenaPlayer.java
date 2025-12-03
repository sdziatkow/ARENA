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
import arenaEnum.personInfo.CharState;
import arenaEnum.personInfo.CharType;
import arenaEnum.personStats.StatType;
import collision.CollisionBox;
import item.weapon.Weapon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import movement.Movement;
import movement.PlayerMovement;
import sprite.charSprite.PlayerSprite;
import window.Controller;
import worldStage.WorldStage;

public class ArenaPlayer extends ArenaPerson{
    /**
     * class Player:
     *  The only playable character in ARENA. Controlled by the user.
    */
	
	private Controller cntrl;
	
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
    
    
    public void stateMachine() {
    	/*
    	 * This method will continuosly run to switch the player between states. 
    	*/
    	
    	// If the attack key is pressed, switch state to ATTK.
    	if (!getCharState().equals(CharState.REST)) {
    		setCharState(CharState.ATTK);
    	}
    	
    	// Switch the character's current state.
    	switch (getCharState()) {
    	case REST:
    		break;
    	case MOVE: // Move the character and play mvAnim given it is not already.
    		break;
    	case ATTK: // Need if here so it is only run once.
    		break;
    	}
    }
    
    public void attk() {
    	/*
    	 * 
    	*/
    }
    
    

}
