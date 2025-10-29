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
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import sprite.charSprite.CharacterSprite;
import sprite.charSprite.NpcTestSprite;
import worldStage.WorldStage;

public class NpcMovement extends Movement{
    /**
     *
    */
	
	// Random number generated from mvGen stored into mv.
	private Random mvGen;
	private int mv;
	
	// Object that controls sprite's position on screen.
	private TranslateTransition pos;
	
	// Increment mvRate plus incMvrate until decreased to minimum 0.0 or exceeds
	// maxMvRate.
	private double incMvRate;
	private double maxMvRate;
	
	// If true then state will be set to attack.
	boolean attkHurtBox;

//CONSTRUCTORS---------------------------------------------------------------------
	
    public NpcMovement() {
        /**
         * Default Constructor for class NpcMovement
        */
    	
    	super();
    	attkHurtBox = false;
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
    	setMvRate(5.50);
    	mvGen = new Random();
    	
    	pos = new TranslateTransition();
    	pos.setNode(getSprite().getSpriteGroup());
    	
    	maxMvRate = getMvRate();
    	incMvRate = getMvRate() / (getMvRate() / 1.5 );
    	attkHurtBox = false;
    }

//SETTERS--------------------------------------------------------------------------

    public void set() {
        /**
         * Setter for field:
        */

    }
    
    public void toggleAttkHurtBox() {
        /**
         * Getter for field: attkHurtBox.
        */
    	
    	attkHurtBox = !attkHurtBox;
    }

//GETTERS--------------------------------------------------------------------------

    public void get() {
        /**
         * Getter for field:
        */

    }
    
    public boolean getAttkHurtBox() {
        /**
         * Getter for field: attkHurtBox.
        */
    	
    	return attkHurtBox;
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
    	
    	// If Npc is to move randomly, call moveRandom().
    	if (mvRand) {
    		moveRandom();
    	}
    	
    	// If boxNum was set and not moving randomly
    	else if (boxNum != -1){ 
    		moveToHurtBox(npcBox, hurtBoxes[boxNum]);
    	}
    	
    	// If animation is not playing, play it.
		if (!getMvAnim().getStatus().equals(Animation.Status.RUNNING)) {
			getMvAnim().play();
		}
    }
    
    public boolean canMove() {
    	/*
    	 * IT WORKS
    	 * 
    	 * NEED TO MAKE IT SCALE LESS???
    	 * NEED TO MAKE IT SCALE LESS ONLY FOR WORLD BOXES???
    	*/
    	
    	boolean can;
    	
    	CollisionBox[] boxes = getStage().getWorldBoxes();
    	CollisionBox checkBox = getSprite().getCheckBox();
    	
    	checkBox.getColBox().setScaleX(1.7);
    	checkBox.getColBox().setScaleY(1.7);
    	
    	checkBox.getColBox().setTranslateX(-getDx());
    	checkBox.getColBox().setTranslateY(-getDy());
    	
    	can = true;
    	for (int box = 0; box < boxes.length && can; ++box) { 
    		
    		if (box != getColBoxIndex()) {
    			if (checkBox.getBounds().intersects(boxes[box].getBounds())) {
	    			setMvRate(getMvRate() - incMvRate);
	    			can = false;
//	    			System.out.println("CAN NOT MOVE");
    			}
    		}
    	}
    	
    	
    	
    	//System.out.println(getMvRate());
    	
    	if (getMvRate() < 0.0) {
    		setMvRate(incMvRate);
    	}
    	
    	
    	checkBox.getColBox().setTranslateX(getDx());
    	checkBox.getColBox().setTranslateY(getDy());
    	
    	checkBox.getColBox().setScaleX(1.0);
    	checkBox.getColBox().setScaleY(1.0);
    	
    	return can;
    }
    
