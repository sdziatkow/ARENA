package animate;

/**
 * Program Name:    PlayerAnimate.java
 *<p>
 * Purpose:         The purpose of this program is to have a system for animating
 * 					the playable character.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.image.WritableImage;
import movement.Movement;
import sprite.charSprite.CharacterSprite;

public class PlayerAnimate extends Animate{
    /**
     *
    */


//CONSTRUCTORS---------------------------------------------------------------------

    public PlayerAnimate(CharacterSprite charSprite, Movement mvmnt) {
        /**
         * Default Constructor for class
        */
    	
    	super(charSprite, mvmnt);

    }

//ANIMATE--------------------------------------------------------------------------
    
	public void animate() {
		/**
		 * 
		*/
		if (getMvmnt().getDx() != 0 || getMvmnt().getDy() != 0) {
			getCharSprite().setSpriteImg(getNextFrame());
		}
		else {
			getCharSprite().setSpriteImg(getIdleFrame());
		}
	}
	
	public void animateAttk() {
		/*
		 * 
		*/
		
		getCharSprite().setSpriteImg(getNextAttkFrame());
	}

//GETTERS--------------------------------------------------------------------------

	public WritableImage getIdleFrame() {
		/**
		 * 
		*/
		
		// Will describe the next img to switch to.
		WritableImage idleFrame;
		
		// Set nextFrame based on direction.
		idleFrame = null;
		switch (getMvmnt().getDir()) {
		case 'w':
			idleFrame = getCharSprite().getUpSprite()[0];
			break;
			
		case 'a':
			idleFrame = getCharSprite().getLeftSprite()[0];
			break;
			
		case 's':
			idleFrame = getCharSprite().getDownSprite()[0];
			break;
			
		case 'd':
			idleFrame = getCharSprite().getRightSprite()[0];
			break;
			
		}
		
		return idleFrame;
		
	}
	
	private WritableImage getNextFrame() {
		/**
		 * This method will return the next image that the player's sprite will be
		 * changed to.
		*/
		
		// Will describe the next img to switch to.
		WritableImage nextFrame;
		
		// Set nextFrame based on direction and xFrameCount.
		nextFrame = null;
		switch (getMvmnt().getDir()) {
		
		//UP----------------
		case 'w':
			
			nextFrame = getCharSprite().getUpSprite()[getUCount()];
			incUCount();
			
			break;
		
		//LEFT--------------
		case 'a':

			nextFrame = getCharSprite().getLeftSprite()[getLCount()];
			incLCount();
			break;
		
		//DOWN------------------
		case 's':
			
			nextFrame = getCharSprite().getDownSprite()[getDCount()];
			incDCount();
			
			break;
			
		//RIGHT--------------------
		case 'd':
			
			nextFrame = getCharSprite().getRightSprite()[getRCount()];
			incRCount();
			
			break;
		}
		return nextFrame;
	}
	
	private WritableImage getNextAttkFrame() {
		/*
		 * 
		*/
		
		WritableImage nextFrame;
		nextFrame = null;
		
		// Set nextFrame based on direction and xFrameCount.
		nextFrame = null;
		switch (getMvmnt().getDir()) {
		
		//UP----------------
		case 'w':
			
			nextFrame = getCharSprite().getUpAttkSprite()[getAttkCount()];
			incAttkCount();
			
			break;
		
		//LEFT--------------
		case 'a':

			nextFrame = getCharSprite().getLeftAttkSprite()[getAttkCount()];
			incAttkCount();
			break;
		
		//DOWN------------------
		case 's':
			
			nextFrame = getCharSprite().getDownAttkSprite()[getAttkCount()];
			incAttkCount();
			
			break;
			
		//RIGHT--------------------
		case 'd':
			
			nextFrame = getCharSprite().getRightAttkSprite()[getAttkCount()];
			incAttkCount();
			
			break;
		}
		
		
		
		return nextFrame;
	}

}