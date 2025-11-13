package sprite.charSprite;

import collision.CollisionBox;
import collision.CollisionBox.ColType;
import javafx.scene.Group;

/**
 * Program Name:    ElmSprite.java
 *<p>
 * Purpose:         The purpose of this program is to create a sprite for log.png.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         Month DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sprite.FrameGen;

public class ElmSprite extends CharacterSprite {
	/*
	 * 
	*/
	
	private FrameGen frameGen;
	private double[] worldBoxBounds;
	private double[] checkBoxBounds;
	
	public ElmSprite() {
		/*
		 * 
		*/
		
		super();
		setSpriteSheet(new Image("file:sprites/log.png"));
		frameGen = new FrameGen(getSpriteSheet());
		
		frameGen.setTotalDirections(4);
		frameGen.setFramesPerDirection(new int[] {4, 4, 4, 4});
		
		frameGen.setXOffset(0);
		frameGen.setYOffset(0);
		frameGen.setFrameWidth(32);
		frameGen.setFrameHeight(32);
		frameGen.genFrames();
		
		setDownSprite(frameGen.getAllFrames()[0]);
		setUpSprite(frameGen.getAllFrames()[1]);
		setRightSprite(frameGen.getAllFrames()[2]);
		setLeftSprite(frameGen.getAllFrames()[3]);
		
		setSpriteView(new ImageView(getDownSprite()[0]));
		getCharPane().getChildren().add(getSpriteView());
		getCharPane().setMinWidth(32);
		getCharPane().setMinHeight(32);
		
		// Initialize CollisionBox bounds and set CollisionBoxes.
		worldBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() - 3.3,
				getCharPane().getBoundsInParent().getCenterY() - 3.3,
				getCharPane().getBoundsInParent().getCenterX() - 8.0,
				getCharPane().getBoundsInParent().getCenterY() - 4.0
			};
		checkBoxBounds = new double[] {
				getCharPane().getBoundsInParent().getCenterX() - 8.5,
				getCharPane().getBoundsInParent().getCenterY() - 6.0,
				getCharPane().getBoundsInParent().getCenterX() - 5.0,
				getCharPane().getBoundsInParent().getCenterY() - 7.0
			};
		
		setWorldBox(new CollisionBox(ColType.WORLDBOX, worldBoxBounds));
		setHurtBox(new CollisionBox(ColType.HURTBOX, worldBoxBounds));
		setCheckBox(new CollisionBox(ColType.CHECKBOX, checkBoxBounds));
		setDetectBox(new CollisionBox(ColType.DETECTBOX, new double[] {0.0, 0.0, 0.1, 0.1}));
		
		setSpriteGroup(new Group(
				getCharPane(),
				getWorldBox().getColBox(),
				getHurtBox().getColBox(),
				getCheckBox().getColBox()
		));
		
		
	}

}
