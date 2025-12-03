package arenaPerson.npc;

/**
 * Program Name:    Elm.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         Month DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaEnum.personInfo.CharClass;
import arenaEnum.personInfo.CharState;
import arenaEnum.personStats.StatType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import movement.NpcMovement;
import sprite.charSprite.ElmSprite;
import worldStage.WorldStage;

public class NPCElm extends ArenaNPC{
	/*
	 * 
	*/
	
	public NPCElm() {
		/*
		 * 
		*/
		
	   	super(CharClass.MONK);
    	setName("Elm");
	}

}
