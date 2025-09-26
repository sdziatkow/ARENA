package animate;

import arenaCharacter.ArenaCharacter.State;
import movement.Movement;
import sprite.charSprite.CharacterSprite;

/**
 * Program Name:    Animate.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         MONTH DD, YYYY
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/


public abstract class Animate{
    /**
     *
    */
	
	private CharacterSprite charSprite;
	private Movement mvmnt;
	
	private int uFrameCount;
	private int lFrameCount;
	private int dFrameCount;
	private int rFrameCount;
	
	private int attkFrameCount;
	
	private int maxFramePerDir;


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
			getMvmnt().getChar().setCharState(State.MOVE);
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
    
    public void get() {
        /**
         * Getter for field:
        */

    }

//
    
    public abstract void animate();
    public abstract void animateAttk();

}