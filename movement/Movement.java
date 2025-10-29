package movement;

import animate.Animate;

/**
 * Program Name:    Movement.java
 *<p>
 * Purpose:         The purpose of this program is to create a system that all 
 * 					moving world objects can use to move on the screen.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.ArenaCharacter;
import collision.CollisionBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.util.Duration;
import sprite.charSprite.CharacterSprite;
import worldStage.WorldStage;

public abstract class Movement{
    /**
     *
    */
	
	private final int BASE_ANIM_FRAME_RATE = 128;
	
	private WorldStage stage;
	private CharacterSprite sprite;
	private Animate anim;
	
	private double mvRate;
	private double dx;
	private double dy;
	private char direction;
	
	private int colBoxIndex;
	
	boolean contained = false;
	
	// Timelines hold the keyframes
	private Timeline mvAnim;
	private Timeline attkAnim;
	private KeyFrame mvFrame;
	private KeyFrame attkFrame;
	
	// Associated with Timeline or KeyFrame.
	private EventHandler<ActionEvent> onMvFrameFinish;
	private EventHandler<ActionEvent> onAttkFrameFinish;


//CONSTRUCTORS---------------------------------------------------------------------

    public Movement() {
        /**
         * Default Constructor for class Movement.
        */
    	
    	this.mvRate = 1.25;
    	dx = 0.0;
    	dy = 0.0;
    	
    	direction = 'd';
    }
    
    public Movement(
    		WorldStage stage, 
    		CharacterSprite sprite, 
    		Animate anim, 
    		double mvRate
    	){
        /**
         * Constructor for class Movement.
        */
    	
    	this.stage = stage;
    	this.sprite = sprite;
    	this.anim   = anim;
    	this.mvRate = mvRate;
    	dx = 0.0;
    	dy = 0.0;
    	
    	direction = 's';
    	
    	// Create event handler for when a movement frame is finished and for when
    	// an attack frame is finished.
    	onMvFrameFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * Call the animate method each time a frame ends.
    		*/
    		
    		public void handle(ActionEvent e) {
    			
    			boolean moving; 
    			
    			moving = (getDx() > 0.0 || getDx() < 0.0) ||
    					 (getDy() > 0.0 || getDy() < 0.0);
    			
    			getAnim().animate(getSprite(), getDir(), moving);
    		}
    	};
    	onAttkFrameFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * Call the animateAttk method each time a frame ends. 
    		*/
    		
    		public void handle(ActionEvent e) {
    			getAnim().animateAttk(getSprite(), getDir());
    		}
    	};
    	
    	// Create new KeyFrames for moving and attacking.
    	mvFrame = new KeyFrame(new Duration(BASE_ANIM_FRAME_RATE), onMvFrameFinish);
    	attkFrame = new KeyFrame(new Duration(BASE_ANIM_FRAME_RATE), onAttkFrameFinish);
    	
    	
    	
    	// Create Timelines for moving and attacking.
    	mvAnim = new Timeline(mvFrame);
    	attkAnim = new Timeline(attkFrame);
    	
    	// Set the cycleCount and onFinished handler for each Timeline.
    	mvAnim.setCycleCount(Timeline.INDEFINITE);
    	attkAnim.setCycleCount(getSprite().getFramesPerDir());
    }

//SETTERS--------------------------------------------------------------------------

    public void setMvRate(double mvRate) {
        /**
         * Setter for field: mvRate
        */
    	
    	this.mvRate = mvRate;
    }
    
    public void setDx(double dx) {
        /**
         * Setter for field: dx
        */
    	
    	this.dx = dx;
    }
    
    public void setDy(double dy) {
        /**
         * Setter for field: dy
        */

    	this.dy = dy;
    }
    
    public void setDir(char direction) {
        /**
         * Setter for field: direction
        */
    	
    	switch (direction) {
    	
    	case 'w':
    	case 'a':
    	case 's':
    	case 'd':
    		this.direction = direction;
    		break;
    		
    	default:
    		this.direction = 's';
    	}

    }
    
    public void setColBoxIndex(int colBoxIndex) {
        /**
         * Setter for field:
        */

    	this.colBoxIndex = colBoxIndex;
    }
    
    public void setStage(WorldStage stage) {
        /**
         * Setter for field: stage
        */
    	
    	this.stage = stage;
    }
    
    public void setSprite(CharacterSprite sprite) {
        /**
         * Setter for field: sprite
        */
    	
    	this.sprite = sprite;
    }
    
    public void setAnim(Animate anim) {
        /**
         * Setter for field: anim
        */
    	
    	this.anim = anim;
    }

