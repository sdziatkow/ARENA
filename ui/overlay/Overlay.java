package ui.overlay;

import arenaCharacter.CharState;
import arenaCharacter.Level;

/**
 * Program Name:    Overlay.java
 *<p>
 * Purpose:         The purpose of this program is to create an overlay that will
 * 					display the player's information.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class Overlay {
	/*
	 * 
	*/
	
	
	private StatBar lvlBar;
	private StatBar[] statBars;
	private GridPane statPane;
	
	private Group overlay;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public Overlay() {
		/*
		 * 
		*/
		
		overlay = new Group();
		overlay.setCache(true);
		
		statPane = new GridPane();
		statPane.setCache(true);
		
		lvlBar = new StatBar();
		statBars = new StatBar[] {
				new StatBar(), 
				new StatBar(), 
				new StatBar(), 
				lvlBar
		};
		
		statBars[0].getBar().getStyleClass().add("hp-bar");
		statBars[1].getBar().getStyleClass().add("mp-bar");
		statBars[2].getBar().getStyleClass().add("sp-bar");
		statBars[3].getBar().getStyleClass().add("xp-bar");
		
		for (int b = 0; b < statBars.length; ++b) {
			statPane.add(statBars[b].getBar(), 0, b);
		}
		
		statPane.getStyleClass().add("bar-pane");
		overlay.getStylesheets().add(
				getClass().getResource("overlayStyle.css").toExternalForm()
		);
		overlay.getChildren().add(statPane);
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public Group getOverlayGroup() {
		/*
		 * 
		*/
		
		return overlay;
	}
	
	public GridPane getStatPane() {
		/*
		 * 
		*/
		
		return statPane;
	}
	
	public StatBar[] getStatBars() {
		/*
		 * 
		*/
		
		return statBars;
	}
	
//UPDATE---------------------------------------------------------------------------
	
	public void updateStatBars(CharState state, Level lvl) {
		/*
		 * 
		*/
		
		double hpVal;
		double mpVal;
		double spVal;
		double xpVal;
		
		hpVal = state.hp().getVal() / state.hp().getMaxVal();
		mpVal = state.mp().getVal() / state.mp().getMaxVal();
		spVal = state.sp().getVal() / state.sp().getMaxVal();
		xpVal = lvl.getXp() / lvl.getMaxXp();
		
		getStatBars()[0].updateProgress(hpVal);
		getStatBars()[1].updateProgress(mpVal);
		getStatBars()[2].updateProgress(spVal);
		getStatBars()[3].updateProgress(xpVal);
		
	}

}
