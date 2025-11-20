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

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import arenaCharacter.CharAttr;
import arenaCharacter.CharAttr.Attribute;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AttrDisplay {
	/*
	 * 
	*/
	
	private GridPane main;
	private Label header;
	
	private GridPane allInfo;
	private Label info;
	
	private Button incButton;
	private Button decButton;
	private ArrayList<ArrayList<Button>> buttons;
	
	
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
		
		main = new GridPane();
		main.getStylesheets().add(
				getClass().getResource("statStyle.css").toExternalForm());
		main.getStyleClass().add("default-main");
		
		header = new Label("ATTRIBUTES");
		header.getStyleClass().add("label");
		header.getStyleClass().add("header");
		
		main.add(header, 0, 0, 4, 1);
		
		buttons = new ArrayList<ArrayList<Button>>();
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setAttrDisp(ArrayList<CharAttr> attrIn) {
		/*
		 * 
		*/
		
		if (main.getChildren().contains(allInfo)) {
			main.getChildren().remove(allInfo);
		}
		
		allInfo = new GridPane();
		
		int col;
		int row = 0;
		
		// For each CharAttr Object.
		CharAttr currAttr;
		ArrayList<String> currInfo;
		for (int a = 0; a < attrIn.size(); ++a) {
			
			currAttr = attrIn.get(a);
			
			// For each info String in key(0) : value(1) pair. 
			currInfo = currAttr.getInfo();
			for (int i = 0; i < currInfo.size(); ++i) {
				info = new Label(currInfo.get(i));
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
				
				allInfo.add(info, col, row);
			}
			
			// Increment row for next CharAttr object.
			++row;
		}
		
		main.add(allInfo, 0, 1);
	}
	
	public void setButtons() {
		/*
		 * 
		*/
		
		ArrayList<Button> btnSet;
		int totalRows = getAllInfo().getRowCount();
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
	
//GETTERS--------------------------------------------------------------------------
	
	public ArrayList<ArrayList<Button>> getButtons() {
		/*
		 * 
		*/
		
		return buttons;
	}
	
	public GridPane getAllInfo() {
		/*
		 * 
		*/
		
		return allInfo;
	}
	
	public GridPane getMain() {
		/*
		 * 
		*/
		
		return main;
	}
	

}