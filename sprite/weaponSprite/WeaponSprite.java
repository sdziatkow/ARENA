package sprite.weaponSprite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 * Program Name:    WeaponSprite.java
 *<p>
 * Purpose:         The purpose of this program is to create a system that all
 * 					Weapon's can use for display and hit boxes.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 07, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import movement.Movement.Going;
import sprite.FrameGen;

public class WeaponSprite {
	/*
	 * For now, this class is only used for hitbox purposes. 
	 * Planned to separate weapon and character sprites entirely in the future.
	*/
	
	private FrameGen frameGen;
	
	private WritableImage[][] frames;
	private ImageView spriteView;
	
	private double hitBoxOffset;
	
//CONSTRUCTORS---------------------------------------------------------------------

	public WeaponSprite() {
		/*
		 * Default constructor for class WeaponSprite. 
		*/
		
		hitBoxOffset = 10.0;
		
		frameGen = new FrameGen(new Image("file:sprites/sword.png"));
		frameGen.setTotalDirections(4);
		frameGen.setFramesPerDirection(new int[] {4, 4, 4, 4} );
		
		frameGen.setXOffset(0);
		frameGen.setYOffset(0);
		frameGen.setFrameWidth(32);
		frameGen.setFrameHeight(32);
		
		frameGen.genFrames();
		
		frames = new WritableImage[4][];
    	frames[0] = frameGen.getAllFrames()[1];
    	frames[1] = frameGen.getAllFrames()[2];
    	frames[2] = frameGen.getAllFrames()[0];
    	frames[3] = frameGen.getAllFrames()[3];
    	
    	spriteView = new ImageView(getFrames(2)[0]);
    	spriteView.setCache(true);
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setHitBoxOffset(double offset) {
		/*
		 * 
		*/
		
		hitBoxOffset = offset;
	}
	
	public void clearSpriteView() {
		/**
		 * This method will return an ImageView object containing the an Image o
		 * object of the current sprite.
		*/
		
    	spriteView = new ImageView(getFrames(0)[0]);
    	spriteView.setCache(true);
	}
	
	public void setSpriteImg(WritableImage img) {
		/**
		 * This method will set the current Image of ImageView currSprite to 
		 * sprite.
		 * 
		 * @param sprite: A WritableImage object containing a player sprite.
		*/
		
		spriteView.setImage(img);
		
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public double[] getHitBoxTranslate(String dir) {
		/*
		 * 
		*/
		
		double translate[] = new double[] {0.0, 0.0};
		

		if (dir.equals("N")) {
			 translate[1] = -hitBoxOffset;
		}
		else if (dir.equals("E")) {
			 translate[0] = hitBoxOffset;
		}
		else if (dir.equals("S")) {
			 translate[1] = hitBoxOffset;
		}
		else if (dir.equals("W")) {
			 translate[0] = -hitBoxOffset;
		}
		
		return translate;
	}
	
	public WritableImage[] getFrames(int dir) {
		/**
		 * 
		*/
		
		return frames[dir];
	}
	
	public ImageView getSpriteView() {
		/**
		 * This method will return an ImageView object containing the an Image o
		 * object of the current sprite.
		*/
		
		return spriteView;
	}
}
