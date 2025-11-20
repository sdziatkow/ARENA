package ui.statMenu;

import java.util.ArrayList;

import arenaCharacter.CharAttr;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
	
	private GridPane main;
	
	private GridPane allInfo;
	private Label info;
	private Button lvlUpBtn;
	
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
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setLvlDisp(ArrayList<String> info) {
		/*
		 * 
		*/
		
		if (main.getChildren().contains(allInfo)) {
			main.getChildren().remove(allInfo);
		}
		
		allInfo = new GridPane();
		
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
		}
		
		main.add(allInfo, 0, 1);
	}
	
	public void setLvlUpBtn() {
		/*
		 * 
		*/
		
		if (allInfo.getChildren().contains(lvlUpBtn)) {
			allInfo.getChildren().remove(lvlUpBtn);
		}
		
		lvlUpBtn = new Button("LEVEL-UP");
		lvlUpBtn.getStyleClass().add("lvl-button");
		
		allInfo.add(lvlUpBtn, 0, allInfo.getRowCount() + 1, 2, 1);
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public Button getLvlUpBtn() {
		/*
		 * 
		*/
		
		return lvlUpBtn;
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
