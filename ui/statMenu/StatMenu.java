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

import java.util.ArrayList;

import arenaPerson.ArenaAttr;
import arenaPerson.ArenaStat;
import javafx.scene.Group;

public class StatMenu {
	/*
	 * 
	*/
	
	private Group menu;
	private GridPane main;
	
	private AttrDisplay attr;
	private LevelDisplay lvl;
	private StatDisplay stat;
	
	
	public StatMenu() {
		/*
		 * 
		*/
		
		main = new GridPane();
		attr = new AttrDisplay();
		lvl = new LevelDisplay();
		stat = new StatDisplay();
		
		main.add(attr.getMain(), 0, 0);
		main.add(lvl.getMain(),  1, 0);
		main.add(stat.getMain(), 1, 0);
		
		main.getStylesheets().add(
				getClass().getResource("statStyle.css").toExternalForm());
		main.getStyleClass().add("main");
		menu = new Group(main);
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setAttrDisp(ArrayList<ArenaAttr> attrIn) {
		/*
		 * 
		*/
		
		main.getChildren().remove(attr.getMain());
		
		attr = new AttrDisplay();
		attr.setAttrDisp(attrIn);
		main.add(attr.getMain(), 0, 0);
	}
	
	public void setLvlDisp(ArrayList<String> info) {
		/*
		 * 
		*/
		
		main.getChildren().remove(lvl.getMain());
		
		lvl = new LevelDisplay();
		lvl.setLvlDisp(info);
		main.add(lvl.getMain(), 1, 0);
	}
	
	public void setStatDisp(ArrayList<ArenaStat> statIn) {
		/*
		 * 
		*/
		
		main.getChildren().remove(stat.getMain());
		
		stat = new StatDisplay();
		stat.setStatDisp(statIn);
		main.add(stat.getMain(), 0, 1);
	}
	
	
//GETTERS--------------------------------------------------------------------------
	
	public AttrDisplay getAttrDisp() {
		/*
		 * 
		*/
		
		return attr;
	}
	
	public LevelDisplay getLvlDisp() {
		/*
		 * 
		*/
		
		return lvl;
	}
	
	public Group getMenu() {
		/*
		 * 
		*/
		
		return menu;
	}
	

}
