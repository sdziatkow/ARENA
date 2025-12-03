package sprite;

/**
 * Program Name:    WeaponSprite.java
 *<p>
 * Purpose:         The purpose of this program is to create a system that all
 * 					will separate a spritesheet into frames.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 07, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class FrameGen {
	/**
	 * 
	*/
	
	private Image sheet;
	private WritableImage frame;
	
	private int initialXOffset;
	private int xOffset;
	private int yOffset;
	private int frameWidth;
	private int frameHeight;
	private int totalDirections;
	private int[] framesPerDirection;
	
	private WritableImage[][] allFrames;
	

//CONSTRUCTORS---------------------------------------------------------------------
	
	public FrameGen() {
		/*
		 * Default constructor for class FrameGen. 
		*/
		
		sheet = null;
		frame = null;
		initialXOffset = 0;
		xOffset = 0;
		yOffset = 0;
		frameWidth = 0;
		frameHeight = 0;
		totalDirections = 0;
		framesPerDirection = null;
	}
	
	public FrameGen(Image sheet) {
		/*
		 * Default constructor for class FrameGen. 
		*/
		
		this.sheet = sheet;
		frame = null;
		initialXOffset = 0;
		xOffset = 0;
		yOffset = 0;
		frameWidth = 0;
		frameHeight = 0;
		totalDirections = 0;
		framesPerDirection = null;
	}
	
	public FrameGen(
		Image sheet, 
		int xOffset, 
		int yOffset, 
		int frameWidth, 
		int frameHeight, 
		int totalDirections, 
		int[] framesPerDirection
	){
		/*
		 * Default constructor for class FrameGen. 
		*/
		
		this.sheet = null;
		frame = null;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.totalDirections = totalDirections;
		this.framesPerDirection = framesPerDirection;
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void set() {
		/*
		 * Setter for field:  
		*/
	}
	
	public void setSheet(Image sheet) {
		/*
		 * Setter for field: sheet.
		*/
		
		this.sheet = sheet;
	}
	
	public void setFrame(WritableImage frame) {
		/*
		 * Setter for field: frame
		*/
		
		this.frame = frame;
	}
	
	public void setInitialXOffset(int xOffset) {
		/*
		 * Setter for field: xOffset.
		*/
		
		this.initialXOffset = xOffset;
	}
	
	public void setXOffset(int xOffset) {
		/*
		 * Setter for field: xOffset.
		*/
		
		this.xOffset = xOffset;
	}
	
	public void setYOffset(int yOffset) {
		/*
		 * Setter for field: yOffset.
		*/
		
		this.yOffset = yOffset;
	}
	
	public void setFrameWidth(int frameWidth) {
		/*
		 * Setter for field: frameWidth.
		*/
		
		this.frameWidth = frameWidth;
	}
	
	public void setFrameHeight(int frameHeight) {
		/*
		 * Setter for field: frameHeight.
		*/
		
		this.frameHeight = frameHeight;
	}
	
	public void setTotalDirections(int totalDirections) {
		/*
		 * Setter for field: totalDirections.
		*/
		
		this.totalDirections = totalDirections;
	}
	
	public void setFramesPerDirection(int[] framesPerDirection) {
		/*
		 * Setter for field: framesPerDirection.
		*/
		
		this.framesPerDirection = framesPerDirection;
	}
	
	
//GETTERS--------------------------------------------------------------------------
	
	public void get() {
		/*
		 * Getter for field:  
		*/
	}
	
	public Image getSheet() {
		/*
		 * Getter for field:  
		*/
		
		return sheet;
	}
	
	public WritableImage getFrame() {
		/*
		 * Getter for field:  
		*/
		
		return frame;
	}
	
	public int getInitialXOffset() {
		/*
		 * Getter for field:  
		*/
		
		return initialXOffset;
	}
	
	public int getXOffset() {
		/*
		 * Getter for field:  
		*/
		
		return xOffset;
	}
	
	public int getYOffset() {
		/*
		 * Getter for field:  
		*/
		
		return yOffset;
	}
	
	public int getFrameWidth() {
		/*
		 * Getter for field:  
		*/
		
		return frameWidth;
	}
	
	public int getFrameHeight() {
		/*
		 * Getter for field:  
		*/
		
		return frameHeight;
	}
	
	public int getTotalDirections() {
		/*
		 * Getter for field:  
		*/
		
		return totalDirections;
	}
	
	public int[] getFramesPerDirection() {
		/*
		 * Getter for field:  
		*/
		
		return framesPerDirection;
	}
	
	public WritableImage[][] getAllFrames() {
		/*
		 * Getter for field:  
		*/
		
		return allFrames;
	}
	
//GEN------------------------------------------------------------------------------
	
	public void genFrames() {
		/*
		 * 
		*/

		allFrames = new WritableImage[totalDirections][];
		
		// For each direction.
		for (int d = 0; d < allFrames.length; ++d) {
			allFrames[d] = new WritableImage[getFramesPerDirection()[d]];
			
			// Add each 
			for (int f = 0; f < allFrames[d].length; ++f) {
				
				allFrames[d][f] = new WritableImage(
						sheet.getPixelReader(),
						getXOffset() + (getFrameWidth() * f),
						getYOffset() + (getFrameHeight() * d),
						getFrameWidth(),
						getFrameHeight()								
				);
			}
		}
	}
	
	public void genFrames(boolean skipBetweenX) {
		/*
		 * 
		*/
		allFrames = new WritableImage[totalDirections][];
		
		// For each direction.
		for (int d = 0; d < allFrames.length; ++d) {
			allFrames[d] = new WritableImage[getFramesPerDirection()[d]];
			
			// Add each 
			for (int f = 0; f < allFrames[d].length * 2; ++f) {
				
				if (f % 2 == 0) {
					allFrames[d][f / 2] = new WritableImage(
							sheet.getPixelReader(),
							getXOffset() + (getFrameWidth() * f),
							getYOffset() + (getFrameHeight() * d),
							getFrameWidth(),
							getFrameHeight()								
					);
				}
			}
		}
	}
	
	public void genFrames(boolean skipBetweenX, boolean skipBetweenTwoY) {
		/*
		 * 
		*/
		
		allFrames = new WritableImage[totalDirections][];
		
		// For each direction.
		for (int d = 0; d < allFrames.length * 2.5; ++d) {
			if (d % 3 == 0) {
			
				allFrames[d / 3] = new WritableImage[getFramesPerDirection()[d / 3]];
			
			
				// Add each 
				for (int f = 0; f < allFrames[d / 3].length * 2; ++f) {
					
					if (f % 2 == 0) {
						allFrames[d / 3][f / 2] = new WritableImage(
								sheet.getPixelReader(),
								getXOffset() + (getFrameWidth() * f),
								getYOffset() + (getFrameHeight() * d),
								getFrameWidth(),
								getFrameHeight()								
						);
					}
				}
			}
		}
	}
}
