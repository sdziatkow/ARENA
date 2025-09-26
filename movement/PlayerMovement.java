package movement;

import arenaCharacter.ArenaCharacter;
import arenaCharacter.Player;
import javafx.scene.Group;
import sprite.charSprite.CharacterSprite;
import sprite.charSprite.PlayerSprite;

/**
 * Program Name:    PlayerMovement.java
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
    
//	public void checkCollision() {
//		/**
//		 * 
//		*/
//		
//		double pX = getChar().getStage().getWorldBoxes()[0].getMinX();
//		double pY = getChar().getStage().getWorldBoxes()[0].getMinY();
//		double pW = getChar().getStage().getWorldBoxes()[0].getMaxX();
//		double pH = getChar().getStage().getWorldBoxes()[0].getMaxY();
//		
//		double x;
//		double y;
//		double w;
//		double h;
//		
//		int amntBoxes = getChar().getStage().getWorldBoxes().length;
//		
//		for (int box = 1; box < amntBoxes; ++box) {
//		
//			x = getChar().getStage().getWorldBoxes()[box].getMinX();
//			y = getChar().getStage().getWorldBoxes()[box].getMinY();
//			w = getChar().getStage().getWorldBoxes()[box].getMaxX();
//			h = getChar().getStage().getWorldBoxes()[box].getMaxY();
//			
//			// Check right
//			if (
//				pW > x && 
//				pW < w &&
//				pY >= y &&
//				pH <= h &&
//				getDx() >= 0
//			) {
//				setDx(0.0);
//			}
//			
//			// Check left
//			if (
//				pX < w && 
//				pX > x &&
//				pY >= y &&
//				pH <= h &&
//				getDx() <= 0
//				) {
//					setDx(0.0);
//			}
//			
//			// Check up
//			if (
//				pY < h &&
//				pY > y &&
//				pX >= x &&
//				pW <= w &&
//				getDy() <= 0
//				) {
//					setDy(0.0);
//			}
//			
//			// Check down.
//			if (
//				pH > y && 
//				pH < h &&
//				pX >= x &&
//				pW <= w &&
//				getDy() >= 0
//				) {
//					setDy(0.0);
//				}
//		
//		}
//		
//	}

}