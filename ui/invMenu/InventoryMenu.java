package ui.invMenu;

/**
 * Program Name:    InventoryMenu.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's Backpack and EuipSlots.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 14, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.layout.GridPane;

public class InventoryMenu {
	/*
	 * 
	*/
	
	public static GridPane main;
	
	public InventoryMenu() {
		/*
		 * 
		*/
		
		main = new GridPane();
		
		initBpMenu();
		initEqMenu();
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public static void initBpMenu() {
		/*
		 * 
		*/
		
		new BpMenu();
		
		main.add(BpMenu.main, 0, 0);
	}
	
	public static void initEqMenu() {
		/*
		 * 
		*/
		
		new EquipMenu();
		
		main.add(EquipMenu.main, 1, 0);
	}
	
	
//GETTERS--------------------------------------------------------------------------

}
