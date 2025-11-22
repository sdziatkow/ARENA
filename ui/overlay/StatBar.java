package ui.overlay;

/**
 * Program Name:    StatBar.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's Stat.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.control.ProgressBar;

public class StatBar {
	/*
	 * 
	*/
	
	private ProgressBar statBar;
	
//CONSTRUCTORS----------------------------------------------------------------------
	
	public StatBar() {
		/*
		 * 
		*/
		
		statBar = new ProgressBar(0);
		
		statBar.setMinWidth(230);
		statBar.setMaxHeight(12);
		statBar.getStylesheets().add(
				getClass().getResource("overlayStyle.css").toExternalForm()
		);
		statBar.getStyleClass().add("progress-bar");
		
		statBar.setCache(true);
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public ProgressBar getBar() {
		/*
		 * 
		*/
		
		return statBar;
	}
	
//MODIFY---------------------------------------------------------------------------
	
	public void updateProgress(double val) {
		/*
		 * 
		*/
		
		statBar.setProgress(val);
	}

}
