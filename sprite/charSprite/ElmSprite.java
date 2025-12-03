package sprite.charSprite;

import collision.CollisionBox;
import arenaEnum.ColType;
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
		
		super(4, 0);
		setSpriteSheet(new Image("file:sprites/log.png"));
		frameGen = new FrameGen(getSpriteSheet());
		
		frameGen.setTotalDirections(4);
		frameGen.setFramesPerDirection(new int[] {4, 4, 4, 4});
		
		frameGen.setXOffset(0);
		frameGen.setYOffset(0);
		frameGen.setFrameWidth(32);
		frameGen.setFrameHeight(32);
		frameGen.genFrames();
		
		setMvFrames(0, frameGen.getAllFrames()[1]);
		setMvFrames(1, frameGen.getAllFrames()[2]);
		setMvFrames(2, frameGen.getAllFrames()[0]);
		setMvFrames(3, frameGen.getAllFrames()[3]);
		
		setSpriteView(new ImageView(getMvFrames(2)[0]));
		getCharPane().getChildren().add(getSpriteView());
		getCharPane().setMinWidth(32);
		getCharPane().setMinHeight(32);
		
		// Initialize CollisionBox bounds and set CollisionBoxes.
		worldBoxBounds = new double[] {
				12, 12, 8, 11
			};
		checkBoxBounds = new double[] {
				13, 14, 6, 8
			};
		setWorldBox(new CollisionBox(ColType.WORLDBOX, worldBoxBounds));
		setHurtBox(new CollisionBox(ColType.HURTBOX, worldBoxBounds));
		setCheckBox(new CollisionBox(ColType.CHECKBOX, checkBoxBounds));
		
		setSpriteGroup(new Group(
				getCharPane(),
				getWorldBox().getColBox(),
				getHurtBox().getColBox(),
				getCheckBox().getColBox(),
				getHpBar().getBar()
		));
		
		
	}

}
