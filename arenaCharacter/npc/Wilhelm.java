package arenaCharacter.npc;

import movement.Movement;

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
import sprite.charSprite.EnemyTestSprite;
import worldStage.WorldStage;

import animate.NpcAnimate;
import arenaCharacter.ArenaCharacter.State;
import arenaCharacter.Stat.StatType;
import item.Item;
import item.weapon.SteelGreatAxe;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Wilhelm extends Npc {
    /**
     *
    */
	
	Movement mvmnt;
	EnemyTestSprite sprite;
	NpcAnimate anim;
	

//CONSTRUCTORS---------------------------------------------------------------------

    public Wilhelm(
    		WorldStage stage
    ){
        /**
         *
        */
    	
    	super(CharClass.BARBARIAN);
    	
    	setStage(stage);
    	sprite = new EnemyTestSprite();
    	anim = new NpcAnimate(sprite.getFramesPerDir());
    	mvmnt = new NpcMovement(getStage(), sprite, anim, stat(StatType.SPEED).getVal());
    	setMvmnt(mvmnt);
    	
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
    	
    	bp().addItem(new SteelGreatAxe());
    	equipSlot().equipWeapon(0);
    	equipSlot().getWeapon().setAttk(2);
    	
    }

//GETTERS--------------------------------------------------------------------------


//DISPLAY--------------------------------------------------------------------------

}
