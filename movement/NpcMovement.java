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

import arenaCharacter.ArenaCharacter;
import arenaCharacter.npc.Npc;
import collision.CollisionBox;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class NpcMovement extends Movement{
    /**
     *
    */

	private Random mvGen;
	private int mv;
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

//MOVEMENT-------------------------------------------------------------------------
    
    public void checkNextMove() {
    	/*
    	 * This method will check if the player is within detection range and
    	 * move the NPC accordingly.
    	*/
    	
    	ArenaCharacter player = getChar().getStage().getPlayer();
    	CollisionBox npcBox = getChar().getSprite().getDetectBox();
    	CollisionBox playerBox = player.getSprite().getHurtBox();
    	
    	if (!npcBox.getBounds().contains(playerBox.getBounds())) {
    		move();
    	}
    	else {
    		moveToPlayer(npcBox, playerBox);
    	}

    }
    
    public void move() {
    	/**
    	 * This method will move the NPC randomly.
    	*/
    	
    	mv = mvGen.nextInt(2000);
    	
    	if (mv > 1000) {
    		setDir('s');
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
    	
    	checkCollision();
    	
		moveX.setFromX(getChar().getSprite().getSpriteGroup().getTranslateX());
		moveX.setToX(getChar().getSprite().getSpriteGroup().getTranslateX() + getDx());
		
		moveY.setFromY(getChar().getSprite().getSpriteGroup().getTranslateY());
		moveY.setToY(getChar().getSprite().getSpriteGroup().getTranslateY() + getDy());
    	
    	moveX.play();
    	moveY.play();
    	
    }
    
    public void moveToPlayer(CollisionBox npcBox, CollisionBox playerBox) {
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
    	
    	checkCollision();
    	
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
    
    	// Move the NPC towards the player.
		moveX.setFromX(getChar().getSprite().getSpriteGroup().getTranslateX());
		moveX.setToX(getChar().getSprite().getSpriteGroup().getTranslateX() + getDx());
		
		moveX.setFromY(getChar().getSprite().getSpriteGroup().getTranslateY());
		moveX.setToY(getChar().getSprite().getSpriteGroup().getTranslateY() + getDy());
    	
    	moveX.play();
    }

}