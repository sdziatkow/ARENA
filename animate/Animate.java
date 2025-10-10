package animate;

/**
 * Program Name:    Animate.java
 *<p>
 * Purpose:         The purpose of this program is to have a general system for 
 * 					handling animations of all types.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.ArenaCharacter.State;
import movement.Movement;
import sprite.charSprite.CharacterSprite;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public abstract class Animate{
    /**
     *
    */
	
	// Base time for each KeyFrame to take.
	private final int BASE_FRAME_RATE = 128;
	
	private CharacterSprite charSprite;
	private Movement mvmnt;
	
	private int uFrameCount;
	private int lFrameCount;
	private int dFrameCount;
	private int rFrameCount;

	private int attkFrameCount;
	private int maxFramePerDir;
	
	// Timelines hold the keyframes
	private Timeline mvAnim;
	private Timeline attkAnim;
	private KeyFrame mvFrame;
	private KeyFrame attkFrame;
	
	// Associated with Timeline or KeyFrame.
	private EventHandler<ActionEvent> onMvFrameFinish;
	private EventHandler<ActionEvent> onAttkFinish;
	private EventHandler<ActionEvent> onAttkFrameFinish;
	
	
	


//CONSTRUCTORS---------------------------------------------------------------------

    public Animate(CharacterSprite charSprite, Movement mvmnt) {
        /**
         * Default Constructor for class
        */
    	
    	this.charSprite = charSprite;
    	this.mvmnt = mvmnt;
    	
    	uFrameCount = 0;
    	lFrameCount = 0;
    	dFrameCount = 0;
    	rFrameCount = 0;
    	
    	attkFrameCount = 0;
    	maxFramePerDir = charSprite.getFramesPerDir() - 1;
    	
    	onMvFrameFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * Call the animate method each time a frame ends.
    		*/
    		
    		public void handle(ActionEvent e) {
    			animate();
    		}
    	};
    	onAttkFrameFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * Call the animateAttk method each time a frame ends. 
    		*/
    		
    		public void handle(ActionEvent e) {
    			animateAttk();
    		}
    	};
    	onAttkFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * This is called when attkAnim has passed through maxFramesPerDir
    		 * KeyFrames.
    		 * This method removes the hitbox from ArenaCharacter's Group and sets
    		 * their state to MOVE.
    		*/
    		
    		public void handle(ActionEvent e) {
    			charSprite.getSpriteGroup().getChildren().remove(charSprite.getHitBox().getColBox());
    			mvmnt.getChar().setCharState(State.MOVE);
    		}
    	};
    	
    	// Create new KeyFrames for moving and attacking.
    	mvFrame = new KeyFrame(new Duration(BASE_FRAME_RATE), onMvFrameFinish);
    	attkFrame = new KeyFrame(new Duration(BASE_FRAME_RATE), onAttkFrameFinish);
    	
    	// Create Timelines for moving and attacking.
    	mvAnim = new Timeline(mvFrame);
    	attkAnim = new Timeline(attkFrame);
    	
    	// Set the cycleCount and onFinished handler for each Timeline.
    	mvAnim.setCycleCount(Timeline.INDEFINITE);
    	attkAnim.setCycleCount(maxFramePerDir);
    	attkAnim.setOnFinished(onAttkFinish);
    }

//SETTERS--------------------------------------------------------------------------

    public void set() {
        /**
         * Setter for field:
        */

    }
    
    public void incUCount() {
    	/**
    	 * 
    	*/
    	
		++uFrameCount;
		
		if (uFrameCount >= maxFramePerDir) {
			uFrameCount = 0;
		}
    }
    
    public void incLCount() {
    	/**
    	 * 
    	*/
    	
		++lFrameCount;
		
		if (lFrameCount >= maxFramePerDir) {
			lFrameCount = 0;
		}
    }
    
    public void incDCount() {
    	/**
    	 * 
    	*/
    	
		++dFrameCount;
		
		if (dFrameCount >= maxFramePerDir) {
			dFrameCount = 0;
		}
    }
    
    public void incRCount() {
    	/**
    	 * 
    	*/
    	
		++rFrameCount;
		
		if (rFrameCount >= maxFramePerDir) {
			rFrameCount = 0;
		}
    }
    
    public void incAttkCount() {
    	/**
    	 * 
    	*/
    	
		++attkFrameCount;
		
		if (attkFrameCount >= maxFramePerDir) {
			attkFrameCount = 0;
		}
    }

//GETTERS--------------------------------------------------------------------------
    
    public CharacterSprite getCharSprite() {
    	/**
    	 * 
    	*/
    	
    	return charSprite;
    }
    
    public Movement getMvmnt() {
    	/**
    	 * 
    	*/
    	
    	return mvmnt;
    }

    public int getUCount() {
        /**
         * Getter for field:
        */

    	return uFrameCount;
    }
    
    public int getLCount() {
        /**
         * Getter for field:
        */

    	return lFrameCount;
    }
    
    public int getDCount() {
        /**
         * Getter for field:
        */

    	return dFrameCount;
    }
    
    public int getRCount() {
        /**
         * Getter for field:
        */

    	return rFrameCount;
    }
    
    public int getAttkCount() {
    	/*
    	 * 
    	*/
    	
    	return attkFrameCount;
    }
    
    public Timeline getMvAnim() {
        /**
         * Getter for field:
        */
    	
    	return mvAnim;
    }
    
    public Timeline getAttkAnim() {
        /**
         * Getter for field:
        */
    	
    	return attkAnim;
    }

//
    
    public abstract void animate();
    public abstract void animateAttk();

}