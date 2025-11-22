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
import arenaCharacter.ArenaCharacter.State;
import arenaCharacter.Stat.StatType;
import collision.CollisionBox;
import item.Item.ItemType;
import item.weapon.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import movement.Movement;
import movement.NpcMovement;
import sprite.charSprite.NpcTestSprite;
import window.Controller;
import worldStage.WorldStage;
import java.util.Random;
import animate.NpcAnimate;


public abstract class Npc extends ArenaCharacter {
    /**
     *
    */
    
    private Random attkGen = new Random();
    private Movement mvmnt;

//CONSTRUCTORS---------------------------------------------------------------------

    protected Npc() {
        /**
         * Default constructor for class Npc.
        */

        super(CharType.NPC, CharClass.BRUTE);
    }
    
    protected Npc(CharClass charClass) {
        /**
         * Constructor for class Npc.
        */

        super(CharType.NPC, charClass);
        
    }
    
    protected Npc(
    		CharClass charClass, 
    		WorldStage stage
    	){
        /**
         * Constructor for class Npc.
        */
    	
    	super(CharType.NPC, charClass);
    	setStage(stage);
    }
    
    protected Npc(
    		CharClass charClass, 
    		WorldStage stage,
    		NpcTestSprite sprite,
    		NpcAnimate anim
    	){
        /**
         * Constructor for class Npc.
        */

    	super(CharType.NPC, charClass);
    	setStage(stage);
    	
    	// Create a movement object with given data. 
    	mvmnt = new NpcMovement(
					getStage(), 
					sprite, 
					anim,
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
    	
    	NpcMovement mvmnt = (NpcMovement)getMvmnt();
    	
    	if (!getCharState().equals(State.REST) && mvmnt.getAttkHurtBox()) {
    		setCharState(State.ATTK);
    		
    	}
    	
    	switch (getCharState()) {
    	case REST:
    		getMvmnt().getMvAnim().pause();
    		getMvmnt().getAttkAnim().pause();
    		break;
    	case MOVE:
    		getMvmnt().move();
    		break;
    	case ATTK:
    		if (mvmnt.getAttkHurtBox() ) {
    			getMvmnt().getMvAnim().pause();
    			getMvmnt().getAttkAnim().play();
    			mvmnt.toggleAttkHurtBox();
    			attk();
    		}
    		break;
    	}
    	
    }
    
    public void attk() {
    	/*
    	 * 
    	*/
    	
    	Weapon weapon = (Weapon)equipSlot().getEquipped(ItemType.WEAPON);
    	
    	int totalHurtBoxes = getStage().getHurtBoxes().length;
    	CollisionBox hitBox = getMvmnt().getSprite().getHitBox();
    	CollisionBox hurtBox;
    		
    	getMvmnt().getSprite().placeHitBox(getMvmnt().getDir());
    	
    	for (int npc = 0; npc < totalHurtBoxes; ++npc) {
    		
    		if (npc != getMvmnt().getColBoxIndex()) {
    			hurtBox = getStage().getHurtBoxes()[npc];
    			if (hitBox.getBounds().intersects(hurtBox.getBounds())) {
    				weapon.setTarget(getStage().getAllChars()[npc]);
    				weapon.genAttk1();
    				getStage().getAllChars()[npc].stat(StatType.HP).dmg(weapon.getAttkDmg());
    				getStage().getAllChars()[npc].hurt();
    			}
    		}
    	}
    }
    
    public void hurt(Weapon weapon) {
    	/*
    	 * 
    	*/
    	
    	
    }

//DISPLAY--------------------------------------------------------------------------

}
