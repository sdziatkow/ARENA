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
import arenaEnum.ColType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import movement.Movement.Going;
import sprite.FrameGen;

public class NpcTestSprite extends CharacterSprite {
	/**
	 * 
	*/
	
	// Will store sprites for each mvmnt direction.
	private FrameGen frameGen;
	
	private double[] worldBoxBounds;
	private double[] checkBoxBounds;
	private double[] detectBoxBounds;

//CONSTRUCTORS---------------------------------------------------------------------
	
	public NpcTestSprite() {
		/**
		 * 
		*/
		
		super(4, 0);
		setSpriteSheet(new Image("file:sprites/NPC_test.png"));
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
		
		// Set sprite view to default position and add it to StackPane.
		setSpriteView(new ImageView(getMvFrames(2)[0]));
		getCharPane().getChildren().add(getSpriteView());

		// Initialize CollisionBox bounds and set CollisionBoxes.
		worldBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() - 3.2,
				getCharPane().getBoundsInParent().getCenterY() + 1.0,
				getCharPane().getBoundsInParent().getCenterX() - 0.1,
				getCharPane().getBoundsInParent().getCenterY() - 6.0
			};
		checkBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() - 3.2,
				getCharPane().getBoundsInParent().getCenterY() + 1.0,
				getCharPane().getBoundsInParent().getCenterX() - 0.1,
				getCharPane().getBoundsInParent().getCenterY() - 6.0
			};
		detectBoxBounds = new double[] {-23.0, -15.0, 65.0, 65.0};
		setWorldBox(new CollisionBox(ColType.WORLDBOX, worldBoxBounds));
		setHurtBox(new CollisionBox(ColType.HURTBOX, worldBoxBounds));
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
	
	public double[] getHitBoxCoords(Going dir) {
		double[] coords = new double[] {0};
		
		return coords;
	}
	
}
