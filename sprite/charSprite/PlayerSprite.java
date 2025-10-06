package sprite.charSprite;

/**
 * Program Name:    PlayerSprite.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 08, 2025
 *<p>
 * Updated:         Month DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import collision.CollisionBox;
import collision.CollisionBox.ColType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerSprite extends CharacterSprite {
	/**
	 * 
	*/
	
	// Will store sprites for each mvmnt direction.
	private int[][] upCoords;
	private int[][] leftCoords;
	private int[][] downCoords;
	private int[][] rightCoords;
	
	private int[][] upAttkCoords;
	private int[][] leftAttkCoords;
	private int[][] downAttkCoords;
	private int[][] rightAttkCoords;
	
	
	double[] worldBoxBounds;
	double[] hurtBoxBounds;
	double[] hitBoxBounds;

//CONSTRUCTORS---------------------------------------------------------------------
	
	public PlayerSprite() {
		/**
		 * 
		*/
		
		super();
		setSpriteSheet(new Image("file:sprites/character.png"));
		
		// MOVEMENT COORDS
		upCoords = new int[][] {
			{ 0, 64, 15, 30},
			{16, 64, 15, 30},
			{32, 64, 15, 30},
			{48, 64, 15, 30}
		};
		leftCoords = new int[][] {
			{ 0, 96, 15, 30},
			{16, 96, 15, 30},
			{32, 96, 15, 30},
			{48, 96, 15, 30}
		};
		downCoords = new int[][] {
			{ 0,  0, 15, 30},
			{16,  0, 15, 30},
			{32,  0, 15, 30},
			{48,  0, 15, 30}
		};
		rightCoords = new int[][] {
			{ 0, 32, 15, 30},
			{16, 32, 15, 30},
			{32, 32, 15, 30},
			{48, 32, 15, 30}
		};
		
		// ATTACK COORDS
		upAttkCoords = new int[][] {
			{ 8, 160, 20, 30},
			{38, 160, 20, 30},
			{68, 160, 20, 30},
			{98, 160, 20, 30}
		};
		leftAttkCoords = new int[][] {
			{ 8, 224, 20, 30},
			{38, 224, 20, 30},
			{68, 224, 20, 30},
			{98, 224, 20, 30},
		};
		downAttkCoords = new int[][] {
			{ 8, 128, 20, 30},
			{38, 128, 20, 30},
			{68, 128, 20, 30},
			{98, 128, 20, 30}
		};
		rightAttkCoords = new int[][] {
			{ 8, 192, 20, 30},
			{38, 192, 20, 30},
			{68, 192, 20, 30},
			{98, 192, 20, 30}
		};
		
		
		// Set all coords to their array
		for (int i = 0; i < upCoords.length; ++i) {
			setUpSprite(i, upCoords[i]);
			setLeftSprite(i, leftCoords[i]);
			setDownSprite(i, downCoords[i]);
			setRightSprite(i, rightCoords[i]);
			
			setUpAttkSprite(i, upAttkCoords[i]);
			setLeftAttkSprite(i, leftAttkCoords[i]);
			setDownAttkSprite(i, downAttkCoords[i]);
			setRightAttkSprite(i, rightAttkCoords[i]);
		}
		
		// Set sprite view to default position
		setSpriteView(new ImageView(getDownSprite()[0]));
		
		// Initialize world box bounds and set world box.
		worldBoxBounds  = new double[] {0, 0, 7.0, 9.0};
		hurtBoxBounds  = new double[] {0, 0, 7.0, 9.0};
		hitBoxBounds  = new double[] {0, 0, 7.0, 9.0};
		setWorldBox(new CollisionBox(ColType.WORLDBOX, worldBoxBounds));
		setHurtBox(new CollisionBox(ColType.HURTBOX, hurtBoxBounds));
		setHitBox(new CollisionBox(ColType.HITBOX, hitBoxBounds));
		
		getCharPane().getChildren().add(getSpriteView());
		getCharPane().getChildren().add(getWorldBox().getColBox());
		getCharPane().getChildren().add(getHurtBox().getColBox());
		getWorldBox().getColBox().setTranslateX(1);
		getWorldBox().getColBox().setTranslateY(5);
		
		getHurtBox().getColBox().setTranslateX(1);
		getHurtBox().getColBox().setTranslateY(5);
		
		setSpriteGroup(new Group(getCharPane()));
	}
	
}
