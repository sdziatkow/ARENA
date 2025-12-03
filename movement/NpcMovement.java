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
import java.util.ArrayList;
import arenaEnum.ColType;
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
	
	private int totalPathCount; // Total amount of time pathing.
	private int pathCount;      // Amount of time per path.
	private int pathLimit;      // Limit per path.
	private Going pathDir;      // Direction of path.
	private Going lastPathDir;  // Direction of last path.
	
	// Will determine how the npc moves.
	boolean mvRand;
	boolean isMvToHurtBox;
	private int currTargetIdx;
	
	// Will determine when pathing begins or becomes too long.
	boolean pathing;
	boolean pathTooLong;
	
	// For random movement.
	private int tempDirCount;
	

//CONSTRUCTORS---------------------------------------------------------------------
	
    public NpcMovement() {
        /**
         * Default Constructor for class NpcMovement
        */
    	
    	super();
    	setMvRate(1.25);
    	setColBounce(0.001);
    	mvGen = new Random();
    	isMvToHurtBox = false;
    	pathing = false;
    	pathTooLong = false;
    	
    	tempDirCount = 0;
    	totalPathCount = 1;
    	pathCount = 0;
    	pathLimit = 2;
    	
    	currTargetIdx = -1;
    	
    	genMv();
    }

//SETTERS--------------------------------------------------------------------------

