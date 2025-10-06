package collision;

/**
 * Program Name:    CollisionBox.java
 *<p>
 * Purpose:         The purpose of this program is to create areas where objects
 * 					within the world can interact with eachother in various ways.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CollisionBox{
    /**
     * 
    */
	
	public enum ColType {
		STAGEBOX,
		HURTBOX,
		HITBOX,
		INTERACTBOX,
		WORLDBOX
	}

	private ColType type;
	private Rectangle colBox;

//CONSTRUCTORS---------------------------------------------------------------------

    public CollisionBox(ColType type, double[] bounds) {
        /**
         * Default Constructor for class
        */
    	
    	this.type = type;
    	
    	colBox = new Rectangle();
    	colBox.setX(bounds[0]);
    	colBox.setY(bounds[1]);
    	colBox.setWidth(bounds[2]);
    	colBox.setHeight(bounds[3]);
    	
    	
    	switch (type) {
    	case STAGEBOX:
    		colBox.setFill(Color.GREY);
    		break;
    	case HURTBOX:
    		colBox.setFill(Color.BLUE);
    		break;
    	case HITBOX:
    		colBox.setFill(Color.RED);
    		break;
    	case INTERACTBOX:
    		colBox.setFill(Color.PURPLE);
    		break;
    	case WORLDBOX:
    		colBox.setFill(Color.BLACK);
    		break;
    		
    	}
    	//colBox.setOpacity(0);
    	colBox.setCache(true);
    }

//SETTERS--------------------------------------------------------------------------

    public void setBounds(double[] bounds) {
        /**
         * Setter for field:
        */

    	colBox.setX(bounds[0]);
    	colBox.setY(bounds[1]);
    	colBox.setWidth(bounds[2]);
    	colBox.setHeight(bounds[3]);
    }
    


//GETTERS--------------------------------------------------------------------------

    public ColType getColType() {
        /**
         * Getter for field:
        */
    	
    	return type;
    }
    
    public Rectangle getColBox() {
    	/**
    	 * Will return Rectangle object.
    	*/
    	
    	return colBox;
    }
    
    public double getMidX() {
    	/**
    	 * Will return x value at center of rectangle.
    	*/
    	
    	return colBox.localToScene(colBox.getBoundsInLocal()).getCenterX();
    }
    
    public double getMidY() {
    	/**
    	 * Will return y value at center of rectangle.
    	*/
    	
    	return colBox.localToScene(colBox.getBoundsInLocal()).getCenterY();
    }
    
    public double getMaxX() {
    	/**
    	 * Will return x value at eastern-most point of rectangle.
    	*/
    	
    	return colBox.localToScene(colBox.getBoundsInLocal()).getMaxX();
    }
    
    public double getMaxY() {
    	/**
    	 * Will return y value at southern-most point of rectangle.
    	*/
    	
    	return colBox.localToScene(colBox.getBoundsInLocal()).getMaxY();
    }
    
    public double getMinX() {
    	/**
    	 * Will return x value at western-most point of rectangle.
    	*/
    	
    	return colBox.localToScene(colBox.getBoundsInLocal()).getMinX();
    }
    
    public double getMinY() {
    	/**
    	 * will return y value of northern-most point of rectangle.
    	*/
    	
    	return colBox.localToScene(colBox.getBoundsInLocal()).getMinY();
    }
    
    public Bounds getBounds() {
    	/**
    	 * Will return a Bounds object of rectangle.
    	*/
    	
    	return colBox.localToScene(colBox.getBoundsInLocal());
    }

//COLLISION------------------------------------------------------------------------
}