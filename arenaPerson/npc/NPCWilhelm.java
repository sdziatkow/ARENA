package arenaPerson.npc;

/**
 * Program Name:    Wilhelm.java
 *<p>
 * Purpose:         The purpose of this program is to create a non-playable
 * 					character for ARENA.
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 23, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import movement.NpcMovement;
import sprite.charSprite.EnemyTestSprite;
import sprite.charSprite.PlayerSprite;
import worldStage.WorldStage;

import arenaEnum.personInfo.CharClass;
import arenaEnum.personInfo.CharState;
import arenaEnum.personStats.StatType;
import arenaEnum.itemInfo.ItemType;
import item.weapon.SteelGreatAxe;
import item.weapon.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class NPCWilhelm extends ArenaNPC {
    /**
     *
    */

//CONSTRUCTORS---------------------------------------------------------------------

    public NPCWilhelm(){
        /**
         *
        */
    	
    	super(CharClass.BARBARIAN);
    	setName("Wilhelm");
    	lvl().setLvl(15);
    }

//DISPLAY--------------------------------------------------------------------------

}
