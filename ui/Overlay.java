package ui;

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
	private String barStyle;
	
	private Group overlay;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public Overlay() {
		/*
		 * 
		*/
		
		overlay = new Group();
		statPane = new GridPane();
		
		lvlBar = new StatBar();
		statBars = new StatBar[] {
				new StatBar(), 
				new StatBar(), 
				new StatBar(), 
				lvlBar
		};
		
		barStyle =
			      "-fx-border-radius: 0;"
			    + "-fx-background-radius: 0;"
			    + "-fx-text-box-border: transparent;"
			    + "-fx-control-inner-background: transparent;"
			    + "-fx-background-color: rgba(0, 0, 0, 0.1);"
			    + "-fx-padding: 1px;"
			    + "-fx-background-insets: 0;"
			    + "-fx-background-radius: 0;";
		
		statBars[0].getBar().setStyle("-fx-accent: red;" +  barStyle);
		statBars[1].getBar().setStyle("-fx-accent: blue;" +  barStyle);
		statBars[2].getBar().setStyle("-fx-accent: green;" +  barStyle);
		statBars[3].getBar().setStyle("-fx-accent: purple;" +  barStyle);
		
		for (int b = 0; b < statBars.length; ++b) {
			statPane.add(statBars[b].getBar(), 0, b);
		}
		
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
