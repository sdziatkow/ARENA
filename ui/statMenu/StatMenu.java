package ui.statMenu;

/**
 * Program Name:    StatMenu.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's CharState and Level.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 17, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.layout.GridPane;

public class StatMenu {
	/*
	 * 
	*/
	
	public static GridPane main;
	
	
	public StatMenu() {
		/*
		 * 
		*/
		
		main = new GridPane();
		
		main.getStylesheets().add(
				getClass().getResource("statStyle.css").toExternalForm());
		main.getStyleClass().add("main");
		
		initAttrDisp();
		initLvlDisp();
		initStatDisp();
		
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public static void initAttrDisp() {
		/*
		 * 
		*/
		
		new AttrDisplay();
		main.add(AttrDisplay.main, 0, 0);
	}
	
	public static void initLvlDisp() {
		/*
		 * 
		*/
		
		new LevelDisplay();
		main.add(LevelDisplay.main, 1, 0);
	}
	
	public static void initStatDisp() {
		/*
		 * 
		*/
		
		new StatDisplay();
		main.add(StatDisplay.main, 0, 1);
	}
	
	
//GETTERS--------------------------------------------------------------------------
	

}
