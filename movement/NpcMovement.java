package movement;

/**
 * Program Name:    NpcMovement.java
 *<p>
 * Purpose:         The purpose of this program is to create a system for
 * 					non-playable characters to move.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import java.util.Random;

import animate.NpcAnimate;
import collision.CollisionBox;
import javafx.animation.Animation;
import javafx.geometry.Bounds;
import sprite.charSprite.CharacterSprite;
import worldStage.WorldStage;

public class NpcMovement extends Movement{
    /**
     *
    */
	
	// Random number generated from mvGen stored into mv.
	private Random mvGen;
	private int mv;
	
	// Increment mvRate plus incMvrate until decreased to minimum 0.0 or exceeds
	// maxMvRate.
	private double incMvRate;
	private double maxMvRate;
	
	// If true then state will be set to attack.
	boolean attkHurtBox;
	
	// Will be used to determine when Npc should move around a worldBox.
	int cantMvToCnt;
	boolean canMvTo;
	
	// For random movement.
	private int tempDirCount;

//CONSTRUCTORS---------------------------------------------------------------------
	
    public NpcMovement() {
        /**
         * Default Constructor for class NpcMovement
        */
    	
    	super();
    	attkHurtBox = false;
    	cantMvToCnt = 0;
    	canMvTo = true;
    	genMv();
    }

    public NpcMovement(
    		WorldStage stage, 
    		CharacterSprite sprite, 
    		NpcAnimate anim, 
    		double mvRate
    	){
        /**
         * Constructor for class NpcMovement.
        */

    	super(stage, sprite, anim, mvRate);
    	setMvRate(1.25);
    	setColBounce(1.0);
    	mvGen = new Random();
    	
    	maxMvRate = getMvRate();
    	incMvRate = getMvRate() / (getMvRate() / 2.0 );
    	attkHurtBox = false;
    	
    	tempDirCount = 0;
    	cantMvToCnt = 0;
    	canMvTo = true;
    	genMv();
    }

//SETTERS--------------------------------------------------------------------------
    
    public void toggleAttkHurtBox() {
        /**
         * Getter for field: attkHurtBox.
        */
    	
    	attkHurtBox = !attkHurtBox;
    }

//GETTERS--------------------------------------------------------------------------
    
    public boolean getAttkHurtBox() {
        /**
         * Getter for field: attkHurtBox.
        */
    	
    	return attkHurtBox;
    }
    
    public int genMv() {
        /**
         * Will generate and return a random positive integer between 0-49.
        */
    	
    	int gen = Math.abs((mvGen.nextInt())) % 50;
    	
    	return gen;
    }

