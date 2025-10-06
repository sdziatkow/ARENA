package sprite;

/**
 * Program Name:    StoneTower.java
 *<p>
 * Purpose:         The purpose of this program is to create a stone tower image
 * 					that can be placed anywhere on a WorldStage.
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
import collision.CollisionBox.ColType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;

public class StoneTower{
    /**
     *
    */
	
	private Image overworldSheet;
	
	private Group objGroup;
	private StackPane objPane;
	private ImageView obj;
	private CollisionBox worldBox;
	private double[] worldBoxBounds;


//CONSTRUCTORS---------------------------------------------------------------------

    public StoneTower() {
        /**
         * Default Constructor for class
        */
    	
    	overworldSheet = new Image("file:sprites/overworld.png");
    	
    	objPane = new StackPane();
    	
    	obj = new ImageView(new WritableImage(
    				overworldSheet.getPixelReader(), 0, 337, 48, 117
    				)
    			)
    	;
    	
    	obj.setCache(true);
    	objPane.setCache(true);
    	
    	worldBoxBounds = new double[] {0, 0, 48, 50}; 
    	worldBox = new CollisionBox(ColType.WORLDBOX, worldBoxBounds);
    	
    	objPane.getChildren().add(obj);
    	objPane.getChildren().add(worldBox.getColBox());
    	
    	worldBox.getColBox().setTranslateY(25);
    	
    	objGroup = new Group(objPane);

    }

//SETTERS--------------------------------------------------------------------------

    public void set() {
        /**
         * Setter for field:
        */

    }

//GETTERS--------------------------------------------------------------------------
    
    public CollisionBox getWorldBox() {
    	/**
    	 * 
    	*/
    	
    	return worldBox;
    }
    

    public StackPane getStoneTower() {
        /**
         * This method will return an ImageView object containing a stone tower 
         * image.
         * EXACT COORDS WORK FOR STONE TOWER
         * (0, 337, 48, 117)
        */
    	
    	return objPane;
    }
    
    public Group getObjGroup() {
    	return objGroup;
    }

//

}