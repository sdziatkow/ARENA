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

public class StoneTower extends StaticSprite{
    /**
     *
    */
	
	private double[] colBounds;


//CONSTRUCTORS---------------------------------------------------------------------

    public StoneTower() {
        /**
         * Default Constructor for class
        */
    	
    	super();
    	
    	setSheet("file:sprites/overworld.png");
    	setFrame(new int[] {0, 337, 48, 117});

    	
    	getPane().getChildren().add(getFrame());
    	
		// Initialize CollisionBox bounds and set CollisionBoxes.
		colBounds = new double[] {
				getPane().getBoundsInParent().getCenterX() - 20.0,
				getPane().getBoundsInParent().getCenterY() + 20.0,
				getPane().getBoundsInParent().getCenterX() + 15.2,
				getPane().getBoundsInParent().getCenterY() - 31.0
			};
    	setWorldBoxBounds(colBounds);
    	getGroup().getChildren().add(getPane());
    	getGroup().getChildren().add(getWorldBox().getColBox());

    }

//SETTERS--------------------------------------------------------------------------

//GETTERS--------------------------------------------------------------------------

//

}