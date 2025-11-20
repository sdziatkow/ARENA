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

import java.util.ArrayList;

import item.Item;
import item.Item.ItemType;
import javafx.collections.ObservableList;

/**
 * Program Name:    PlayerMenu.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's CharState
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

public class InventoryMenu {
	/*
	 * 
	*/
	
	private Group menu;
	private GridPane main;
	private BpMenu bpMenu;
	private EquipMenu eqMenu;
	
	public InventoryMenu() {
		/*
		 * 
		*/
		
		main = new GridPane();
		
		bpMenu = new BpMenu();
		eqMenu = new EquipMenu();
		main.add(bpMenu.getMain(), 0, 0);
		
		menu = new Group(main);
		
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void resetBpMenu() {
		/*
		 * 
		*/
		
		main.getChildren().remove(bpMenu);
		
		bpMenu = new BpMenu();
		
		main.add(bpMenu.getMain(), 0, 0);
	}
	
	public void resetEqMenu(ArrayList<Item> items) {
		/*
		 * 
		*/
		
		main.getChildren().remove(eqMenu);
		
		eqMenu = new EquipMenu();
		eqMenu.setEq(items);
		
		main.add(eqMenu.getMain(), 1, 0);
	}
	
	
//GETTERS--------------------------------------------------------------------------
	
	public BpMenu getBpMenu() {
		/*
		 * 
		*/
		
		return bpMenu;
	}
	
	public EquipMenu getEqMenu() {
		/*
		 * 
		*/
		
		return eqMenu;
	}
	
	public Group getMenu() {
		/*
		 * 
		*/
		
		return menu;
	}

}
