package sprite;

/**
 * Program Name:    GroundTileSet.java
 *<p>
 * Purpose:         The purpose of this program is to create a large background
 * 					with tiles for the main backdrop of a WorldStage
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import java.util.Random;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class GroundTileSet {
	/**
	 * 
	*/
	
	// Constant integers describing the amount of rows, columns, and total tiles
	// (JLabels) needed for the JPanel.
	private final int ROWS        = 85;
	private final int COLS        = 85;
	
	private Random grassGen;
	private int nextGrass;
	
	// Will store entire overworld img.
	private Image overworldSheet;
	private Image grass1;
	private Image grass2;
	private ImageView currGrass;
	private ImageView randGrass;
	
	// Will store a ground tile set.
	private GridPane ground;
	
	// Will store the gridPane that stores the ground tile set.
	private Group container;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public GroundTileSet() {
		/**
		 * 
		*/
		
		grassGen = new Random();
		
		overworldSheet = new Image("file:sprites/Overworld.png");
		grass1 = new 
				WritableImage(overworldSheet.getPixelReader(), 273, 465, 30, 30);
		grass2 = new 
				WritableImage(overworldSheet.getPixelReader(), 225, 513, 30, 30);
		
		container = new Group();
		ground = new GridPane();

		for (int row = 0; row < ROWS; ++row) {
			
			for (int col = 0; col < COLS; ++col) {
				
				// Grab a random ImageView object containing grass.
				currGrass = getRandGrass();
				
				// Add the grass to the grid.
				ground.add(currGrass, row, col);
				
				// Ensures there is no extra whitespace between each grass img.
				ground.getRowConstraints().add(new RowConstraints(25));
				
				// Ensures there is no extra whitespace between each grass img.
				ground.getColumnConstraints().add(new ColumnConstraints(25));
			}
		}

		// Add the grid to the Group container.
		container.getChildren().add(ground);
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public ImageView getRandGrass() {
		/**
		 * This method will return a random grass Image being 1 or 2
		*/

		nextGrass = grassGen.nextInt();
		
		if (nextGrass % 2 == 0) {
		
			randGrass = new ImageView(grass1);
		}
		else {
			
			randGrass = new ImageView(grass2);
		}
		
		return randGrass;
	}

//COMPLETED-TILE-PANE--------------------------------------------------------------
	
	public Group getTilePane() {
		/**
		 * This method will return a Group containing a gridPane which contains a
		 * tileSet.
		*/
		
		return container;
	}
	
	

}
