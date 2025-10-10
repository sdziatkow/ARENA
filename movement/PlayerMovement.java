package movement;

/**
 * Program Name:    PlayerMovement.java
 *<p>
 * Purpose:         The purpose of this program is to create a system for the
 * 					playable character to move on the screen.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.Player;

public class PlayerMovement extends Movement{
    /**
     *
    */


//CONSTRUCTORS---------------------------------------------------------------------

    public PlayerMovement() {
        /**
         * Default Constructor for class
        */
    	
    	super();
    }
    
    public PlayerMovement(Player player) {
        /**
         * Default Constructor for class
        */
    	
    	super(player, 1.25);
    }
    
    public PlayerMovement(Player player, double mvRate) {
        /**
         * Default Constructor for class
        */
    	
    	super(player, mvRate);

    }
    
//GETTERS--------------------------------------------------------------------------
    
//    public Player getChar() {
//    	/**
//    	 * 
//    	*/
//    	
//    	return (Player)getChar();
//    }
    
//MOVEMENT-------------------------------------------------------------------------
    
    public void move() {
    	/**
    	 * 
    	*/
    	
		// For setDir(
		if (getChar().getCntrl().getADown()) {
			setDir('a');
		}
		else if(getChar().getCntrl().getDDown()) {
			setDir('d');
		}
		else if (getChar().getCntrl().getWDown()) {
			setDir('w');
		}
		else if(getChar().getCntrl().getSDown()) {
			setDir('s');
		}
    	
		// Player does not move when no keys are not pressed.
		if (!getChar().getCntrl().getWDown() && !getChar().getCntrl().getSDown()) {
			setDy(0.0);
		}
		if (!getChar().getCntrl().getADown() && !getChar().getCntrl().getDDown()) {
			setDx(0.0);
		}
		
		// Set dx, dy dependent on which keys are pressed down.
		if (getChar().getCntrl().getWDown() && getChar().getCntrl().getADown()) {
			setDx( -getMvRate() * getNormalX() );
			setDy( -getMvRate() * getNormalY() );
		}
		else if (getChar().getCntrl().getWDown() && getChar().getCntrl().getDDown()) {
			setDx(  getMvRate() * getNormalX() );
			setDy( -getMvRate() * getNormalY() );
		}
		else if (getChar().getCntrl().getSDown() && getChar().getCntrl().getADown()) {
			setDx( -getMvRate() * getNormalX() );
			setDy(  getMvRate() * getNormalY() );
		}
		else if (getChar().getCntrl().getSDown() && getChar().getCntrl().getDDown()) {
			setDx( getMvRate() * getNormalX() );
			setDy( getMvRate() * getNormalY() );
		}
		else if (getChar().getCntrl().getWDown()) {
			setDx(0.0);
			setDy( -getMvRate() );
		}
		else if (getChar().getCntrl().getADown()) {
			setDx( -getMvRate() );
			setDy(0.0);
		}
		else if (getChar().getCntrl().getSDown()) {
			setDx(0.0);
			setDy( getMvRate() );
		}
		else if (getChar().getCntrl().getDDown()) {
			setDx( getMvRate() );
			setDy(0.0);
		}
		
		checkCollision();

		// Move the getChar() group by translation
		getChar().getSprite().getSpriteGroup().setTranslateY(
				getChar().getSprite().getSpriteGroup().getTranslateY() + getDy()
				)
		;
		getChar().getSprite().getSpriteGroup().setTranslateX(
				getChar().getSprite().getSpriteGroup().getTranslateX() + getDx()
				)
		;
    	
    }
}