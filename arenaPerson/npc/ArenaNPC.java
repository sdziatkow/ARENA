package arenaPerson.npc;

import collision.CollisionBox;
import arenaEnum.itemInfo.ItemType;
import item.weapon.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import movement.Movement;
import movement.NpcMovement;
import sprite.charSprite.NpcTestSprite;
import window.Controller;
import worldStage.WorldStage;
import java.util.Random;
import arenaEnum.personInfo.CharClass;
import arenaEnum.personInfo.CharState;
import arenaEnum.personInfo.CharType;
import arenaEnum.personStats.StatType;
import arenaPerson.*;


public abstract class ArenaNPC extends ArenaPerson {
    /**
     *
    */
    
    private Random attkGen = new Random();
    private Movement mvmnt;

//CONSTRUCTORS---------------------------------------------------------------------

    protected ArenaNPC() {
        /**
         * Default constructor for class Npc.
        */

        super(CharType.NPC, CharClass.BRUTE);
    }
    
    protected ArenaNPC(CharClass charClass) {
        /**
         * Constructor for class Npc.
        */

        super(CharType.NPC, charClass);
        
    }

//GETTERS--------------------------------------------------------------------------
    
    public Random getAttkGen() {
        /**
         *
        */
        return attkGen;
    }

//STATES---------------------------------------------------------------------------
    
    public void stateMachine() {
    	/*
    	 * 
    	*/
    	
    	if (!getCharState().equals(CharState.REST)) {
    		setCharState(CharState.ATTK);
    		
    	}
    	
    	switch (getCharState()) {
    	case REST:
    		break;
    	case MOVE:
    		break;
    	case ATTK:
    	}
    	
    }
    
    public void attk() {
    	/*
    	 * 
    	*/
    	
    }
    

//DISPLAY--------------------------------------------------------------------------

}
