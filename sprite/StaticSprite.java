package sprite;

/**
 * Program Name:    StaticSprite.java
 *<p>
 * Purpose:         The purpose of this program is to create a base parent class
 * 					for all sprites that do not move.
 *<p>
 * @version         0.0
 *<p>
 * Created:         December 01, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaEnum.ColType;
import collision.CollisionBox;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class StaticSprite {
	/**
	 * @field spriteSheet: Contains file with entire sprite sheet.
	 * @field spriteView:  Contains WritableImage of frame taken from spriteSheet.
	 * @field spritePane:  Can contain multiple spriteViews.
	 * @field worldBox:    Determines where moving sprites will collide with this.
	 * @field spriteGroup: Contains spritePane and worldBox.
	 * @field pos:         Property that is bound to spriteGroup's scene position.
	*/
	
	private Image spriteSheet;
	private ImageView spriteView;
	private StackPane spritePane;
	
	private CollisionBox worldBox;
	
	private Group spriteGroup;
	
	private DoubleProperty[] pos;
	
	public StaticSprite() {
		/*
		 * 
		*/
		
		spriteSheet = null;
		spriteView = new ImageView();
		spritePane = new StackPane();
		worldBox = new CollisionBox(ColType.WORLDBOX, new double[] {0.0, 0.0, 1.0, 1.0});
		spriteGroup = new Group();
		
		pos = new DoubleProperty[] {new SimpleDoubleProperty(0.0), new SimpleDoubleProperty(0.0)};
		
		spriteGroup.translateXProperty().bind(pos[0]);
		spriteGroup.translateYProperty().bind(pos[1]);
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setSheet(String path) {
		/*
		 * 
		*/
		
		spriteSheet = new Image(path);
	}
	
	public void setFrame(int[] bounds) {
		/*
		 * 
		*/
		
		spriteView.setImage(new WritableImage(
				getSheet().getPixelReader(),
				bounds[0],
				bounds[1],
				bounds[2],
				bounds[3]
		));
	}
	
	public void setWorldBoxBounds(double[] bounds) {
		/*
		 * 
		*/
		
		worldBox.setBounds(bounds);
	}
	
	public void setPos(int x, int y) {
		/*
		 * 
		*/
		
		pos[0].set(x);
		pos[1].set(y);
	}

//GETTERS--------------------------------------------------------------------------
	
	public Image getSheet() {
		/*
		 * 
		*/
		
		return spriteSheet;
	}
	
	public ImageView getFrame() {
		/*
		 * 
		*/
		
		return spriteView;
	}
	
	public StackPane getPane() {
		/*
		 * 
		*/
		
		return spritePane;
	}
	
    public CollisionBox getWorldBox() {
    	/**
    	 * 
    	*/
    	
    	return worldBox;
    }
	
    public Group getGroup() {
    	/*
    	 * 
    	*/
    	
    	return spriteGroup;
    }
    
    public DoubleProperty getPos(int axis) {
    	/*
    	 * 
    	*/
    	
    	return pos[axis];
    }
}
