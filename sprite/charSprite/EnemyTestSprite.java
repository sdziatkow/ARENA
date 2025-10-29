package sprite.charSprite;

import collision.CollisionBox;
import collision.CollisionBox.ColType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyTestSprite extends CharacterSprite {
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
	double[] detectBoxBounds;
	double[] checkBoxBounds;

//CONSTRUCTORS---------------------------------------------------------------------
	
	public EnemyTestSprite() {
		/**
		 * 
		*/
		
		super();
		setSpriteSheet(new Image("file:sprites/enemy_test.png"));
		
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
		
		// Set sprite view to default position and add it to StackPane.
		setSpriteView(new ImageView(getDownSprite()[0]));
		getCharPane().getChildren().add(getSpriteView());
		
		// Initialize CollisionBox bounds and set CollisionBoxes.
		worldBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() - 3.2,
				getCharPane().getBoundsInParent().getCenterY() + 1.0,
				getCharPane().getBoundsInParent().getCenterX() - 0.2,
				getCharPane().getBoundsInParent().getCenterY() - 6.0
			};
		checkBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() - 3.2,
				getCharPane().getBoundsInParent().getCenterY() + 1.0,
				getCharPane().getBoundsInParent().getCenterX() - 0.1,
				getCharPane().getBoundsInParent().getCenterY() - 6.0
			};
		hitBoxBounds  = new double[] {0, 0, 7.0, 9.0};
		detectBoxBounds = new double[] {
				-getCharPane().getBoundsInParent().getCenterX() * 7.5,
				-getCharPane().getBoundsInParent().getCenterY() * 3.75,
				getCharPane().getBoundsInParent().getCenterX() * 17.0,
				getCharPane().getBoundsInParent().getCenterY() * 9.5
			};
		setWorldBox(new CollisionBox(ColType.WORLDBOX, worldBoxBounds));
		setHurtBox(new CollisionBox(ColType.HURTBOX, worldBoxBounds));
		setHitBox(new CollisionBox(ColType.HITBOX, hitBoxBounds));
		setCheckBox(new CollisionBox(ColType.CHECKBOX, checkBoxBounds));
		setDetectBox(new CollisionBox(ColType.DETECTBOX, detectBoxBounds));
		
		// Create new Group with StackPane and CollisionBoxes.
		setSpriteGroup(new Group(
				getCharPane(), 
				getWorldBox().getColBox(),
				getHurtBox().getColBox(),
				getCheckBox().getColBox(),
				getDetectBox().getColBox()
		));
	}
	
	public double[] getHitBoxCoords(char dir) {
		/*
		 * 
		*/
		
		double coords[];
			
		switch (dir) {
		case 'w':
			coords = new double[] {3.0, 1.0, 14.0, 7.0};
			break;
		case 'a':
			coords = new double[] {-5.0, 12.0, 7.0, 12.0};
			break;
		case 's':
			coords = new double[] {4.0, 24.0, 14.0, 10.0};
			break;
		case 'd':
			coords = new double[] {16.0, 12.0, 7.0, 12.0};
			break;
		default:
			coords = new double[] {16.0, 12.0, 7.0, 12.0};
			break;
		}
		
		return coords;
		
	}

}
