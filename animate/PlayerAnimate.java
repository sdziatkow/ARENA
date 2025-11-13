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
import movement.Movement.Going;
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
    
	public void animate(CharacterSprite sprite, Going dir, boolean move) {
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
	
	public void animateAttk(CharacterSprite sprite, Going dir) {
		/*
		 * 
		*/
		
		sprite.setSpriteImg(getNextAttkFrame(sprite, dir));
	}

//GETTERS--------------------------------------------------------------------------

	public WritableImage getIdleFrame(CharacterSprite sprite, Going dir) {
		/**
		 * 
		*/
		
		// Will describe the next img to switch to.
		WritableImage idleFrame;
		
		// Set nextFrame based on direction.
		idleFrame = null;

		if (dir == Going.N) {
			idleFrame = sprite.getUpSprite()[0];
		}
		else if (dir == Going.W) {
			idleFrame = sprite.getLeftSprite()[0];
		}
		else if (dir == Going.S) {
			idleFrame = sprite.getDownSprite()[0];
		}
		else if (dir == Going.E) {
			idleFrame = sprite.getRightSprite()[0];
		}
		
		return idleFrame;
		
	}
	
	private WritableImage getNextFrame(CharacterSprite sprite, Going dir) {
		/**
		 * This method will return the next image that the player's sprite will be
		 * changed to.
		*/
		
		// Will describe the next img to switch to.
		WritableImage nextFrame;
		
		// Set nextFrame based on direction and xFrameCount.
		nextFrame = null;
			
		if (dir == Going.N) {
			nextFrame = sprite.getUpSprite()[getUCount()];
			incUCount();
		}
		else if (dir == Going.W) {
			nextFrame = sprite.getLeftSprite()[getLCount()];
			incLCount();
		}
		else if (dir == Going.S) {
			nextFrame = sprite.getDownSprite()[getDCount()];
			incDCount();
		}
		else if (dir == Going.E) {
			nextFrame = sprite.getRightSprite()[getRCount()];
			incRCount();	
		}
		return nextFrame;
	}
	
	private WritableImage getNextAttkFrame(CharacterSprite sprite, Going dir) {
		/*
		 * 
		*/
		
		WritableImage nextFrame;
	
		// Set nextFrame based on direction and xFrameCount.
		nextFrame = null;
		if (dir == Going.N) {
			nextFrame = sprite.getUpAttkSprite()[getAttkCount()];
			sprite.getWeaponSprite().setSpriteImg(sprite.getWeaponSprite().getUpSprite()[getAttkCount()]);
			incAttkCount();
		}
		else if (dir == Going.W) {

			nextFrame = sprite.getLeftAttkSprite()[getAttkCount()];
			sprite.getWeaponSprite().setSpriteImg(sprite.getWeaponSprite().getLeftSprite()[getAttkCount()]);
			incAttkCount();
		}
		else if (dir == Going.S) {
			nextFrame = sprite.getDownAttkSprite()[getAttkCount()];
			sprite.getWeaponSprite().setSpriteImg(sprite.getWeaponSprite().getDownSprite()[getAttkCount()]);
			incAttkCount();
		}
		else if (dir == Going.E) {
			nextFrame = sprite.getRightAttkSprite()[getAttkCount()];
			sprite.getWeaponSprite().setSpriteImg(sprite.getWeaponSprite().getRightSprite()[getAttkCount()]);
			incAttkCount();
		}
		
		return nextFrame;
	}

}