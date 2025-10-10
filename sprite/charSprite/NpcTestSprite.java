package sprite.charSprite;

/**
 * Program Name:    NpcTestSprite.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
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

public class NpcTestSprite extends CharacterSprite {
	/**
	 * 
	*/
	
	// Will store sprites for each mvmnt direction.
	private int[][] upCoords;
	private int[][] leftCoords;
	private int[][] downCoords;
	private int[][] rightCoords;
	
	double[] worldBoxBounds;
	double[] hurtBoxBounds;
	double[] detectBoxBounds;

//CONSTRUCTORS---------------------------------------------------------------------
	
	public NpcTestSprite() {
		/**
		 * 
		*/
		
		super();
		setSpriteSheet(new Image("file:sprites/NPC_test.png"));
		
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
		
		for (int i = 0; i < upCoords.length; ++i) {
			setUpSprite(i, upCoords[i]);
			setLeftSprite(i, leftCoords[i]);
			setDownSprite(i, downCoords[i]);
			setRightSprite(i, rightCoords[i]);
		}
		
		
		setSpriteView(new ImageView(getDownSprite()[0]));
		
		worldBoxBounds  = new double[] {0, 0, 7.0, 8.0};
		hurtBoxBounds   = new double[] {0, 0, 9.0, 10.0};
		detectBoxBounds = new double[] {-23.0, -15.0, 65.0, 65.0};
		
		setWorldBox(new CollisionBox(ColType.WORLDBOX, worldBoxBounds));
		setHurtBox(new CollisionBox(ColType.HURTBOX, worldBoxBounds));
		setDetectBox(new CollisionBox(ColType.DETECTBOX, detectBoxBounds));
		
		getCharPane().getChildren().add(getSpriteView());
		getCharPane().getChildren().add(getWorldBox().getColBox());
		getCharPane().getChildren().add(getHurtBox().getColBox());
		getWorldBox().getColBox().setTranslateX(1);
		getWorldBox().getColBox().setTranslateY(3);
		
		getHurtBox().getColBox().setTranslateX(1);
		getHurtBox().getColBox().setTranslateY(3);
		
		setSpriteGroup(new Group(getCharPane()));
		
		getSpriteGroup().getChildren().add(getDetectBox().getColBox());
	}
	
	public double[] getHitBoxCoords(char dir) {
		double[] coords = new double[] {0};
		
		return coords;
	}
	
}
