package ui.statMenu;

/**
 * Program Name:    StatDisplay.java
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

import javafx.scene.control.Button;

/**
 * Program Name:    StatEvent.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's CharState.
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
import worldStage.WorldStage;

import java.util.ArrayList;

import arenaCharacter.ArenaCharacter;
import arenaCharacter.CharAttr;
import arenaCharacter.Stat.StatType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StatEvent {
	/*
	 * 
	*/
	
	private EventHandler<ActionEvent> lvlBtnOnActn;
	private EventHandler<ActionEvent> attrBtnOnActn;
	
	// This is for whether or not to show AttrDisplay buttons.
	private boolean keepShowing;
	
	public StatEvent(
			WorldStage stage,
			StatMenu menu,
			ArenaCharacter player
		) {
		/*
		 * 
		*/
		
		keepShowing = false;
		
		attrBtnOnActn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				/*
				 * 
				*/
				
				// Each button on AttrDisplay has CharAttr object as its data.
				Button src = (Button)e.getSource();
				CharAttr attr = (CharAttr)src.getUserData();
				
				// If incButton and has skillPoints and less than maxVal
				if (player.lvl().getSkillPoints() > 0 && attr.getVal() < attr.getMaxVal()) {
					
					if (src.getText().equals("+")) {
						attr.incAttr();
						player.lvl().decSkillPoints();
					} 
				}
				
				// If decButton and greater than minVal
				if (src.getText().equals("-") && attr.getVal() > attr.getMinVal()) {
						attr.decAttr();
						player.lvl().incSkillPoints();
				}
				
				// Update CharAttr info and reset AttrDisplay.
				attr.setInfo();
				menu.setAttrDisp(player.getStats().getAllAttr());
				
				// If still showing buttons, add them again and set the handler.
				if (keepShowing) {
					menu.getAttrDisp().setButtons();
					
					ArrayList<Button> currSet;
					for (int s = 0; s < menu.getAttrDisp().getButtons().size(); ++s) {
						currSet = menu.getAttrDisp().getButtons().get(s);
						
						for (int b = 0; b < currSet.size(); ++b) {
							currSet.get(b).setOnAction(attrBtnOnActn);
						}
					}
				}
				
				// Update stat info and reset StatDisplay.
				player.getStats().updateStats();
				menu.setStatDisp(player.getStats().getAllStats());
				
				// Update Level info and reset LevelDisplay.
				player.lvl().setInfo();
				menu.setLvlDisp(player.lvl().getInfo());
				if (player.lvl().isLvlUpReady()) {
					menu.getLvlDisp().setLvlUpBtn();
					menu.getLvlDisp().getLvlUpBtn().setOnAction(lvlBtnOnActn);
				}
				
			}
		};
		
		lvlBtnOnActn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				// Level up, reset AttrDisplay, and add inc/dec Buttons.
				player.lvl().onLvlUp();
				menu.setAttrDisp(player.getStats().getAllAttr());
				menu.getAttrDisp().setButtons();
				
				ArrayList<Button> currSet;
				for (int s = 0; s < menu.getAttrDisp().getButtons().size(); ++s) {
					currSet = menu.getAttrDisp().getButtons().get(s);
					
					for (int b = 0; b < currSet.size(); ++b) {
						currSet.get(b).setOnAction(attrBtnOnActn);
					}
				}
				
				// Reset LevelDisplay and add button if can level up again.
				menu.setLvlDisp(player.lvl().getInfo());
				if (player.lvl().isLvlUpReady()) {
					menu.getLvlDisp().setLvlUpBtn();
					menu.getLvlDisp().getLvlUpBtn().setOnAction(lvlBtnOnActn);
				}
				
				// Keep showing until menu is exitted.
				keepShowing(true);
			}
		};

	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void keepShowing(boolean show) {
		/*
		 * 
		*/
		
		keepShowing = show;
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public EventHandler<ActionEvent> getLvlBtnOnActn() {
		/*
		 * 
		*/
		
		return lvlBtnOnActn;
	}
	
	public EventHandler<ActionEvent> getAttrBtnOnActn() {
		/*
		 * 
		*/
		
		return attrBtnOnActn;
	}
	

}