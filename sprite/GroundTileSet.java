package sprite;

/**
 * Program Name:    ArenaCharacter.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 27, 2025
 *<p>
 * Updated:         May 12, 2025
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
	private final int ROWS        = 100;
	private final int COLS        = 100;
	
	// Will store entire overworld img.
	private Image overworldSheet;
	
	// Will store a ground tile set.
	private GridPane ground;
	
	// Will store the gridPane that stores the ground tile set.
	private Group container;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public GroundTileSet() {
		/**
		 * 
		*/
		
		overworldSheet = new Image("file:sprites/Overworld.png");
		
		container = new Group();
		ground = new GridPane();

		for (int row = 0; row < ROWS; ++row) {
			
			for (int col = 0; col < COLS; ++col) {
				
				// Grab a random ImageView object containing grass.
				ImageView grass = getRandGrass();
				
				// Add the grass to the grid.
				ground.add(grass, row, col);
				
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
		
		Random randGen = new Random();
		int num = randGen.nextInt();
		
		ImageView grass;
		
		if (num % 2 == 0) {
		
			grass = new ImageView(getGrass1());
		}
		else {
			
			grass = new ImageView(getGrass2());
		}
		
		grass.setCache(true);
		
		return grass;
	}
	
	private Image getGrass1() {
		/**
		 * This method will return sub image of 30x30 grass (plain).
		 * CURRENT COORDS WORK EXACTLY WITH GRASS 2
		 * 
		 * 273, 465, 30, 30
		*/
		
		Image grass1 = new 
				WritableImage(overworldSheet.getPixelReader(), 273, 465, 30, 30);
		
		return grass1;

	}
	
	private Image getGrass2() {
		/**
		 * This method will return sub image of 30x30 grass (raised center).
		 * CURRENT COORDS WORK EXACTLY WITH GRASS 1
		 * 225, 513, 30, 30
		*/
		
		Image grass2 = new 
				WritableImage(overworldSheet.getPixelReader(), 225, 513, 30, 30);
		
		return grass2;

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