    public void moveRandom() {
    	/**
    	 * This method will move the NPC randomly.
    	*/
    	
    	mv = mvGen.nextInt(8000);
    	
    	if (mv > 1000) {
    		setDx(0.0);
    		setDy(0.0);
    	}
    	else if (mv > 750) {
    		setDir('w');
    		setDx(0.0);
    		setDy(-getMvRate());
    		
    	}
    	else if (mv > 500) {
    		setDir('a');
    		setDx(-getMvRate());
    		setDy(0.0);
    	}
    	else if (mv > 250) {
    		setDir('s');
    		setDx(0.0);
    		setDy(getMvRate());
    	}
    	else {
    		setDir('d');
    		setDx(getMvRate());
    		setDy(0.0);
    	}
    	
    	//checkCollision();
    	if (canMove() && !pos.getStatus().equals(Animation.Status.RUNNING)) {
    		
			pos.setFromX(getSprite().getSpriteGroup().getTranslateX());
			pos.setToX(getSprite().getSpriteGroup().getTranslateX() + getDx());
			
			pos.setFromY(getSprite().getSpriteGroup().getTranslateY());
			pos.setToY(getSprite().getSpriteGroup().getTranslateY() + getDy());
	    	
	    	pos.play();
	    	
	    	setMvRate(getMvRate() + incMvRate);
	    	
	    	if (getMvRate() > maxMvRate) {
	    		setMvRate(maxMvRate);
	    	}
    	}
    }
    
    public void moveToHurtBox(CollisionBox npcBox, CollisionBox playerBox) {
    	/*
    	 * This method will move the NPC towards the player.
    	*/
    	
    	// used to make Npc's direction look more natural when moving towards 
    	// player.
    	double distDiffY;
    	double distDiffX;

    	distDiffY = Math.abs((npcBox.getMidY() - playerBox.getMidY()));
    	distDiffX = Math.abs((npcBox.getMidX() - playerBox.getMidX()));
    	
    	// Set dx/dx based on position of player.
    	if (npcBox.getMidY() < playerBox.getMidY()) { // Below - move down.
    		setDy(getMvRate());
    	}
    	else if (npcBox.getMidY() > playerBox.getMidY()) { // Above - move up.
    		setDy(-getMvRate());
    	}
    	
    	if (npcBox.getMidX() > playerBox.getMidX()) { // Left - move left.
    		setDx(-getMvRate());
    	}
    	else if (npcBox.getMidX() < playerBox.getMidX()) { // Right - move right.
    		setDx(getMvRate());
    	}
    	
    	//checkCollision();
    	
    	// Use distY and distX to determine which direction to set.
    	if (getDy() < 0.0 && distDiffY > distDiffX) {
    		setDir('w');
    	}
    	else if (getDy() > 0.0 && distDiffY > distDiffX) {
    		setDir('s');
    	}
    	
    	else if (getDx() < 0.0 && distDiffY < distDiffX) {
    		setDir('a');
    	}
    	else if (getDx() > 0.0 && distDiffY < distDiffX) {
    		setDir('d');
    	}
    	
    	// Normalize movement if moving diagonally.
    	if (getDx() != 0.0 && getDy() != 0.0) {
			setDx( getDx() * getNormalX() );
			setDy( getDy() * getNormalY() );
    	}
    	
    	if (canMove() && !pos.getStatus().equals(Animation.Status.RUNNING)) {
    		
	    	// Move the NPC towards the player.
			pos.setFromX(getSprite().getSpriteGroup().getTranslateX());
			pos.setToX(getSprite().getSpriteGroup().getTranslateX() + getDx());
			
			pos.setFromY(getSprite().getSpriteGroup().getTranslateY());
			pos.setToY(getSprite().getSpriteGroup().getTranslateY() + getDy());
	    	
	    	pos.play();
	    	setMvRate(getMvRate() + incMvRate);
	    	
	    	if (getMvRate() > maxMvRate) {
	    		setMvRate(maxMvRate);
	    	}
    	}
    	else if (!canMove() && (distDiffY < 10.0 || distDiffX < 10.0)){
    		attkHurtBox = true;
    	}
    }

}