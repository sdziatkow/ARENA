package sprite.charSprite;

/**
 * Program Name:    CharacterSprite.java
 *<p>
 * Purpose:         The purpose of this program is to create a system that all
 * 					ArenaCharacter sprites can use to be easily managed.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import collision.CollisionBox;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;

public abstract class CharacterSprite{
    /**
     *
    */
	
	// Will store entire character PNG file.
	private Image spriteSheet;
	
	// Will store sprites for each mvmnt direction.
	private int framesPerDir;
	private WritableImage[] upSprites;
	private WritableImage[] leftSprites;
	private WritableImage[] downSprites;
	private WritableImage[] rightSprites;
	
	private WritableImage[] upAttkSprites;
	private WritableImage[] leftAttkSprites;
	private WritableImage[] downAttkSprites;
	private WritableImage[] rightAttkSprites;
	
	// Will store sub image of playerSheet.
	private ImageView spriteView;
	private Group     spriteGroup;
	
	private CollisionBox worldBox;
	private CollisionBox hurtBox;
	private CollisionBox hitBox;
	private CollisionBox detectBox;
	private CollisionBox checkBox;
	
	// Will store spriteView and worldBox.getColBox() -> Ellipse
	private StackPane charPane;

//CONSTRUCTORS---------------------------------------------------------------------

    public CharacterSprite() {
        /**
         * Default Constructor for class
        */
    	
    	framesPerDir = 4;
    	
    	upSprites = new WritableImage[framesPerDir];
    	leftSprites = new WritableImage[framesPerDir];
    	downSprites = new WritableImage[framesPerDir];
    	rightSprites = new WritableImage[framesPerDir];
    	
    	upAttkSprites = new WritableImage[framesPerDir];
    	leftAttkSprites = new WritableImage[framesPerDir];
    	downAttkSprites = new WritableImage[framesPerDir];
    	rightAttkSprites = new WritableImage[framesPerDir];
    	
    	charPane = new StackPane();
    	charPane.setCache(true);

    }
    
    public CharacterSprite(int framesPerDir) {
        /**
         * Default Constructor for class
        */
    	
    	this.framesPerDir = framesPerDir;
    	
    	upSprites = new WritableImage[framesPerDir];
    	leftSprites = new WritableImage[framesPerDir];
    	downSprites = new WritableImage[framesPerDir];
    	rightSprites = new WritableImage[framesPerDir];
    	
    	upAttkSprites = new WritableImage[framesPerDir];
    	leftAttkSprites = new WritableImage[framesPerDir];
    	downAttkSprites = new WritableImage[framesPerDir];
    	rightAttkSprites = new WritableImage[framesPerDir];
    	
    	charPane = new StackPane();
    	charPane.setCache(true);

    }

//SETTERS--------------------------------------------------------------------------
    
    public void setSpriteSheet(Image spriteSheet) {
    	/**
    	 * 
    	*/
    	
    	this.spriteSheet = spriteSheet;
    }

    public void setUpSprite(int index, int[] coords) {
        /**
         * Setter for field: upSprites.
        */

    	this.upSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
    public void setLeftSprite(int index, int[] coords) {
        /**
         * Setter for field:
        */

    	this.leftSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
    public void setDownSprite(int index, int coords[]) {
        /**
         * Setter for field:
        */

    	this.downSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
    public void setRightSprite(int index, int coords[]) {
        /**
         * Setter for field:
        */

    	this.rightSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
    public void setUpAttkSprite(int index, int[] coords) {
        /**
         * Setter for field:
        */

    	this.upAttkSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
    public void setLeftAttkSprite(int index, int[] coords) {
        /**
         * Setter for field:
        */

    	this.leftAttkSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
    public void setDownAttkSprite(int index, int[] coords) {
        /**
         * Setter for field:
        */

    	this.downAttkSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
    public void setRightAttkSprite(int index, int[] coords) {
        /**
         * Setter for field:
        */

    	this.rightAttkSprites[index] = new WritableImage(
    			spriteSheet.getPixelReader(), 
    			coords[0], 
    			coords[1], 
    			coords[2], 
    			coords[3]
    			)
    	;
    }
    
	public void setSpriteView(ImageView spriteView) {
		/**
		 * This method will set the current Image of ImageView currSprite to 
		 * sprite.
		 * 
		 * @param sprite: A WritableImage object containing a player sprite.
		*/
		
		this.spriteView = spriteView;
		
		this.spriteView.setCache(true);
		
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
	
	public void setWorldBox(CollisionBox worldBox) {
		/**
		 * 
		*/
		
		this.worldBox = worldBox;
	}
	
	public void setHurtBox(CollisionBox hurtBox) {
		/*
		 * 
		*/
		
		this.hurtBox = hurtBox;
	}
	
	public void setHitBox(CollisionBox hitBox) {
		/*
		 * 
		*/
		
		this.hitBox = hitBox;
	}
	
	public void setDetectBox(CollisionBox detectBox) {
		/*
		 * 
		*/
		
		this.detectBox = detectBox;
	}
	
	public void setCheckBox(CollisionBox checkBox) {
		/*
		 * 
		*/
		
		this.checkBox = checkBox;
	}
	
	public void setSpriteGroup(Group spriteGroup) {
		/**
		 * 
		*/
		
		this.spriteGroup = spriteGroup;
		
		this.spriteGroup.setCache(true);
	}

//GETTERS--------------------------------------------------------------------------
	
	public int getFramesPerDir() {
		/**
		 * 
		*/
		
		return framesPerDir;
	}
    
	public Image getSpriteSheet() {
		/**
		 * This method will return an Image object of the entire player sprite 
		 * sheet.
		*/
		
		return spriteSheet;
	}
	
	public ImageView getSpriteView() {
		/**
		 * This method will return an ImageView object containing the an Image o
		 * object of the current sprite.
		*/
		
		return spriteView;
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
	
	public WritableImage[] getUpAttkSprite() {
		/**
		 * 
		*/
		
		return upAttkSprites;
	}
	
	public WritableImage[] getLeftAttkSprite() {
		/**
		 * 
		*/
		
		return leftAttkSprites;
	}
	
	public WritableImage[] getDownAttkSprite() {
		/**
		 * 
		*/
		
		return downAttkSprites;
	}
	
	public WritableImage[] getRightAttkSprite() {
		/**
		 * 
		*/
		
		return rightAttkSprites;
	}
	
	public CollisionBox getWorldBox() {
		/**
		 * 
		*/
		
		return worldBox;
	}
	
	public CollisionBox getHurtBox() {
		/*
		 * 
		*/
		
		return hurtBox;
	}
	
	public CollisionBox getHitBox() {
		/*
		 * 
		*/
		
		return hitBox;
	}
	
	public CollisionBox getDetectBox() {
		/*
		 * 
		*/
		
		return detectBox;
	}
	
	public CollisionBox getCheckBox() {
		/*
		 * 
		*/
		
		return checkBox;
	}
	
	public StackPane getCharPane() {
		/**
		 * 
		*/
		
		return charPane;
	}
	
	public Group getSpriteGroup() {
		/**
		 * This method will return a group containing ImageView currSprite.
		*/
		
		return spriteGroup;
	}
	
//HIT-BOX-PLACEMENT----------------------------------------------------------------
	
	public void placeHitBox(double[] coords) {
		/*
		 * 
		*/
		if (!getSpriteGroup().getChildren().contains(getHitBox().getColBox())) {
			getHitBox().setBounds(coords);
			getSpriteGroup().getChildren().add(getHitBox().getColBox());
		}
		
		
	}
	
	public abstract double[] getHitBoxCoords(char dir);

}