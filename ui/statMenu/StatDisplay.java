package ui.statMenu;


/**
 * Program Name:    StatDisplay.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's Stats.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaPerson.ArenaStat;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import ui.Menus;
import worldStage.WorldData;
import javafx.scene.control.Label;

public class StatDisplay {
	/*
	 * 
	*/
	
	public static GridPane main;
	public static Label header;
	
	public static GridPane allInfo;
	public static ArrayList<Label> infoLabels;
	
	public StatDisplay() {
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
		
		header = new Label("STATS");
		header.getStyleClass().add("label");
		header.getStyleClass().add("header");
		
		main.add(header, 0, 0, 4, 1);
		
		initStatDisp();
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public static void initStatDisp() {
		/*
		 * 
		*/
		
		ArrayList<ArenaStat> statIn = WorldData.persons.get(Menus.ID.get()).data().getAllStats();
		allInfo = new GridPane();
		infoLabels = new ArrayList<Label>();
		
		int col;
		int row = 0;
		
		// For each Stat Object.
		ArenaStat currStat;
		ArrayList<String> currInfo;
		for (int s = 0; s < statIn.size(); ++s) {
			
			currStat = statIn.get(s);
			
			// For each info String in key(0) : value(1) pair.
			currInfo = currStat.getInfo();
			for (int i = 0; i < currInfo.size(); ++i) {
				Label info = new Label(currInfo.get(i));
				info.getStyleClass().add("label");
				info.getStyleClass().add("info");
				
				// Add color based on which stat it is 
				// (Currently only for HP SP MP).
				if (s < 3) {
					info.getStyleClass().add(currStat.getInfo().get(0).toLowerCase().substring(0, 2));
				}
				
				// if even (key), add to leftmost col.
				if (i % 2 == 0) {
					col = 0;
					info.getStyleClass().add("key");
					info.getStyleClass().add("stat-key");
				}
				else { // Otherwise, (val) add to right col.
					col = 1;
					info.getStyleClass().add("stat-val");
				}
				
				infoLabels.add(info);
				allInfo.add(info, col, row);
			}
			
			// Inc to next row for next Stat.
			++row;
		}
		
		main.add(allInfo, 0, 1);
	}
	
	public static void setStatVals() {
		/*
		 * 
		*/
		
		ArenaStat currStat;
		for (int v = 1; v < infoLabels.size() ; v += 2) {
			currStat = WorldData.persons.get(Menus.ID.get()).data().getAllStats().get(v / 2);
			infoLabels.get(v).setText(currStat.getInfo().get(v % 2));
		}
	}
	
//GETTERS--------------------------------------------------------------------------
}