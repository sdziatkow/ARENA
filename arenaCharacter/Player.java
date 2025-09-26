package arenaCharacter;

import animate.Animate;
import animate.PlayerAnimate;
import movement.Movement;
import movement.PlayerMovement;
import sprite.charSprite.CharacterSprite;
import sprite.charSprite.PlayerSprite;
import window.Controller;
import window.Main;
import worldStage.WorldStage;

/**
 * Program Name:    Player.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 05, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/


public class Player extends ArenaCharacter{
    /**
     * class Player:
     *  The only playable character in ARENA. Controlled by the user.
    */
	
	private WorldStage stage;
	private CharacterSprite charSprite;
	private Movement movement;
	private Animate animate;

//CONSTRUCTORS---------------------------------------------------------------------
	
    public Player() {
        /**
         * Default Constructor for class
        */
    	
    	super(CharType.PLAYER, CharClass.BRUTE);
    	
    	charSprite = new PlayerSprite();
    	movement = new PlayerMovement(this);
    	animate = new 
    			PlayerAnimate((PlayerSprite)charSprite, (PlayerMovement)movement);
    	
    	setSprite(charSprite);
    	setMvmnt(movement);
    	setAnim(animate);
    	
    }

    public Player(CharClass charClass, WorldStage stage) {
        /**
         * Constructor for class
        */
    	
    	super(CharType.PLAYER, charClass);
    	
    	setStage(stage);
    	
    	charSprite = new PlayerSprite();
    	movement = new PlayerMovement(this);
    	animate = new 
    			PlayerAnimate(charSprite, movement);
    	
    	setSprite(charSprite);
    	setMvmnt(movement);
    	setAnim(animate);
    }
    
//GETTERS--------------------------------------------------------------------------
    
    public Controller getCntrl() {
    	/**
    	 * 
    	*/
    	
    	return getStage().getWindow().getController();
    }
    
//ACTION---------------------------------------------------------------------------
    
    public void attk() {
    	/*
    	 * 
    	*/
    	
    	if (getCntrl().getAttkDown()) {
    		setCharState(State.ATTK);
    		getCntrl().setAttkDown(false);
    	}
    }
    

}
