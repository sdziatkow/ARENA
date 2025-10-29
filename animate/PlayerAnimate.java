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
import sprite.charSprite.CharacterSprite;

public class PlayerAnimate extends Animate{
    /**
     *
    */


//CONSTRUCTORS---------------------------------------------------------------------

    public PlayerAnimate(int framesPerDir) {
        /**
         * Default Constructor for class
        */
    	
    	super(framesPerDir);

    }

//ANIMATE--------------------------------------------------------------------------
    
	public void animate(CharacterSprite sprite, char dir, boolean move) {
		/**
		 * 
		*/
		if (move) {
			sprite.setSpriteImg(getNextFrame(sprite, dir));
		}
		else {
			sprite.setSpriteImg(getIdleFrame(sprite, dir));
		}
	}
	
	public void animateAttk(CharacterSprite sprite, char dir) {
		/*
		 * 
		*/
		
		sprite.setSpriteImg(getNextAttkFrame(sprite, dir));
	}

//GETTERS--------------------------------------------------------------------------

	public WritableImage getIdleFrame(CharacterSprite sprite, char dir) {
		/**
		 * 
		*/
		
		// Will describe the next img to switch to.
		WritableImage idleFrame;
		
		// Set nextFrame based on direction.
		idleFrame = null;
		switch (dir) {
		case 'w':
			idleFrame = sprite.getUpSprite()[0];
			break;
			
		case 'a':
			idleFrame = sprite.getLeftSprite()[0];
			break;
			
		case 's':
			idleFrame = sprite.getDownSprite()[0];
			break;
			
		case 'd':
			idleFrame = sprite.getRightSprite()[0];
			break;
			
		}
		
		return idleFrame;
		
	}
	
	private WritableImage getNextFrame(CharacterSprite sprite, char dir) {
		/**
		 * This method will return the next image that the player's sprite will be
		 * changed to.
		*/
		
		// Will describe the next img to switch to.
		WritableImage nextFrame;
		
		// Set nextFrame based on direction and xFrameCount.
		nextFrame = null;
		switch (dir) {
		
		//UP----------------
		case 'w':
			
			nextFrame = sprite.getUpSprite()[getUCount()];
			incUCount();
			
			break;
		
		//LEFT--------------
		case 'a':

			nextFrame = sprite.getLeftSprite()[getLCount()];
			incLCount();
			break;
		
		//DOWN------------------
		case 's':
			
			nextFrame = sprite.getDownSprite()[getDCount()];
			incDCount();
			
			break;
			
		//RIGHT--------------------
		case 'd':
			
			nextFrame = sprite.getRightSprite()[getRCount()];
			incRCount();
			
			break;
		}
		return nextFrame;
	}
	
	private WritableImage getNextAttkFrame(CharacterSprite sprite, char dir) {
		/*
		 * 
		*/
		
		WritableImage nextFrame;
		nextFrame = null;
		
		// Set nextFrame based on direction and xFrameCount.
		nextFrame = null;
		switch (dir) {
		
		//UP----------------
		case 'w':
			
			nextFrame = sprite.getUpAttkSprite()[getAttkCount()];
			incAttkCount();
			
			break;
		
		//LEFT--------------
		case 'a':

			nextFrame = sprite.getLeftAttkSprite()[getAttkCount()];
			incAttkCount();
			break;
		
		//DOWN------------------
		case 's':
			
			nextFrame = sprite.getDownAttkSprite()[getAttkCount()];
			incAttkCount();
			
			break;
			
		//RIGHT--------------------
		case 'd':
			
			nextFrame = sprite.getRightAttkSprite()[getAttkCount()];
			incAttkCount();
			
			break;
		}
		
		
		
		return nextFrame;
	}

}