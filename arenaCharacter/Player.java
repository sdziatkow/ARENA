package arenaCharacter;

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

import animate.Animate;
import animate.PlayerAnimate;
import arenaCharacter.Stat.StatType;
import collision.CollisionBox;
import javafx.animation.Animation;
import movement.Movement;
import movement.PlayerMovement;
import sprite.charSprite.CharacterSprite;
import sprite.charSprite.PlayerSprite;
import window.Controller;
import worldStage.WorldStage;

public class Player extends ArenaCharacter{
    /**
     * class Player:
     *  The only playable character in ARENA. Controlled by the user.
    */
	
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
    
    
    public void stateMachine() {
    	/*
    	 * This method will continuosly run to switch the player between states. 
    	*/
    	
    	// If the attack key is pressed, switch state to ATTK.
    	if (getCntrl().getAttkDown()) {
    		setCharState(State.ATTK);
    	}
    	
    	// Switch the character's current state.
    	switch (getCharState()) {
    	case REST:
    		break;
    	case MOVE: // Move the character and play mvAnim given it is not already.
    		getMvmnt().move();
    		if (!getAnim().getMvAnim().getStatus().equals(Animation.Status.RUNNING)) {
    			getAnim().getMvAnim().play();
    		}
    		break;
    	case ATTK: // Need if here so it is only run once.
    		if (getCntrl().getAttkDown()) {
    			
    			// Pause mvAnim, play attkAnim and run attk.
    			getAnim().getMvAnim().pause();
    			getAnim().getAttkAnim().play();
    			attk();
    			getCntrl().setAttkDown(false);
    		}
    		break;
    	}
    }
    
    public void attk() {
    	/*
    	 * 
    	*/
    	
    	int totalHurtBoxes = getStage().getHurtBoxes().length;
    	CollisionBox hitBox = getSprite().getHitBox();
    	CollisionBox hurtBox;
    		
    	getSprite().placeHitBox(getSprite().getHitBoxCoords(getMvmnt().getDir()));
    	
    	for (int npc = 0; npc < totalHurtBoxes; ++npc) {
    		
    		if (npc != getMvmnt().getColBoxIndex()) {
    			hurtBox = getStage().getHurtBoxes()[npc];
    			if (hitBox.getBounds().intersects(hurtBox.getBounds())) {
    				equipSlot().getWeapon().setTarget(getStage().getNpc()[0]);
    				equipSlot().getWeapon().genAttk1();
    				getStage().getNpc()[0].stat(StatType.HP).dmg(equipSlot().getWeapon().getAttkDmg());
    			}
    		}
    	}
		
//		System.out.println("NPC HP: " + getStage().getNpc()[0].stat(StatType.HP).getVal());
//		System.out.println("PLAYER WEAPON: " + equipSlot().getWeapon().getName());
//		System.out.println("WEAPON DMG: " + equipSlot().getWeapon().getAttkDmg());
		
    }
    	
    

}
