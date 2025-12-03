package sprite.charSprite;

import collision.CollisionBox;
import arenaEnum.ColType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import movement.Movement.Going;
import sprite.FrameGen;
import ui.overlay.StatBar;

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
		
		super(4, 4);
		
		setSpriteSheet(new Image("file:sprites/enemy_test.png"));
		frameGen = new FrameGen(getSpriteSheet());
		
		frameGen.setTotalDirections(4);
		frameGen.setFramesPerDirection(new int[] { 4, 4, 4, 4} );
		frameGen.setXOffset(0);
		frameGen.setYOffset(0);
		frameGen.setFrameWidth(16);
		frameGen.setFrameHeight(32);
		frameGen.genFrames();
		
		setMvFrames(0, frameGen.getAllFrames()[2]);
		setMvFrames(1, frameGen.getAllFrames()[1]);
		setMvFrames(2, frameGen.getAllFrames()[0]);
		setMvFrames(3, frameGen.getAllFrames()[3]);
		
		frameGen.setXOffset(8);
		frameGen.setYOffset(128);
		frameGen.genFrames(true);
		
		setAttkFrames(0, frameGen.getAllFrames()[1]);
		setAttkFrames(1, frameGen.getAllFrames()[2]);
		setAttkFrames(2, frameGen.getAllFrames()[0]);
		setAttkFrames(3, frameGen.getAllFrames()[3]);
	
		// Set sprite view to default position and add it to StackPane.
		setSpriteView(new ImageView(getMvFrames(2)[0]));
		getCharPane().getChildren().add(getSpriteView());
		getCharPane().setMinHeight(32);
		getCharPane().setMinWidth(32);
		
		// Initialize CollisionBox bounds and set CollisionBoxes.
		worldBoxBounds = new double[] {
				12, 12, 8, 11
			};
		checkBoxBounds = new double[] {
				13, 14, 6, 7
			};
		hitBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX(),
				getCharPane().getBoundsInParent().getCenterY() - 5.0,
				getCharPane().getBoundsInParent().getMaxX(),
				getCharPane().getBoundsInParent().getCenterY() + 1.0
			};
		detectBoxBounds = new double[] {
				-getCharPane().getBoundsInParent().getCenterX() * 8.5,
				-getCharPane().getBoundsInParent().getCenterY() * 4.75,
				getCharPane().getBoundsInParent().getCenterX() * 20.0,
				getCharPane().getBoundsInParent().getCenterY() * 10.5
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
				getDetectBox().getColBox(),
				getHpBar().getBar()
		));
	}
}