//GETTERS--------------------------------------------------------------------------
    
    public WorldStage getStage() {
        /**
         * Getter for field: stage
        */
    	
    	return stage;
    }
    
    public CharacterSprite getSprite() {
        /**
         * Getter for field: sprite
        */

    	return sprite;
    }
    
    public Animate getAnim() {
        /**
         * Getter for field: anim
        */

    	return anim;
    }

    public double getMvRate() {
        /**
         * Getter for field: mvRate
        */

    	return mvRate;
    }
    
    public double getDx() {
        /**
         * Getter for field: dx
        */
    	
    	return dx;
    }
    
    public double getDy() {
        /**
         * Getter for field: dy
        */
    	
    	return dy;
    }
    
    public char getDir() {
        /**
         * Getter for field: direction
        */
    	
    	return direction;
    }
    
	public double getNormalX() {
		/**
		 * This method will return the correct value for dx when the player is 
		 * moving diagonally.
		*/
		
		double normalX;
		
		normalX = 1.0;
		if (getSpeed() > 0) {
			
			if (mvRate / getSpeed() > 0) {
				
				normalX = mvRate / getSpeed();
			}
		}
		
		return normalX;
	}
	
	public double getNormalY() {
		/**
		 * This method will return the correct value for dx when the player is 
		 * moving diagonally.
		*/
		
		double normalY;
		
		normalY = 1.0;
		if (getSpeed() > 0) {
			
			if (mvRate / getSpeed() > 0) {
				
				normalY = mvRate / getSpeed();
			}
		}
		
		return normalY;
	}
	
	public double getSpeed() {
		/**
		 * This method will return the value at which the player is moving 
		 * diagonally.
		*/
		
		double speed;
		
		speed = Math.sqrt( Math.pow(mvRate, 2) + Math.pow(mvRate, 2) );
	
		if (!(speed > 0 || speed < 0)) {
			speed = 1.0;
		}
		
		return speed;
	}
	
    public int getColBoxIndex() {
        /**
         * Getter for field:
        */
    	
    	return colBoxIndex;

    }
    
    public boolean getContained() {
        /**
         * Getter for field:
        */

    	return contained;
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
    
//MOVEMENT-------------------------------------------------------------------------
    
    public abstract void move();
    
	public void checkCollision() {
		/**
		 * 
		*/
		
		
		// Character's current worldBox bounds.
		Bounds charBounds = getSprite().getWorldBox().getBounds();
		
		// Will store the worldBox bounds of the worldBox being checked.
		Bounds checkBounds;
		
		// Character's current center, min, and max X/Y values.
		double currX = getSprite().getWorldBox().getMidX();
//		double currY = getSprite().getWorldBox().getMidY();
		double maxX  = getSprite().getWorldBox().getMaxX();
		double maxY  = getSprite().getWorldBox().getMaxY();
		double minX  = getSprite().getWorldBox().getMinX();
		double minY  = getSprite().getWorldBox().getMinY();
		
		// Will store the center, min, and max X/Y values of the worldBox being checked.
		double checkX;
//		double checkY;
		double checkMaxX;
		double checkMaxY;
		double checkMinX;
		double checkMinY;
		
		// An array containing all worldBoxes in the character's stage.
		CollisionBox[] worldBoxes = getStage().getWorldBoxes();
		int totalWorldBoxes = worldBoxes.length;
		
		// The modifier that the Character will be pushed back upon colliding with 
		// another worldBox.
		double bounce = 0.1;
		
		// For intersection purposes allows greater detection range.
		double pad = 3.0;
		
		for (int box = 0; box < totalWorldBoxes && !contained; ++box) {
			
			if (box != getColBoxIndex()) {
			
				checkBounds = worldBoxes[box].getBounds();
				checkX = worldBoxes[box].getMidX();
//				checkY = worldBoxes[box].getMidY();
				
				checkMaxX = worldBoxes[box].getMaxX();
				checkMaxY = worldBoxes[box].getMaxY();
				
				checkMinX = worldBoxes[box].getMinX();
				checkMinY = worldBoxes[box].getMinY();
				
				if (charBounds.intersects(checkBounds)) {
					contained = true;
				}
				
				if (contained) {
					
					boolean w;
					boolean a;
					boolean s;
					boolean d;
					
					// Will determine where the intersection is occuring.
					w = (minY + pad) > checkMaxY;                     // up
					a = (currX > checkX && (minX + pad) > checkMaxX); // left
					s = (maxY - pad) < checkMinY;                     // down
					d = currX < checkX && (maxX - pad) < checkMinX;   // right
					
					if (
						(getDx() > 0.0 && d) 
						&&
						(getDy() > 0.0 && s)
						) { // right - down
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (
							(getDx() > 0.0 && d) 
							&&
							(getDy() < 0.0 && w)
							) { // right - up
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (
							(getDx() < 0.0 && a)
							&&
							(getDy() > 0.0 && s)
							) { // left - down
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (
							(getDx() < 0.0 && a)
							&&
							(getDy() < 0.0 && w)
							) { // left - up
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (getDx() > 0.0 && d) { // right
						setDx(-getDx() * bounce);
					}
					else if (getDx() < 0.0 && a) { //left
						setDx(-getDx() * bounce);
					}
					else if (getDy() < 0.0 && w) { //up
						setDy(-getDy() * bounce);
					}
					else if (getDy() > 0.0 && s) { //down
						setDy(-getDy() * bounce);
					}
				}
			}
			
			contained = false;
		}
	}
	
}