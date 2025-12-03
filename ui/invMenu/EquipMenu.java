package ui.invMenu;

/**
 * Program Name:    EquipMenu.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's EquipSlots.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import item.Item;
import arenaEnum.itemInfo.ItemType;
import javafx.scene.control.Label;

public class EquipMenu {
	/*
	 * 
	*/
	
	private GridPane main;
	private Label header;
	
	private Label type;
	
	private Label wName;
	private Label aName;
	private Label uName;
	
	private GridPane wInfo;
	private GridPane aInfo;
	private GridPane uInfo;
	
	public EquipMenu() {
		/*
		 * 
		*/
		
		init();
	}
	
	public void init() {
		/*
		 * This method will create a display of all items equipped by the
		 * ArenaCharacter's EquipSlot. 
		*/
		
		// Main GridPane that holds everything.
		main = new GridPane();
		main.getStylesheets().add(
				getClass().getResource("eqMenuStyle.css").toExternalForm());
		main.getStyleClass().add("eq-main");
		
		// Header displayed along top.
		header = new Label("EQUIP SLOTS");
		header.getStyleClass().add("eq-label");
		header.getStyleClass().add("eq-header");
		main.add(header, 0, 0, 5, 1);
		
		// A label for each ItemType equippable by ArenaCharacter.
		type = new Label(ItemType.WEAPON.toString());
		type.getStyleClass().add("eq-label");
		type.getStyleClass().add("eq-type");
		main.add(type, 0, 1);
		
		type = new Label(ItemType.ARMOR.toString());
		type.getStyleClass().add("eq-label");
		type.getStyleClass().add("eq-type");
		main.add(type, 0, 2);
		
		type = new Label(ItemType.USEABLE.toString());
		type.getStyleClass().add("eq-label");
		type.getStyleClass().add("eq-type");
		main.add(type, 0, 3);
		
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setEq(ArrayList<Item> items) {
		/*
		 * This method will set the display to show information of the given items. 
		*/
		
		int row = -1;
		Label name;
		GridPane itemInfo;
		Item currItem;
		
		// For Each Item.
		for (int i = 0; i < items.size(); ++i) {	
			currItem = items.get(i);
			
			if (currItem != null) {
				
				// Set the row and display objects depending on type.
				switch (currItem.getItemType()) {
				case WEAPON:
					row = 1;
					name = wName;
					itemInfo = wInfo;
					break;
				case ARMOR:
					row = 2;
					name = aName;
					itemInfo = aInfo;
					break;
				case USEABLE:
					row = 3;
					name = uName;
					itemInfo = uInfo;
					break;
				default:
					return;
				}
			
			
				// Create label for item name and add it to left-most col.
				name = new Label(currItem.getInfo().get(0));
				name.getStyleClass().add("eq-label");
				name.getStyleClass().add("eq-info-name");
				main.add(name, 1, row);
				
				// Create a new GridPane to store all the item info.
				itemInfo = new GridPane();
				itemInfo.getStyleClass().add("eq-info");
				Label field = new Label();
				
				// For each key / name pair.
				for (int f = 1; f < currItem.getInfo().size(); ++f) {
					field =  new Label(currItem.getInfo().get(f));
					field.getStyleClass().add("eq-label");
					
					if (f % 2 != 0) {
						field.getStyleClass().add("eq-info-field");
						itemInfo.add(field, 0, f - 1);
					}
					else {
						
						field.getStyleClass().add("eq-info-data");
						itemInfo.add(field, 1, f - 2);
						
					}
				}
				
				main.add(itemInfo, 2, row);
			}
		}
		
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public GridPane getMain() {
		/*
		 * 
		*/
		
		return main;
	}
	

}
