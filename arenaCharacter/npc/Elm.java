package arenaCharacter.npc;

/**
 * Program Name:    Elm.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         Month DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import animate.NpcAnimate;
import arenaCharacter.Stat.StatType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import movement.NpcMovement;
import sprite.charSprite.ElmSprite;
import worldStage.WorldStage;

public class Elm extends Npc{
	/*
	 * 
	*/
	
	ElmSprite sprite;
	NpcAnimate anim;
	
	public Elm(WorldStage stage) {
		/*
		 * 
		*/
		
	   	super(CharClass.MONK);
    	setStage(stage);
    	setName("Elm");
    	
    	sprite = new ElmSprite();
    	anim = new NpcAnimate(sprite.getFramesPerDir());
    	setMvmnt(new NpcMovement(
    			getStage(),
    			sprite,
    			anim,
    			stat(StatType.SPEED).getVal()
    	));
    	
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
	
	public void hurt() {
		
	}

}
