package sprite.charSprite;

import arenaEnum.ColType;
import collision.CollisionBox;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sprite.FrameGen;

/**
 * Program Name:    OrcSprite.java
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

public class OrcSprite extends CharacterSprite {
	/*
	 * 
	*/
	
	private FrameGen frameGen;
	
	private double[] worldBoxBounds;
	private double[] checkBoxBounds;
	private double[] hitBoxBounds;
	private double[] detectBoxBounds;
	
	public OrcSprite() {
		/*
		 * 
		*/
		
		super(9, 6);
		
		setSpriteSheet(new Image("file:sprites/orc.png"));
		frameGen = new FrameGen(getSpriteSheet());
		
		frameGen.setTotalDirections(4);
		frameGen.setFramesPerDirection(new int[] { 9, 9, 9, 9} );
		frameGen.setXOffset(0);
		frameGen.setYOffset(512);
		frameGen.setFrameWidth(64);
		frameGen.setFrameHeight(64);
		frameGen.genFrames();
		
		setMvFrames(0, frameGen.getAllFrames()[0]);
		setMvFrames(1, frameGen.getAllFrames()[3]);
		setMvFrames(2, frameGen.getAllFrames()[2]);
		setMvFrames(3, frameGen.getAllFrames()[1]);
		
		frameGen.setFramesPerDirection(new int[] { 6, 6, 6, 6} );
		frameGen.setXOffset(48);
		frameGen.setYOffset(3520);
		frameGen.setFrameWidth(96);
		frameGen.setFrameHeight(64);
		frameGen.genFrames(true, true);
		
		setAttkFrames(0, frameGen.getAllFrames()[0]);
		setAttkFrames(1, frameGen.getAllFrames()[3]);
		setAttkFrames(2, frameGen.getAllFrames()[2]);
		setAttkFrames(3, frameGen.getAllFrames()[1]);
		
		// Set sprite view to default position and add it to StackPane.
		setSpriteView(new ImageView(getMvFrames(2)[0]));
		getCharPane().getChildren().add(getSpriteView());
		getCharPane().setMinHeight(64);
		getCharPane().setMinWidth(96);
		
		// Initialize CollisionBox bounds and set CollisionBoxes.
		worldBoxBounds = new double[] {
				38, 32 , 20, 28
			};
		checkBoxBounds = new double[] {
				24, 16, 24, 32
			};
		hitBoxBounds = new double[] {
				38, 32 , 20, 28
			};
		detectBoxBounds = new double[] {
				-16, -32, 128, 128
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
		
		getHpBar().getBar().setTranslateX(32);
		getHpBar().getBar().setTranslateY(10);
		
		getWeaponSprite().setHitBoxOffset(20.0);
	}

}
