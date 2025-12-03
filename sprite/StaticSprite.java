package sprite;

import arenaEnum.ColType;
import collision.CollisionBox;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;

public class StaticSprite {
	/*
	 * 
	*/
	
	private Image spriteSheet;
	private ImageView spriteView;
	private StackPane spritePane;
	
	private CollisionBox worldBox;
	private double[] worldBoxBounds;
	
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
