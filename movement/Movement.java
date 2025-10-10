package movement;

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
import javafx.geometry.Bounds;

public abstract class Movement{
    /**
     *
    */
	
	private ArenaCharacter arenaChar;
	
	private double mvRate;
	private double dx;
	private double dy;
	private char direction;
	
	private int colBoxIndex;
	
	boolean contained = false;


//CONSTRUCTORS---------------------------------------------------------------------

    public Movement() {
        /**
         * Default Constructor for class
        */
    	
    	mvRate = 1.25;
    	dx = 0.0;
    	dy = 0.0;
    	
    	direction = 'd';
    }
    
    public Movement(ArenaCharacter arenaChar, double mvRate) {
        /**
         * Constructor for class
        */
    	
    	this.arenaChar = arenaChar;
    	this.mvRate = mvRate;
    	dx = 0.0;
    	dy = 0.0;
    	
    	direction = 'd';
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
    		this.direction = 'd';
    	}

    }
    
    public void setColBoxIndex(int colBoxIndex) {
        /**
         * Setter for field:
        */

    	this.colBoxIndex = colBoxIndex;
    }
    
    public void set() {
        /**
         * Setter for field:
        */

    }

//GETTERS--------------------------------------------------------------------------
    
    public ArenaCharacter getChar() {
        /**
         * Getter for field: arenaChar
        */

    	return arenaChar;
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
    
//MOVEMENT-------------------------------------------------------------------------
    
    public abstract void move();
    
	public void checkCollision() {
		/**
		 * 
		*/
		
		
		// Character's current worldBox bounds.
		Bounds charBounds = getChar().getSprite().getWorldBox().getBounds();
		
		// Will store the worldBox bounds of the worldBox being checked.
		Bounds checkBounds;
		
		// Character's current center, min, and max X/Y values.
		double currX = getChar().getSprite().getWorldBox().getMidX();
		double currY = getChar().getSprite().getWorldBox().getMidY();
		double maxX  = getChar().getSprite().getWorldBox().getMaxX();
		double maxY  = getChar().getSprite().getWorldBox().getMaxY();
		double minX  = getChar().getSprite().getWorldBox().getMinX();
		double minY  = getChar().getSprite().getWorldBox().getMinY();
		
		// Will store the center, min, and max X/Y values of the worldBox being checked.
		double checkX;
		double checkY;
		double checkMaxX;
		double checkMaxY;
		double checkMinX;
		double checkMinY;
		
		// An array containing all worldBoxes in the character's stage.
		CollisionBox[] worldBoxes = getChar().getStage().getWorldBoxes();
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
				checkY = worldBoxes[box].getMidY();
				
				checkMaxX = worldBoxes[box].getMaxX();
				checkMaxY = worldBoxes[box].getMaxY();
				
				checkMinX = worldBoxes[box].getMinX();
				checkMinY = worldBoxes[box].getMinY();
				
				if (charBounds.intersects(checkBounds)) {
					contained = true;
				}
				
				if (contained) {
					
					if (
						(getDx() > 0.0 && (currX < checkX && (maxX - pad) < checkMinX)) 
						&&
						(getDy() > 0.0 && ( (maxY - pad) < checkMinY))
						) { // right - down
						
						// go up - left
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (
							(getDx() > 0.0 && (currX < checkX && (maxX - pad) < checkMinX)) 
							&&
							(getDy() < 0.0 && ( (minY + pad) > checkMaxY))
							) { // right - up
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (
							(getDx() < 0.0 && (currX > checkX && (minX + pad) > checkMaxX))
							&&
							(getDy() > 0.0 && ( (maxY - pad) < checkMinY))
							) { // left - down
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (
							(getDx() < 0.0 && (currX > checkX && (minX + pad) > checkMaxX))
							&&
							(getDy() < 0.0 && ( (minY + pad) > checkMaxY))
							) { // left - up
						setDx(-getDx() * bounce);
						setDy(-getDy() * bounce);
					}
					else if (getDx() > 0.0 && (currX < checkX && (maxX - pad) < checkMinX)) { // right
						setDx(-getDx() * bounce);
					}
					else if (getDx() < 0.0 && (currX > checkX && (minX + pad) > checkMaxX)) { //left
						setDx(-getDx() * bounce);
					}
					else if (getDy() < 0.0 && ( (minY + pad) > checkMaxY)) { //up
						setDy(-getDy() * bounce);
					}
					else if (getDy() > 0.0 && ( (maxY - pad) < checkMinY)) { //down
						setDy(-getDy() * bounce);
					}
				}
			}
			
			contained = false;
		}
	}
	
}