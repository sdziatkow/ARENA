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
	
	private WritableImage[] upSprites;
	private WritableImage[] leftSprites;
	private WritableImage[] downSprites;
	private WritableImage[] rightSprites;
	private ImageView spriteView;
	
	private double[][] hitBoxCoords;
	
//CONSTRUCTORS---------------------------------------------------------------------

	public WeaponSprite() {
		/*
		 * Default constructor for class WeaponSprite. 
		*/
		
		frameGen = new FrameGen(new Image("file:sprites/sword.png"));
		frameGen.setTotalDirections(4);
		frameGen.setFramesPerDirection(new int[] {4, 4, 4, 4} );
		
		frameGen.setXOffset(0);
		frameGen.setYOffset(0);
		frameGen.setFrameWidth(32);
		frameGen.setFrameHeight(32);
		
		frameGen.genFrames();
		
    	downSprites = frameGen.getAllFrames()[0];
    	upSprites = frameGen.getAllFrames()[1];
    	rightSprites = frameGen.getAllFrames()[2];
    	leftSprites = frameGen.getAllFrames()[3];
    	
    	spriteView = new ImageView(getDownSprite()[0]);
    	spriteView.setCache(true);
    	
        hitBoxCoords = new double[][] {
			new double[] {8.0, 1.0, 14.0, 7.0},
			new double[] {21.0, 12.0, 7.0, 12.0},
			new double[] {9.0, 24.0, 14.0, 7.0},
			new double[] {0.0, 12.0, 7.0, 12.0}
		};
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void clearSpriteView() {
		/**
		 * This method will return an ImageView object containing the an Image o
		 * object of the current sprite.
		*/
		
    	spriteView = new ImageView(getDownSprite()[0]);
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
	
	public void setHitBoxCoords(double[][] coords) {
		/*
		 * Setter for field: hitBoxCoords
		*/
		
		this.hitBoxCoords = coords;
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public double[][] getAllHitBoxCoords() {
		/*
		 * Getter for field: hitBoxCoords.
		*/
		
		return hitBoxCoords;
	}
	
	public double[] getHitBoxCoords(Going dir) {
		/*
		 * 
		*/
		
		double[] coords;
		
		switch (dir) {
		case N:
			coords = getAllHitBoxCoords()[0];
			break;
		case E:
			coords = getAllHitBoxCoords()[1];
			break;
		case S:
			coords = getAllHitBoxCoords()[2];
			break;
		case W:
			coords = getAllHitBoxCoords()[3];
			break;
		default:
			coords = getAllHitBoxCoords()[0];
		}
		
		return coords;
	}
	
	public WritableImage[] getUpSprite() {
		/**
		 * 
		*/
		
		return upSprites;
	}
	
	public WritableImage[] getLeftSprite() {
		/**
		 * 
		*/
		
		return leftSprites;
	}
	
	public WritableImage[] getDownSprite() {
		/**
		 * 
		*/
		
		return downSprites;
	}
	
	public WritableImage[] getRightSprite() {
		/**
		 * 
		*/
		
		return rightSprites;
	}
	
	public ImageView getSpriteView() {
		/**
		 * This method will return an ImageView object containing the an Image o
		 * object of the current sprite.
		*/
		
		return spriteView;
	}
}
