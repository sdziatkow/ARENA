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
import arenaCharacter.ArenaCharacter.CharClass;
import arenaCharacter.ArenaCharacter.State;
import arenaCharacter.Stat.StatType;
import collision.CollisionBox;
import item.weapon.Weapon;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import movement.Movement;
import movement.NpcMovement;
import movement.PlayerMovement;
import sprite.charSprite.NpcTestSprite;
import sprite.charSprite.PlayerSprite;
import window.Controller;
import worldStage.WorldStage;

import java.util.Random;

import animate.NpcAnimate;
import animate.PlayerAnimate;


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
    	
    	if (mvmnt.getAttkHurtBox() ) {
    		setCharState(State.ATTK);
    		
    	}
    	
    	switch (getCharState()) {
    	case REST:
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
    	
    	int totalHurtBoxes = getStage().getHurtBoxes().length;
    	CollisionBox hitBox = getMvmnt().getSprite().getHitBox();
    	CollisionBox hurtBox;
    		
    	getMvmnt().getSprite().placeHitBox(
    			getMvmnt().getSprite().getHitBoxCoords(getMvmnt().getDir())
    	);
    	
    	for (int npc = 0; npc < totalHurtBoxes; ++npc) {
    		
    		if (npc != getMvmnt().getColBoxIndex()) {
    			hurtBox = getStage().getHurtBoxes()[npc];
    			if (hitBox.getBounds().intersects(hurtBox.getBounds())) {
    				equipSlot().getWeapon().setTarget(getStage().getNpc()[0]);
    				equipSlot().getWeapon().genAttk2();
    				getStage().getPlayer().stat(StatType.HP).dmg(equipSlot().getWeapon().getAttkDmg());
    				getStage().getPlayer().hurt(equipSlot().getWeapon());
    				System.out.println(
    						"PLAYER HP: [" + 
    						(int)getStage().getPlayer().stat(StatType.HP).getVal() +
    						" / " +
    						(int)getStage().getPlayer().stat(StatType.HP).getMaxVal() +
    						"]"
    						);
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
