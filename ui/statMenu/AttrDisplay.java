package ui.statMenu;

/**
 * Program Name:    AttrDisplay.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's CharAttr.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaPerson.ArenaAttr;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import ui.Menus;
import worldStage.WorldData;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class AttrDisplay {
	/*
	 * 
	*/
	
	public static GridPane main;
	public static Label header;
	
	public static GridPane allInfo;
	public static ArrayList<Label> infoLabels;
	
	public static Button incButton;
	public static Button decButton;
	public static ArrayList<ArrayList<Button>> buttons;
	
	
	public AttrDisplay() {
		/*
		 * 
		*/
		
		init();
	}
	
	public void init() {
		/*
		 * 
		*/
		
		// HOLDS WHOLE DISPLAY.
		main = new GridPane();
		main.getStylesheets().add(
				getClass().getResource("statStyle.css").toExternalForm());
		main.getStyleClass().add("default-main");
		
		header = new Label("ATTRIBUTES");
		header.getStyleClass().add("label");
		header.getStyleClass().add("header");
		
		main.add(header, 0, 0, 4, 1);
		
		// KEY-VAL ATTR INFO.
		infoLabels = new ArrayList<Label>();
		initAttrDisp();
		
		// LVL UP BUTTONS.
		buttons = new ArrayList<ArrayList<Button>>();
		initButtons();
		hideButtons();
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public static void initAttrDisp() {
		/*
		 * 
		*/
		
		allInfo = new GridPane();
		
		int col;
		int row = 0;
		
		// For each CharAttr Object.
		ArenaAttr currAttr;
		ArrayList<String> currInfo;
		for (int a = 0; a < WorldData.persons.get(Menus.ID.get()).data().getAllAttr().size(); ++a) {
			
			currAttr = WorldData.persons.get(Menus.ID.get()).data().getAllAttr().get(a);
			
			// For each info String in key(0) : value(1) pair. 
			currInfo = currAttr.getInfo();
			for (int i = 0; i < currInfo.size(); ++i) {
				Label info = new Label(currInfo.get(i));
				info.getStyleClass().add("label");
				info.getStyleClass().add("info");
				
				// If even, put on leftmost col (key) and set user data to 
				// CharAttr Object.
				if (i % 2 == 0) {
					col = 0;
					info.getStyleClass().add("key");
					info.getStyleClass().add("attr-key");
					info.setUserData(currAttr);
				}
				else { // Otherwise put on right col (val)
					col = 1;
					info.getStyleClass().add("attr-val");
				}
				
				infoLabels.add(info);
				allInfo.add(info, col, row);
			}
			
			// Increment row for next CharAttr object.
			++row;
		}
		
		main.add(allInfo, 0, 1);
	}
	
	public static void setAttrVals() {
		/*
		 * 
		*/
				
		ArenaAttr currAttr;
		for (int v = 1; v < infoLabels.size() ; v += 2) {
			currAttr = WorldData.persons.get(Menus.ID.get()).data().getAllAttr().get(v / 2);
			infoLabels.get(v).setText(currAttr.getInfo().get(v % 2));
		}
	}
	
	public static void initButtons() {
		/*
		 * 
		*/
		
		ArrayList<Button> btnSet;
		int totalRows = allInfo.getRowCount();
		int attrIdx = 0;
		
		// For each in allInfo.
		for (int b = 0; b < totalRows; ++b) {
			
			// Init dec/inc Button.
			decButton = new Button("-");
			incButton = new Button("+");
			
			// Set user data to the user data of the key in the row 
			// (see setAttrDisp() for when key's user data is set).
			decButton.setUserData(allInfo.getChildren().get(attrIdx).getUserData());
			incButton.setUserData(allInfo.getChildren().get(attrIdx).getUserData());
			
			decButton.getStyleClass().add("attr-button");
			incButton.getStyleClass().add("attr-button");
			
			decButton.setOnAction(StatEvent.attrBtnOnActn);
			incButton.setOnAction(StatEvent.attrBtnOnActn);
			
			// Init arrayList for both buttons and add arrayList to master list.
			btnSet = new ArrayList<Button>();
			btnSet.add(decButton);
			btnSet.add(incButton);
			buttons.add(btnSet);
			
			// Increment attrIdx by two to get the next key's child idx for user data.
			attrIdx += 2;
			
			allInfo.add(decButton, 2, b);
			allInfo.add(incButton, 3, b);
		}
	}
	
	public static void showButtons() {
		/*
		 * 
		*/
		
		for (int a = 0; a < buttons.size(); ++a) {
			for (int b = 0; b < buttons.get(a).size(); ++b) {
				Menus.show(buttons.get(a).get(b));
			}
		}
	}
	
	public static void hideButtons() {
		/*
		 * 
		*/
		
		for (int a = 0; a < buttons.size(); ++a) {
			for (int b = 0; b < buttons.get(a).size(); ++b) {
				Menus.hide(buttons.get(a).get(b));
			}
		}
	}
	
//GETTERS--------------------------------------------------------------------------
	

}