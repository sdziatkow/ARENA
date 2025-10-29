package movement;

import animate.PlayerAnimate;
import javafx.animation.Animation;

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

import sprite.charSprite.PlayerSprite;
import window.Controller;
import worldStage.WorldStage;

public class PlayerMovement extends Movement{
    /**
     *
    */
	
	private Controller cntrl;


//CONSTRUCTORS---------------------------------------------------------------------

    public PlayerMovement() {
        /**
         * Default Constructor for class PlayerMovement.
        */
    	
    	super();
    }
    
    public PlayerMovement(
    		WorldStage stage,
    		PlayerSprite sprite, 
    		PlayerAnimate anim,
    		Controller cntrl,
    		double mvRate
    	){
        /**
         * Default Constructor for class PlayerMovement.
        */
    	
    	super(stage, sprite, anim, mvRate);
    	this.cntrl = cntrl;

    }
    
//SETTERS--------------------------------------------------------------------------
    
    public void setCntrl(Controller cntrl) {
    	/*
    	 * 
    	*/
    	
    	this.cntrl = cntrl;
    }
    
//GETTERS--------------------------------------------------------------------------
    
//MOVEMENT-------------------------------------------------------------------------
    
    public void move() {
    	/**
    	 * 
    	*/
    	
		// For setDir.
		if (cntrl.getADown()) {
			setDir('a');
		}
		else if(cntrl.getDDown()) {
			setDir('d');
		}
		else if (cntrl.getWDown()) {
			setDir('w');
		}
		else if(cntrl.getSDown()) {
			setDir('s');
		}
    	
		// Player does not move when no keys are not pressed.
		if (!cntrl.getWDown() && !cntrl.getSDown()) {
			setDy(0.0);
		}
		if (!cntrl.getADown() && !cntrl.getDDown()) {
			setDx(0.0);
		}
		
		// Set dx, dy dependent on which keys are pressed down.
		if (cntrl.getWDown() && cntrl.getADown()) {
			setDx( -getMvRate() * getNormalX() );
			setDy( -getMvRate() * getNormalY() );
		}
		else if (cntrl.getWDown() && cntrl.getDDown()) {
			setDx(  getMvRate() * getNormalX() );
			setDy( -getMvRate() * getNormalY() );
		}
		else if (cntrl.getSDown() && cntrl.getADown()) {
			setDx( -getMvRate() * getNormalX() );
			setDy(  getMvRate() * getNormalY() );
		}
		else if (cntrl.getSDown() && cntrl.getDDown()) {
			setDx( getMvRate() * getNormalX() );
			setDy( getMvRate() * getNormalY() );
		}
		else if (cntrl.getWDown()) {
			setDx(0.0);
			setDy( -getMvRate() );
		}
		else if (cntrl.getADown()) {
			setDx( -getMvRate() );
			setDy(0.0);
		}
		else if (cntrl.getSDown()) {
			setDx(0.0);
			setDy( getMvRate() );
		}
		else if (cntrl.getDDown()) {
			setDx( getMvRate() );
			setDy(0.0);
		}
		
		checkCollision();

		// Move the getChar() group by translation
		getSprite().getSpriteGroup().setTranslateY(
				getSprite().getSpriteGroup().getTranslateY() + getDy()
				)
		;
		getSprite().getSpriteGroup().setTranslateX(
				getSprite().getSpriteGroup().getTranslateX() + getDx()
				)
		;
		
		if (!getMvAnim().getStatus().equals(Animation.Status.RUNNING)) {
			getMvAnim().play();
		}
    	
    }
}