package movement;

/**
 * Program Name:    NpcMovement.java
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

import java.util.Random;

import arenaCharacter.npc.Npc;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class NpcMovement extends Movement{
    /**
     *
    */

	private Random mvGen;
	private int mv;
	private int stepsPerDir;
	private TranslateTransition moveX;
	private TranslateTransition moveY;

//CONSTRUCTORS---------------------------------------------------------------------

    public NpcMovement(Npc npc) {
        /**
         * Default Constructor for class
        */

    	super(npc, 3.25);
    	mvGen = new Random();
    	
    	moveX = new TranslateTransition();
    	moveX.setNode(npc.getSprite().getSpriteGroup());
    	
    	moveY = new TranslateTransition();
    	moveY.setNode(npc.getSprite().getSpriteGroup());
    }
    
    public NpcMovement(Npc npc, double mvRate) {
    	/**
    	 * 
    	*/
    	
    	super(npc, mvRate);
    	mvGen = new Random();
    	
    	moveX = new TranslateTransition();
    	moveX.setNode(npc.getSprite().getSpriteGroup());
    	moveX.setDuration(Duration.millis(npc.getSprite().getFramesPerDir()));
    	moveX.setCycleCount(npc.getSprite().getFramesPerDir());
    	moveX.setByX(getDx());
    	
    	moveY = new TranslateTransition();
    	moveY.setNode(npc.getSprite().getSpriteGroup());
    	moveY.setDuration(Duration.millis(npc.getSprite().getFramesPerDir()));
    	moveY.setCycleCount(npc.getSprite().getFramesPerDir());
    	moveY.setByY(getDy());
    }

//SETTERS--------------------------------------------------------------------------

    public void set() {
        /**
         * Setter for field:
        */

    }

//GETTERS--------------------------------------------------------------------------

    public void get() {
        /**
         * Getter for field:
        */

    }

//
    
    public void move() {
    	/**
    	 * 
    	*/
    	
    	mv = mvGen.nextInt(5);
    	
    	switch (mv) {
    	
    	default:
    	case 0:
    		setDir('w');
    		setDx(0.0);
    		setDy(-getMvRate());
    		break;
    		
    	case 1:
    		setDir('a');
    		setDx(-getMvRate());
    		setDy(0.0);
    		break;
    		
    	case 2:
    		setDir('s');
    		setDx(0.0);
    		setDy(getMvRate());
    		break;
    		
    	case 3:
    		setDir('d');
    		setDx(getMvRate());
    		setDy(0.0);
    		break;
    		
    	case 4:
    		setDir('s');
    		setDx(0.0);
    		setDy(0.0);
    		break;
    	}
    	
    	checkCollision();
    	
		moveX.setFromX(getChar().getSprite().getSpriteGroup().getTranslateX());
		moveX.setToX(getChar().getSprite().getSpriteGroup().getTranslateX() + getDx());
		
		moveY.setFromY(getChar().getSprite().getSpriteGroup().getTranslateY());
		moveY.setToY(getChar().getSprite().getSpriteGroup().getTranslateY() + getDy());
    	
    	moveX.play();
    	moveY.play();
    	
    }

}