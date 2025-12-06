package ui.statMenu;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import ui.Menus;
import worldStage.WorldData;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
 * Program Name:    AttrDisplay.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's Level.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

public class LevelDisplay {
	/*
	 * 
	*/
	
	public static GridPane main;
	
	public static GridPane allInfo;
	public static ArrayList<Label> infoLabels;
	public static Button lvlUpBtn;
	
	public LevelDisplay() {
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
		
		initLvlDisp();
		initButton();
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public static void initLvlDisp() {
		/*
		 * 
		*/
		
		ArrayList<String> info = WorldData.persons.get(Menus.ID.get()).lvl().getInfo();
		info.addFirst(WorldData.persons.get(Menus.ID.get()).getCharClass().toString());
		info.addFirst("CLASS:");
		info.addFirst(WorldData.persons.get(Menus.ID.get()).getName());
		info.addFirst("NAME:");
		allInfo = new GridPane();
		infoLabels = new ArrayList<Label>();
		
		int col = 0;
		int row = 0;
		
		// For each info String in key(0) : value(1) pair.
		for (int i = 0; i < info.size() - 1; ++i) {
			
			Label data = new Label(info.get(i));
			data.getStyleClass().add("label");
			data.getStyleClass().add("info");
			
			allInfo.add(data, col, row);
			
			// If odd (value), inc to next row at leftmost col.
			if (i % 2 != 0) {
				col = 0;
				++row;
				data.getStyleClass().add("lvl-val");
			}
			else { // Otherwise even (key), keep row, next col over.
				col = 1;
				data.getStyleClass().add("key");
				data.getStyleClass().add("lvl-key");
			}
			infoLabels.add(data);
		}
		
		main.add(allInfo, 0, 1);
	}
	
	public static void setLvlVals() {
		/*
		 * 
		*/
		
		WorldData.persons.get(Menus.ID.get()).lvl().setInfo();
		ArrayList<String> info = WorldData.persons.get(Menus.ID.get()).lvl().getInfo();

		for (int v = 1; v < infoLabels.size(); v += 2) {
			if (v == 1) {
				infoLabels.get(v).setText(WorldData.persons.get(Menus.ID.get()).getName());
			}
			else if (v == 3) {
				infoLabels.get(v).setText(WorldData.persons.get(Menus.ID.get()).getCharClass().toString());
			}
			else {
				infoLabels.get(v).setText(info.get(v - 4));
			}
		}
	}
	
	public static void initButton() {
		/*
		 * 
		*/
		
		lvlUpBtn = new Button("LEVEL-UP");
		lvlUpBtn.getStyleClass().add("lvl-button");
		lvlUpBtn.setOnAction(StatEvent.lvlBtnOnActn);
		hideButton();
		allInfo.add(lvlUpBtn, 0, allInfo.getRowCount() + 1, 2, 1);
	}
	
	public static void hideButton() {
		/*
		 * 
		*/
		
		lvlUpBtn.setVisible(false);
		lvlUpBtn.setDisable(true);
	}
	
	public static void showButton() {
		/*
		 * 
		*/
		
		lvlUpBtn.setVisible(true);
		lvlUpBtn.setDisable(false);
	}
	
//GETTERS--------------------------------------------------------------------------

}