//MOVEMENT-------------------------------------------------------------------------
    
    public void move() {
    	/*
    	 * This method will check if the player is within detection range and
    	 * move the NPC accordingly.
    	*/
    	
    	// Check if any hurtBoxes are in range of Sprite's detectBox.
    	CollisionBox npcBox = getSprite().getDetectBox();
    	CollisionBox[] hurtBoxes = getStage().getHurtBoxes();
    	
    	// Iterate through all hurtBoxes on the stage or until mvRand is false.
    	boolean mvRand = true;
    	int boxNum = -1;
    	for (int b = 0; b < hurtBoxes.length && mvRand; ++b) {
    		
    		// Not checking own hurtbox and a hurtBox is contained within this
    		// Sprite's detectBox.
        	if (
        		b != getColBoxIndex() && 
        		npcBox.getBounds().contains(hurtBoxes[b].getBounds())
        		)        		
        	{ // Do not move randomly and set boxNum to get the box later.
        		mvRand = false;
        		boxNum = b;
        	}
    	}
    	
    	// If animation is not playing, play it.
		if (!getMvAnim().getStatus().equals(Animation.Status.RUNNING)) {
			getMvAnim().play();
		}
		
    	// If Npc is to move randomly, call moveRandom().
    	if (mvRand) {
    		cantMvToCnt = 0;
    		moveRandom();
    	}
    	
    	// If boxNum was set and not moving randomly
    	else if (boxNum != -1){ 
    		
    		moveToHurtBox(npcBox, hurtBoxes[boxNum]);
    	}
    }
    
    public void translate() {
    	/*
    	 * This method will move the Npc's sprite group on the stage and increment
    	 * its mvRate.
    	*/

		getSprite().getSpriteGroup().setTranslateY(
				getSprite().getSpriteGroup().getTranslateY() + getDy()
				)
		;
		getSprite().getSpriteGroup().setTranslateX(
				getSprite().getSpriteGroup().getTranslateX() + getDx()
				)
		;
		
    	setMvRate(getMvRate() + incMvRate);
    	
    	if (getMvRate() > maxMvRate) {
    		setMvRate(maxMvRate);
    	}
    }
    
    public boolean canMove() {
    	/*
    	 * This method will return true if the Npc's checkBox bounds do not 
    	 * intersect with any worldBox on the stage. 
    	*/
    	
    	boolean can;
    	
    	CollisionBox[] boxes = getStage().getWorldBoxes();
    	CollisionBox checkBox = getSprite().getCheckBox();
    	
    	double ogMinX = checkBox.getOriginalBounds().getMinX();
    	double ogMinY = checkBox.getOriginalBounds().getMinY();
    	double ogMaxX = checkBox.getOriginalBounds().getMaxX();
    	double ogMaxY = checkBox.getOriginalBounds().getMaxY();
    	
    	double minX = ogMinX;
    	double minY = ogMinY;
    	double maxX = ogMaxX;
    	double maxY = ogMaxY;
    	double pad  = 1.0;
    	double[] checkBounds;
    	
    	if (getDy() > 0.0) {
    		maxY += (getDy() * pad);
    	}
    	else {
    		minY += (getDy() * pad);
    	}
    	
    	if (getDx() > 0.0) {
    		maxX += (getDx() * pad);
    	}
    	else {
    		minX += (getDx() *  (pad / 1.0));
    	}
    	
    	checkBounds = new double[] {minX, minY, maxX, maxY};
    	checkBox.setBounds(checkBounds);
    	
    	
    	can = true;
    	for (int box = 0; box < boxes.length && can; ++box) { 

    		if (box != getColBoxIndex()) {
				if (checkBox.getBounds().intersects(boxes[box].getBounds())) {
	    			setMvRate(getMvRate() - incMvRate);
	    			can = false;
				}
    		}
    	}
    	
    	if (getMvRate() < 0.0) {
    		setMvRate(incMvRate);
    	}
    	
    	return can;
    }
    
    public void moveRandom() {
    	/**
    	 * This method will move the NPC randomly.
    	*/
    	
    	++tempDirCount;

		if (mv < 2) {
    		setDx(0.0);
    		setDy(-getMvRate());
		}
		else if (mv < 4){
    		setDx(getMvRate());
    		setDy(0.0);
		}
		else if (mv < 6) {
			setDx(0.0);
			setDy(getMvRate());
		}
		else if (mv < 8) {
			setDx(-getMvRate());
			setDy(0.0);
		}
		else {
			setDx(0.0);
			setDy(0.0);
		}
		
    	if (tempDirCount > 10) {
    		tempDirCount = 0;
        	mv = genMv();
    	}
    	
    	
    	if (!canMove()) {
    		checkCollision();
    	}
    	else {
    		setDir();
    		translate();
    	}
    }
    
    public void moveToHurtBox(CollisionBox npcBox, CollisionBox hurtBox) {
    	/*
    	 * This method will move the NPC towards a hurtBox.
    	*/
    	
    	// used to make Npc's direction look more natural when moving towards 
    	// player.
    	double distDiffY;
    	double distDiffX;
    	double absDistX;
    	double absDistY;
    	
    	distDiffY = npcBox.getMidY() - hurtBox.getMidY();
    	distDiffX = npcBox.getMidX() - hurtBox.getMidX();
    	absDistX  = Math.abs(Math.abs(npcBox.getMidX()) - Math.abs(hurtBox.getMidX()));
    	absDistY  = Math.abs(Math.abs(npcBox.getMidY()) - Math.abs(hurtBox.getMidY()));
    	
    	// Set dx/dx based on position of player.
    	if (distDiffY < -3.0) { // Below - move down.
    		setDy(getMvRate());
    	}
    	else if (distDiffY > 3.0) { // Above - move up.
    		setDy(-getMvRate());
    	}
    	
    	if (distDiffX > 3.0) { // Left - move left.
    		setDx(-getMvRate());
    	}
    	else if (distDiffX < -3.0) { // Right - move right.
    		setDx(getMvRate());
    	}
    	
    	// Use distY and distX to determine which direction to set.
    	setMoveToDir(distDiffX, distDiffY, absDistX, absDistY);
    	
    	// Normalize movement if moving diagonally.
    	if (Math.abs(getDx()) > 0.0 && Math.abs(getDy()) > 0.0) {
			setDx( getDx() * getNormalX() );
			setDy( getDy() * getNormalY() );
    	}
    	
    	if (canMove() && mv > 10) {
    		translate();
    	}
    	else if (!canMove() && (absDistY < 25.0 && absDistX < 25.0)){
    		
    		setMoveToDir(distDiffX, distDiffY, absDistX, absDistY);
    		attkHurtBox = true;
    	}
    	else if (!canMove() && !(absDistY < 25.0 && absDistX < 25.0)) {
    		moveAround();
    	}
    	
    	mv = genMv();
    }
    
    public void setMoveToDir(
    		double distDiffX, 
    		double distDiffY,
    		double absDistX,
    		double absDistY
    	){
    	/**
    	 * This method will set the Npc's direction based on where it is moving to
    	 * given the difference of the Npc's and its target's 2D position.
    	 * 
    	 * @param distDiffX: The difference between the Npc's and its target's X 
    	 * 					 position. 
    	 * @param distDiffY: The difference between the Npc's and its target's Y 
    	 * 					 position. 
    	 * @param absDistX: The absolute value of distDiffX.
    	 * @param absDistY: The absolute value of distDiffY.
    	*/
    	
		if (distDiffY > 0.0 && absDistY > absDistX) {
			forceDir(Going.N);
		}
		else if (distDiffY < 0.0 && absDistY > absDistX) {
			forceDir(Going.S);
		}
		else if (distDiffX > 0.0 && absDistX > absDistY) {
			forceDir(Going.W);
		}
		else if (distDiffX < 0.0 && absDistX > absDistY) {
			forceDir(Going.E);
		}
    }
    
	public void moveAround() {
		/**
		 * works fairly decent. 
		 * Not 100% as intended.
		 * Main issue is getting stuck on corners and in the middle.
		*/
		
		
		// Character's current checkBox bounds.
		Bounds charBounds = getSprite().getCheckBox().getBounds();
		
		// Will store the worldBox bounds of the worldBox being checked.
		Bounds checkBounds;
		
		// Character's current center, min, and max X/Y values.
		double currX = getSprite().getCheckBox().getMidX();
		double currY = getSprite().getCheckBox().getMidY();
		double maxX  = getSprite().getCheckBox().getMaxX();
		double maxY  = getSprite().getCheckBox().getMaxY();
		double minX  = getSprite().getCheckBox().getMinX();
		double minY  = getSprite().getCheckBox().getMinY();
		
		// Will store the center, min, and max X/Y values of the worldBox being checked.
		double checkX;
		//double checkY;
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
				//checkY = worldBoxes[box].getMidY();
				
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
					
					// right or left
					if ((getDx() > 0.0 && d) || (getDx() < 0.0 && a)) {
						
						setDx(-getDx() * bounce);
						
						if (canMvTo) {
							if (currY > checkMaxY) {
								setDy(maxMvRate);
							}
							else if (currY < checkMinY){
								setDy(-maxMvRate);
							}
							else if (getDy() > 0.0) {
								setDy(maxMvRate);
							}
							else if (getDy() < 0.0) {
								setDy(-maxMvRate);
							}
						}
					}
					
					// Up or down.
					else if ((getDy() < 0.0 && w) || (getDy() > 0.0 && s)) {
						setDy(-getDy() * bounce);
						
						if (canMvTo) {
							if (currX > checkMaxX) {
								setDx(maxMvRate);
							}
							else if (currX < checkMinX){
								setDx(-maxMvRate);
							}
							else if (getDx() > 0.0) {
								setDx(maxMvRate);
							}
							else if (getDx() < 0.0) {
								setDx(-maxMvRate);
							}
						}
					}
				}
			}
			
			contained = false;
		}
		
    	if (canMove()) {
	    	translate();
	    	
	    	++cantMvToCnt;
	    	if (cantMvToCnt > 25 && !canMvTo) {
	    		cantMvToCnt = 0;
	    		canMvTo = true;
	    	}
	    	else if (cantMvToCnt > 25) {
	    		cantMvToCnt = 0;
	    		canMvTo = false;
	    	}
    	}
	}

}