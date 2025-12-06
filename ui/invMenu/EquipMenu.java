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
import ui.Menus;
import worldStage.WorldData;

import java.util.ArrayList;

import item.Item;
import arenaEnum.itemInfo.ItemType;
import javafx.scene.control.Label;

public class EquipMenu {
	/*
	 * 
	*/
	
	public static GridPane main;
	public static Label header;
	
	public static Label type;
	
	public static ArrayList<Label> nameLabels;
	public static ArrayList<ArrayList<Label>> infoLabels;
	
	
	
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
		
		initEq();
		clearEq();
		setEq();
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public static void initEq() {
		/*
		 * This method will set the display to show information of the given items. 
		*/
		
		nameLabels = new ArrayList<Label>();
		infoLabels = new ArrayList<ArrayList<Label>>();
		
		final int TOTAL_INFO_LABELS = 11;
		
		Label name;
		GridPane itemInfo;
		ArrayList<Label> info;
		
		// For Each Item.
		for (int n = 0; n < 3; ++n) {	
		
			// Create label for item name and add it to left-most col.
			name = new Label();
			name.getStyleClass().add("eq-label");
			name.getStyleClass().add("eq-info-name");
			nameLabels.add(name);
			main.add(name, 1, n + 1);
			
			// Create a new GridPane to store all the item info.
			itemInfo = new GridPane();
			itemInfo.getStyleClass().add("eq-info");
			
			// For each key / name pair.
			Label field;
			info = new ArrayList<Label>();
			for (int f = 1; f < TOTAL_INFO_LABELS; ++f) {
				field = new Label();
				field.getStyleClass().add("eq-label");
				
				if (f % 2 != 0) {
					field.getStyleClass().add("eq-info-field");
					itemInfo.add(field, 0, f - 1);
				}
				else {
					
					field.getStyleClass().add("eq-info-data");
					itemInfo.add(field, 1, f - 2);
				}
				info.add(field);
			}
			infoLabels.add(info);
			
			main.add(itemInfo, 2, n + 1);
		}
	}
	
	public static void setEq() {
		/*
		 * 
		*/
		
		ArrayList<Item> eq = WorldData.eqSlots.get(Menus.ID.get()).getEquipped();
		Item currEq;
		ArrayList<String> currInfo;
		
		for (int n = 0; n < nameLabels.size(); ++n) {
			currEq = eq.get(n);
			
			if (currEq != null) {
				currInfo = currEq.getInfo();
				nameLabels.get(n).setText(currInfo.get(0));
				
				for (int i = 1; i < currInfo.size(); ++i) {
					infoLabels.get(n).get(i - 1).setText(currInfo.get(i));
				}
			}		
		}
	}
	
	public static void clearEq() {
		/*
		 * 
		*/
		
		for (int n = 0; n < nameLabels.size(); ++n) {
			nameLabels.get(n).setText("");
		}		
		
		for (int n = 0; n < infoLabels.size(); ++n) {
			for (int i = 0; i < infoLabels.get(n).size(); ++i) {
				infoLabels.get(n).get(i).setText("");
			}
		}
	}
}
