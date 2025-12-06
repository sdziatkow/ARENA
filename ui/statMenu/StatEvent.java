package ui.statMenu;

/**
 * Program Name:    StatEvent.java
 *<p>
 * Purpose:         The purpose of this program is to handle user input on 
 *                  StatMenu.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import ui.Menus;
import arenaPerson.ArenaAttr;
import arenaPerson.ArenaLevel;
import worldStage.WorldData;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StatEvent {
	/*
	 * 
	*/
	
	
	public static EventHandler<ActionEvent> lvlBtnOnActn;
	public static EventHandler<ActionEvent> attrBtnOnActn;
	
	public StatEvent() {
		/*
		 * 
		*/
		
		attrBtnOnActn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				/*
				 * 
				*/
				
				// Each button on AttrDisplay has CharAttr object as its data.
				Button src = (Button)e.getSource();
				ArenaAttr attr = (ArenaAttr)src.getUserData();
				ArenaLevel lvl = WorldData.persons.get(Menus.ID.get()).lvl();
				
				// If incButton and has skillPoints and less than maxVal
				if (lvl.getSkillPoints() > 0 && attr.getVal() < attr.getMaxVal()) {
					
					if (src.getText().equals("+")) {
						attr.incAttr();
						WorldData.persons.get(Menus.ID.get()).lvl().decSkillPoints();
					} 
				}
				
				// If decButton and greater than minVal
				if (src.getText().equals("-") && attr.getVal() > attr.getMinVal()) {
						attr.decAttr();
						lvl.incSkillPoints();
				}
				
				// Update CharAttr info and reset AttrDisplay.
				attr.setInfo();
				AttrDisplay.setAttrVals();
				
				// Update stat info and reset StatDisplay.
				WorldData.persons.get(Menus.ID.get()).data().updateStats();
				StatDisplay.setStatVals();
				
				// Update Level info and reset LevelDisplay.
				lvl.setInfo();
				LevelDisplay.setLvlVals();
				if (WorldData.persons.get(Menus.ID.get()).lvl().isLvlUpReady()) {
					LevelDisplay.showButton();
				}
				else {
					LevelDisplay.hideButton();
				}
				
			}
		};
		
		lvlBtnOnActn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				// Level up, reset AttrDisplay, and add inc/dec Buttons.
				WorldData.persons.get(Menus.ID.get()).lvl().onLvlUp();
				AttrDisplay.setAttrVals();
				AttrDisplay.showButtons();
				
				// Reset LevelDisplay and add button if can level up again.
				LevelDisplay.setLvlVals();
				if (WorldData.persons.get(Menus.ID.get()).lvl().isLvlUpReady()) {
					LevelDisplay.showButton();
				}
				else {
					LevelDisplay.hideButton();
				}
				
			}
		};

	}
}