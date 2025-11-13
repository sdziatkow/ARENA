package sprite.charSprite;

import collision.CollisionBox;
import collision.CollisionBox.ColType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import movement.Movement.Going;
import sprite.FrameGen;

public class EnemyTestSprite extends CharacterSprite {
	/**
	 * 
	*/
	
	private FrameGen frameGen;
	
	private double[] worldBoxBounds;
	private double[] hurtBoxBounds;
	private double[] hitBoxBounds;
	private double[] detectBoxBounds;
	private double[] checkBoxBounds;

//CONSTRUCTORS---------------------------------------------------------------------
	
	public EnemyTestSprite() {
		/**
		 * 
		*/
		
		setSpriteSheet(new Image("file:sprites/enemy_test.png"));
		frameGen = new FrameGen(getSpriteSheet());
		
		frameGen.setTotalDirections(4);
		frameGen.setFramesPerDirection(new int[] { 4, 4, 4, 4} );
		frameGen.setXOffset(0);
		frameGen.setYOffset(0);
		frameGen.setFrameWidth(16);
		frameGen.setFrameHeight(32);
		frameGen.genFrames();
		
		setDownSprite(frameGen.getAllFrames()[0]);
		setRightSprite(frameGen.getAllFrames()[1]);
		setUpSprite(frameGen.getAllFrames()[2]);
		setLeftSprite(frameGen.getAllFrames()[3]);
		
		frameGen.setXOffset(8);
		frameGen.setYOffset(128);
		frameGen.genFrames(true);
		
		setDownAttkSprite(frameGen.getAllFrames()[0]);
		setUpAttkSprite(frameGen.getAllFrames()[1]);
		setRightAttkSprite(frameGen.getAllFrames()[2]);
		setLeftAttkSprite(frameGen.getAllFrames()[3]);
	
		// Set sprite view to default position and add it to StackPane.
		setSpriteView(new ImageView(getDownSprite()[0]));
		getCharPane().getChildren().add(getSpriteView());
		getCharPane().setMinHeight(32);
		getCharPane().setMinWidth(32);
		
		// Initialize CollisionBox bounds and set CollisionBoxes.
		worldBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() + 4.3,
				getCharPane().getBoundsInParent().getCenterY() + 1.3,
				getCharPane().getBoundsInParent().getCenterX() - 1.0,
				getCharPane().getBoundsInParent().getCenterY() - 7.0
			};
		checkBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() + 0.5,
				getCharPane().getBoundsInParent().getCenterY() - 6.0,
				getCharPane().getBoundsInParent().getCenterX() - 0.2,
				getCharPane().getBoundsInParent().getCenterY() - 7.0
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
}