//GETTERS--------------------------------------------------------------------------
    
    public boolean isMvToHurtBox() {
        /**
         * Getter for field: attkHurtBox.
        */
    	
    	return isMvToHurtBox;
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
    	CollisionBox detectBox = getBoxes(ColType.DETECTBOX).get(getColBoxIndex());
    	CollisionBox hurtBox;
    	
    	// If not moving to hurtBox, get closest hurtBox and set currTargetIdx to 
    	// its idx.
    	if (!isMvToHurtBox) {
    		hurtBox = getClosestBox(ColType.HURTBOX);
    		
    		if (hurtBox != null) {
    			currTargetIdx = getBoxes(ColType.HURTBOX).indexOf(hurtBox);
    		}
    	}
    	
    	// If is moving to hurtBox, keep moving to the same one that initially 
    	// entered.
    	else {
    		hurtBox = getBoxes(ColType.HURTBOX).get(currTargetIdx);
    	}
    	
    	mvRand = true;
    	if (detectBox != null && hurtBox != null) {
        	if (detectBox.getBounds().contains(hurtBox.getBounds())) {
        		mvRand = false;
        	}
    	}
		
    	// If Npc is to move randomly, call moveRandom().
    	if (mvRand) {
    		isMvToHurtBox = false;
    		moveRandom();
    	}
    	
    	// If boxNum was set and not moving randomly
    	else { 
    		isMvToHurtBox = true;
    		moveToPoint(detectBox, hurtBox);
    	}
    }
    
    public void translate() {
    	/*
    	 * This method will move the Npc's sprite group on the stage and increment
    	 * its mvRate.
    	*/
    	
    	if (!isAttk().get() && (getDx() != 0.0 || getDy() != 0.0)) {
    		isMv().set(true);
    	}
    	else {
    		isMv().set(false);
    	}
    	
    	if (isMvToHurtBox) {
        	mv = genMv();
        	if (mv < 10) {
        		setMvRate(getMvRate() * 0.5);
        	}
    	}
    	
    	super.translate();
    }
    
    public void moveCheckBox() {
    	/*
    	 * 
    	*/
    	
    	CollisionBox checkBox = getBoxes(ColType.CHECKBOX).get(getColBoxIndex());
    	
    	double minX = checkBox.getOriginalBounds()[0];
    	double minY = checkBox.getOriginalBounds()[1];
    	double maxX = checkBox.getOriginalBounds()[2];
    	double maxY = checkBox.getOriginalBounds()[3];
    	double pad  = 1.0000;
    	double[] checkBounds;
    	
		minY += (getDy() * pad);
		
		minX += (getDx() *  pad);
    	
    	checkBounds = new double[] {minX, minY, maxX, maxY};
    	checkBox.setBounds(checkBounds);
    }
    
    public boolean canMove() {
    	/*
    	 * This method will return true if the Npc's checkBox bounds do not 
    	 * intersect with any worldBox on the stage. 
    	*/
    	
    	boolean can = true;
    	ArrayList<CollisionBox> boxes = getBoxesWithinRange(ColType.WORLDBOX, true);
    	if (boxes == null) {
    		return can;
    	}
    	
    	CollisionBox checkBox = getBoxes(ColType.CHECKBOX).get(getColBoxIndex());
    	moveCheckBox();
    	
    	can = true;
    	for (int b = 0; b < boxes.size(); ++b) {
			if (checkBox.getBounds().intersects(boxes.get(b).getBounds())) {
				setMvRate(getMvRate() - getIncMvRate());
				can = false;
			}
    	}
    	
    	return can;
    }
    
    public void moveRandom() {
    	/**
    	 * This method will move the NPC randomly.
    	 * Very low chance to actually move because it ticks every 32ms 
    	*/
    	
    	++tempDirCount;

		if (mv < 2) {
			moveOneDir(Going.N, false);
		}
		else if (mv < 4){
			moveOneDir(Going.E, false);
		}
		else if (mv < 6) {
			moveOneDir(Going.S, false);
		}
		else if (mv < 8) {
			moveOneDir(Going.W, false);
		}
		else {
			moveOneDir(null, false);
		}
		
		// If npc has moved 10 times in one direction, reset count and gen new mv.
    	if (tempDirCount > 10) {
    		tempDirCount = 0;
        	mv = genMv();
    	}
    	
    	
    	if (!canMove()) {
    		checkCollision();
    	}
    	else {
    		setDir();
    	}
		translate();
    }
    
    public void moveOneDir(Going dir, boolean moveCheckBox) {
    	/*
    	 * WILL SET DX/DY based on given dir.
    	 * 
    	 * This method provides multiple uses.
    	 * Given moveCheckBox is true, that means dx/dy will be multiplied to
    	 * further extend the checkbox when moving it.
    	 * 
    	 * Otherwise sets dx/dy normally.
    	*/
    	
    	setDx(0.000);
    	setDy(0.000);
    	
    	if (dir == null) {
    		return;
    	}
    	
    	double dxMult = 10.5;
    	double dyMult = 10.5;
    	
    	switch (dir) {
    	
    	// NORTH
    	case N:
    		setDy(-getMvRate());
    		break;
    	case NE:
    		setDx(getMvRate());
    		setDy(-getMvRate());
    		break;
    	case NW:
    		setDx(-getMvRate());
    		setDy(-getMvRate());
    		break;
    		
    	// EAST
    	case E:
    		setDx(getMvRate());
    		break;
    	
    	// SOUTH
    	case S:
    		setDy(getMvRate());
    		break;
    	case SE:
    		setDx(getMvRate());
    		setDy(getMvRate());
    		break;
    	case SW:
    		setDx(-getMvRate());
    		setDy(getMvRate());
    		break;
    	
    	// WEST
    	case W:
    		setDx(-getMvRate());
    		break;
    	default:
    		break;
    	}
    	
    	// Normalize dx/dy
    	if (getDx() != 0.000 && getDy() != 0.000) {
    		setDx(getDx() * getNormalX());
    		setDy(getDx() * getNormalY());
    	}
    	
    	// Apply dx/dy mult if moving checkbox only.
    	if (moveCheckBox) {
    		setDx(getDx() * dxMult);
    		setDy(getDy() * dyMult);
    	}
    }
    
    public void moveToPoint(CollisionBox myBox, CollisionBox otherBox) {
    	/*
    	 * 
    	*/
    	
    	// used to make Npc's direction look more natural when moving towards 
    	// player.
    	double distDiffY;
    	double distDiffX;
    	double absDistX;
    	double absDistY;
    	
    	distDiffY = myBox.getMidY() - otherBox.getMidY();
    	distDiffX = myBox.getMidX() - otherBox.getMidX();
    	absDistX  = Math.abs(Math.abs(myBox.getMidX()) - Math.abs(otherBox.getMidX()));
    	absDistY  = Math.abs(Math.abs(myBox.getMidY()) - Math.abs(otherBox.getMidY()));
    	
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
    	
    	// Normalize movement if moving diagonally.
    	if (Math.abs(getDx()) > 0.0 && Math.abs(getDy()) > 0.0) {
			setDx( getDx() * getNormalX() );
			setDy( getDy() * getNormalY() );
    	}
    	
    	// If canMove and not currently pathing, MOVE TO POINT
    	if (canMove() && !pathing) {
    		totalPathCount = 1;
    		pathTooLong = false;
    		setMoveToDir(distDiffX, distDiffY, absDistX, absDistY);
    		translate();
    	}
    	
    	// If can not move and not currently pathing and otherBox close enough, ATTACK.
    	else if (!pathing && isMvToHurtBox() && (absDistY + absDistX < 30.0)) {
    		setMoveToDir(distDiffX, distDiffY, absDistX, absDistY);
    		isAttk().set(true);
    	}
    	
    	// Otherwise begin pathing if not attacking.
    	else{
			pathing = true;
			
			// Every 25 times pathing is true, path is too long.
			// Will be set to false next time npc can move to point.
			++totalPathCount;
			if (totalPathCount % 40 == 0) {
				pathCount = 0;
				pathTooLong = true;
			}
			
			// Get next direction
			pathDir = findNextMove();
			
			// Move at half rate in next available dir.
    		setMvRate(getMaxMvRate() * 0.5);
    		moveOneDir(pathDir, false);
    		moveCheckBox();
    		translate();
    		
    		// PathLimit default 2, repeat this twice before trying to move to point again.
    		++pathCount;
    		if (pathCount > pathLimit) {
    			pathCount = 0;
    			pathing = false;
    		}
    	}
    }
    
    public Going findNextMove() {
    	/*
    	 * WORKS PERFECTLY.
    	*/
    	
    	// Create array of directions with length of total directions being checked.
    	final int TOTAL_DIR = 8;
    	Going dirInOrder[] = new Going[TOTAL_DIR];
    	
    	// Dir should already be set with setMoveToDir() OR forced by pathing.
    	// This is the direction the npc is heading already.
    	dirInOrder[0] = getSimpleDir();
    	
    	// Depending on which direction the npc is heading initially, check value
    	// of other axis and set the order to check directions accordingly.
    	if (dirInOrder[0].equals(Going.N)) {
    		
    		// Always put opposite direction last.
			dirInOrder[7] = Going.S;
    		
			// If moving EAST as well
    		if (getDx() > 0.0) {
    			dirInOrder[1] = Going.E;  // Try EAST       second
    			dirInOrder[2] = Going.SE; // Try SOUTH EAST third
    			dirInOrder[3] = Going.NE; // Try NORTH EAST fourth
    			dirInOrder[4] = Going.NW; // Try NORTH WEST fifth
    			dirInOrder[5] = Going.W;  // Try WEST       sixth
    			dirInOrder[6] = Going.SW; // Try SOUTH WEST seventh
    		}
    		
    		// Otherwise moving WEST as well.
    		else {
    			dirInOrder[1] = Going.W;  // Try WEST       second
    			dirInOrder[2] = Going.SW; // Try SOUTH WEST third
    			dirInOrder[3] = Going.NW; // Try NORTH WEST fourth
    			dirInOrder[4] = Going.NE; // Try NORTH EAST fifth
    			dirInOrder[5] = Going.E;  // Try EAST       sixth
    			dirInOrder[6] = Going.SE; // Try SOUTH EAST seventh
    		}
    	}
    	else if (dirInOrder[0].equals(Going.S)) {
			dirInOrder[7] = Going.N;
    		
    		if (getDx() > 0.0) {
    			dirInOrder[1] = Going.E;
    			dirInOrder[2] = Going.NE;
    			dirInOrder[3] = Going.SE;
    			dirInOrder[4] = Going.SW;
    			dirInOrder[5] = Going.W;
    			dirInOrder[6] = Going.NW;
    		}
    		else {
    			dirInOrder[1] = Going.W;
    			dirInOrder[2] = Going.NW;
    			dirInOrder[3] = Going.SW;
    			dirInOrder[4] = Going.SE;
    			dirInOrder[5] = Going.E;
    			dirInOrder[6] = Going.NE;
    		}
    	}
    	else if (dirInOrder[0].equals(Going.E)) {
			dirInOrder[7] = Going.W;
    		
			// If also going NORTH.
    		if (getDy() < 0.0) {
    			dirInOrder[1] = Going.N;
    			dirInOrder[2] = Going.NW;
    			dirInOrder[3] = Going.NE;
    			dirInOrder[4] = Going.SE;
    			dirInOrder[5] = Going.S;
    			dirInOrder[6] = Going.SW;
    		}
    		
    		// Otherwise also going SOUTH.
    		else {
    			dirInOrder[1] = Going.S;
    			dirInOrder[2] = Going.SW;
    			dirInOrder[3] = Going.SE;
    			dirInOrder[4] = Going.NE;
    			dirInOrder[5] = Going.N;
    			dirInOrder[6] = Going.NW;
    		}
    	}
    	else if (dirInOrder[0].equals(Going.W)) {
			dirInOrder[7] = Going.E;
    		
    		if (getDy() < 0.0) {
    			dirInOrder[1] = Going.N;
    			dirInOrder[2] = Going.NE;
    			dirInOrder[3] = Going.NW;
    			dirInOrder[4] = Going.SW;
    			dirInOrder[5] = Going.S;
    			dirInOrder[6] = Going.SE;
    		}
    		else {
    			dirInOrder[1] = Going.S;
    			dirInOrder[2] = Going.SE;
    			dirInOrder[3] = Going.SW;
    			dirInOrder[4] = Going.NW;
    			dirInOrder[5] = Going.N;
    			dirInOrder[6] = Going.NE;
    		}
    	}
    	
    	// For each direction
    	Going dir;
    	for (int d = 0; d < TOTAL_DIR; ++d) {
    		dir = dirInOrder[d];
    		
    		// This will exaggerate the dx/dy values so that canMove() checks 
    		// multiple moves in advance.
    		moveOneDir(dir, true);
			
    		// Return the first direction that is moveable.
			if (canMove()) {
				
				// If path way too long, act as if no path was found.
				if (totalPathCount > 300) {
					pathCount = 0;
					forceDir(dirInOrder[6]);
					System.out.println(">> PATH WAY TOO LONG, FORCING: " + dirInOrder[6].toString());
				}
				
				// Otherwise if pathTooLong force to last moveable dir.
				else if (pathTooLong) {
					System.out.println(">  PATH TOO LONG, FORCING: " + dir.toString());
					forceDir(lastPathDir);
				}
				else {
					lastPathDir = dir;
				}
				System.out.println("|  DIR FOUND: " + dir.toString());
				System.out.println("-------------------------");
				return dir;
			}
    	}
    	
		System.out.println("!  NO DIR FOUND, FORCING: " + dirInOrder[6].toString());
		System.out.println("-------------------------");
    	
		// If no dir found, force to opposite dir and continue.
		forceDir(dirInOrder[6]);
		pathCount = 0;
    	return null;
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
    	
		if (distDiffX > 0.0 && absDistX > absDistY) {
			forceDir(Going.W);
		}
		else if (distDiffX < 0.0 && absDistX > absDistY) {
			forceDir(Going.E);
		}
		else if (distDiffY > 0.0 && absDistY > absDistX) {
			forceDir(Going.N);
		}
		else if (distDiffY < 0.0 && absDistY > absDistX) {
			forceDir(Going.S);
		}
    }
    

}