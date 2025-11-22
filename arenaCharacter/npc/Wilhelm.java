package arenaCharacter.npc;

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
import sprite.charSprite.PlayerSprite;
import worldStage.WorldStage;

import animate.NpcAnimate;
import arenaCharacter.ArenaCharacter.State;
import arenaCharacter.Stat.StatType;
import item.Item.ItemType;
import item.weapon.SteelGreatAxe;
import item.weapon.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Wilhelm extends Npc {
    /**
     *
    */

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
    	setName("Wilhelm");
    	
    	sprite = new EnemyTestSprite();
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
    	
    	bp().addItem(new SteelGreatAxe());
    	bp().look(ItemType.WEAPON);
    	bp().setItemSlot(0);
    	
    	equipSlot().equipItem(bp().grabItem());
    	((Weapon)equipSlot().getEquipped(ItemType.WEAPON)).setAttk(2);
    	((EnemyTestSprite)getMvmnt().getSprite()).getHpBar().updateProgress(stat(StatType.HP).getVal() / stat(StatType.HP).getMaxVal());
    	
    }

//GETTERS--------------------------------------------------------------------------
    
    public void hurt() {
    	/*
    	 * 
    	*/
    	
    	((EnemyTestSprite)getMvmnt().getSprite()).getHpBar().updateProgress(stat(StatType.HP).getVal() / stat(StatType.HP).getMaxVal());
    	
       	if (!isAlive()) {
       		getStage().getWorldSpace().getChildren().remove(getMvmnt().getSprite().getSpriteGroup());
       		getMvmnt().setSprite(new EnemyTestSprite());
       		setCharState(State.REST);
       	}
    }


//DISPLAY--------------------------------------------------------------------------

}
