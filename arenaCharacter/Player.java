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

import animate.PlayerAnimate;
import arenaCharacter.Stat.StatType;
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

public class Player extends ArenaCharacter{
    /**
     * class Player:
     *  The only playable character in ARENA. Controlled by the user.
    */
	
	private Movement mvmnt;
	private PlayerSprite sprite;
	private PlayerAnimate anim;
	private Controller cntrl;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
    public Player() {
        /**
         * Default Constructor for class Player
        */
    	
    	super(CharType.PLAYER, CharClass.BRUTE);
    }
    
    public Player(
    		CharClass charClass, 
    		WorldStage stage,
    		Controller cntrl
    	){
        /**
         * Constructor for class Player.
        */
    	
    	super(CharType.PLAYER, charClass);
    	
    	setStage(stage);
    	this.cntrl = cntrl;
    	
    	// Create sprite and animation objects for PlayerMovement object.
    	sprite = new PlayerSprite();
    	anim = new PlayerAnimate(sprite.getFramesPerDir());
    	
    	
    	mvmnt = new PlayerMovement(
					getStage(), 
					sprite, 
					anim, 
					cntrl,
					stat(StatType.SPEED).getVal()
    	);
    	
    	setMvmnt(mvmnt);
    	
    	// Create an event handler for when the Player's attack animation is done.
    	getMvmnt().getAttkAnim().setOnFinished(new EventHandler<ActionEvent>() {
    		/*
    		 * This is called when attkAnim has passed through maxFramesPerDir
    		 * KeyFrames.
    		 * This method removes the hitbox from ArenaCharacter's Group and sets
    		 * their state to MOVE.
    		*/
    		
    		public void handle(ActionEvent e) {
    			/*
    			 * 
    			*/
    			
    			// Remove hitBox from spriteGroup
    			sprite.getCharPane().getChildren().remove(sprite.getWeaponSprite().getSpriteView());
    			sprite.getSpriteGroup().getChildren().remove(
    					sprite.getHitBox().getColBox()
    			);
    			
    			setCharState(State.MOVE);
    		}
    	});
    	
    }

    public Player(
    		CharClass charClass, 
    		WorldStage stage,
    		PlayerSprite sprite,
    		PlayerAnimate anim,
    		Controller cntrl
    	){
        /**
         * Constructor for class Player.
        */
    	
    	super(CharType.PLAYER, charClass);
    	
    	setStage(stage);
    	this.cntrl = cntrl;
    	
    	// Create a movement object with all given data.
    	mvmnt = new PlayerMovement(
					getStage(), 
					sprite, 
					anim, 
					cntrl,
					stat(StatType.SPEED).getVal()
    	);
    	
    	setMvmnt(mvmnt);
    	
    	// Create an event handler for when attack animation is done.
    	getMvmnt().getAttkAnim().setOnFinished(new EventHandler<ActionEvent>() {
    		/*
    		 * This is called when attkAnim has passed through maxFramesPerDir
    		 * KeyFrames.
    		 * This method removes the hitbox from ArenaCharacter's Group and sets
    		 * their state to MOVE.
    		*/
    		
    		public void handle(ActionEvent e) {
    			/*
    			 * 
    			*/
    			
    			// Remove hitBox from spriteGroup
    			sprite.getCharPane().getChildren().remove(sprite.getWeaponSprite().getSpriteView());
    			sprite.getSpriteGroup().getChildren().remove(
    					sprite.getHitBox().getColBox()
    			);
    			
    			setCharState(State.MOVE);
    		}
    	});
    	
    }
    
//GETTERS--------------------------------------------------------------------------
    
    public Controller getCntrl() {
    	/**
    	 * 
    	*/
    	
    	return cntrl;
    }
    
//ACTION---------------------------------------------------------------------------
    
    
    public void stateMachine() {
    	/*
    	 * This method will continuosly run to switch the player between states. 
    	*/
    	
       	getStage().getOverlay().updateStatBars(state(), lvl());
    	
    	// If the attack key is pressed, switch state to ATTK.
    	if (!getCharState().equals(State.REST) && getCntrl().getAttkDown()) {
    		setCharState(State.ATTK);
    	}
    	
    	// Switch the character's current state.
    	switch (getCharState()) {
    	case REST:
    		getMvmnt().getMvAnim().pause();
    		getMvmnt().getAttkAnim().pause();
    		break;
    	case MOVE: // Move the character and play mvAnim given it is not already.
    		getMvmnt().move();
    		break;
    	case ATTK: // Need if here so it is only run once.
    		if (getCntrl().getAttkDown()) {
    			
    			// Pause mvAnim, play attkAnim and run attk.
    			mvmnt.getMvAnim().pause();
    			mvmnt.getAttkAnim().play();
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
    	
    	// Get the Player's hitBox and the total amount of hurtBoxes on the stage.
    	int totalHurtBoxes = getStage().getHurtBoxes().length;
    	CollisionBox hitBox = getMvmnt().getSprite().getHitBox();
    	CollisionBox hurtBox;
    	
    	// Place the Player's hitBox.
    	getMvmnt().getSprite().placeHitBox(getMvmnt().getSimpleDir());
    	
    	// For each hurtBox on the stage.
    	for (int npc = 0; npc < totalHurtBoxes; ++npc) {
    		
    		// If not checking own hurtBox, set hurtBox to the next one in the list.
    		if (npc != getMvmnt().getColBoxIndex()) {
    			hurtBox = getStage().getHurtBoxes()[npc];
    			
    			// If Player's hitBox intersects current hurtBox.
    			if (hitBox.getBounds().intersects(hurtBox.getBounds())) {
    				
    				// Damage the hurtBox.
    				weapon().setTarget(getStage().getAllChars()[npc]);
    				weapon().genAttk1();
    				getStage().getAllChars()[npc].stat(StatType.HP).dmg(weapon().getAttkDmg());
    			}
    		}
    	}
    	
    	stat(StatType.SP).dmg(10);
    	getStage().getOverlay().updateStatBars(state(), lvl());
    	
//		System.out.println("NPC HP: " + getStage().getNpc()[0].stat(StatType.HP).getVal());
//		System.out.println("PLAYER WEAPON: " + equipSlot().getWeapon().getName());
//		System.out.println("WEAPON DMG: " + equipSlot().getWeapon().getAttkDmg());
		
    }
    
    public void hurt(Weapon weapon) {
    	/*
    	 * 
    	*/
    	
    }
    

}
