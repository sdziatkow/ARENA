package arenaPerson;

/**
 * Program Name:    ArenaCharacter.java
 *<p>
 * Purpose:         The purpose of this class is to have a base character class
 *                  that can be used by subclasses player and npc.
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 27, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import backpack.Backpack;
import backpack.EquipSlots;

import java.util.ArrayList;

import arenaEnum.itemInfo.ItemType;
import item.weapon.Weapon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import movement.Movement;
import window.Controller;
import worldStage.WorldStage;
import arenaEnum.personInfo.CharClass;
import arenaEnum.personInfo.CharState;
import arenaEnum.personInfo.CharType;
import arenaEnum.personStats.Attribute;
import arenaEnum.personStats.StatType;

public abstract class ArenaPerson{
    /**
     *
    */
	
	public static int idCount = 0;
	private int id;

	// Player or NPC
	private CharType charType;
    
    // String describing the class of the character; MONK, DUELIST, etc..
    private CharClass charClass;
    
    // String describing the state of the character.
    private CharState charState;

    // Name of character in-game
    private String name;
    
    // All character stats and attributes.
    private ArenaData data;
    private ArenaLevel lvl;

    // Object of class Backpack; used to store items.
    private Backpack backpack;

    // Object of class EquipSlots; used to equip items from backpack so they can
    // be used by character. Also equipped items can then be used to modify
    // character's stats and do various actions such as attacking.
    private EquipSlots equipSlot;
    
    public static ArrayList<ArenaPerson> otherPersons;
    private ObservableBooleanValue isAttk;
    private ObservableIntegerValue personHit;
    public static IntegerProperty isDead;
    
    // Triggered when attack is started in Movement
    private ChangeListener<? super Boolean> onAttkChanged = new ChangeListener<Boolean>() {
    	public void changed(
    			ObservableValue<? extends Boolean> attk,
    			Boolean oldVal,
    			Boolean newVal
    	){
    		if (newVal.booleanValue()) {
    			setCharState(CharState.ATTK);
    		}
    		else {
    			setCharState(CharState.MOVE);
    		}
    	}
    };
    
    // Triggered when hitBox is placed in Movement.
    private ChangeListener<? super Number> onPersonHit = new ChangeListener<Number>() {
    	public void changed(
				ObservableValue<? extends Number> idx, 
				Number oldVal,
				Number newVal
		){
    		
    		if (newVal.intValue() != -1) {
    			weapon().setTarget(otherPersons.get(newVal.intValue()));
    			weapon().setAttk(1);
    			weapon().genAttack();
    			otherPersons.get(newVal.intValue()).hurt(id); 
    			weapon().setTarget(null);
    		}
    	}
    };
    
    protected ArenaPerson() {
        /**
         * Default constructor for class ArenaCharacter.
        */
    	
    	id = idCount;
    	++idCount;
    	
    	charType = CharType.NPC;
    	charClass = CharClass.BRUTE;
    	charState = CharState.MOVE;
    	
    	data = new ArenaData();
    	lvl = new ArenaLevel();
        backpack = new Backpack(this);
        equipSlot = new EquipSlots(this);
        
        data.setDefaultVals(CharClass.BRUTE);
    }
    
    protected ArenaPerson(CharType charType, CharClass charClass) {
        /**
         *
        */
    	
    	id = idCount;
    	++idCount;
    	
    	this.charType = charType;
    	this.charClass = charClass;
    	
    	charState = CharState.MOVE;
    	
        data = new ArenaData();
        lvl = new ArenaLevel();
        backpack = new Backpack(this);
        equipSlot = new EquipSlots(this);
        
        data.setDefaultVals(charClass);
    }
    
//SETTERS--------------------------------------------------------------------------
    
    public void setCharState(CharState charState) {
    	/*
    	 * Setter for field: charState.
    	*/
    	
    	this.charState = charState;
    }
    
    public void setName(String name) {
        /**
         * Setter for field: name
        */

        this.name = name;
    }
    
    public static void setOtherPersons(ArrayList<ArenaPerson> persons) {
    	/*
    	 * 
    	*/
    	
    	otherPersons = persons;
    }
    public static void setOnDeath(IntegerProperty onDeath) {
    	/*
    	 * 
    	*/
    	
    	isDead = onDeath;
    }
    
//GETTERS--------------------------------------------------------------------------
    
    public int getID() {
    	/*
    	 * 
    	*/
    	
    	return id;
    }
    
    public CharType getCharType () {
    	/**
    	 * Getter for field: charType
    	*/
    	
    	return charType;
    }
    
    public CharClass getCharClass() {
    	/**
    	 * Getter for field: charClass
    	*/
    	
    	return charClass;
    }
    
    public CharState getCharState() {
    	/*
    	 * Getter for field: charState 
    	*/
    	
    	return charState;
    }

    public String getName() {
        /**
         * Getter for field: name
        */

        return name;
    }

    public ArenaData data() {
        /**
         * Getter for field: health
        */

        return data;
    }
    
    public ArenaLevel lvl() {
        /**
         * Getter for field: health
        */

        return lvl;
    }

    public Backpack bp() {
        /**
         * Getter for field: backpack
        */

        return backpack;
    }

    public EquipSlots equipSlot() {
        /**
         * Getter for field: equipper
        */

        return equipSlot;
    }
    
    public Weapon weapon() {
    	/**
    	 * Getter for currently equipped weapon.
    	*/
    	
    	return (Weapon)equipSlot().getEquipped(ItemType.WEAPON);
    }
    
    public abstract void attk();
    public abstract void stateMachine();
    
//FLAGS----------------------------------------------------------------------------
    
    public boolean isAlive() {
    	/**
    	 * Will return true if value of Stat health is greater than min value of 
    	 * Stat health.
    	 * 
    	 * @return alive: boolean value describing the NPC's live status. 
    	*/
    	
    	boolean alive;
    	
    	alive = arenaStat(StatType.HP).getVal() > arenaStat(StatType.HP).getMinVal();
    	
    	return alive;
    }
    
    public void setIsAttk(BooleanProperty attk) {
    	/*
    	 * 
    	*/
    	
    	isAttk = attk;
    	isAttk.addListener(onAttkChanged);
    }
    
    public void setPersonHit(IntegerProperty personIdx) {
    	/*
    	 * 
    	*/
    	
    	personHit = personIdx;
    	personHit.addListener(onPersonHit);
    }
    
//ACCESS-STATS---------------------------------------------------------------------
    
    public ArenaStat arenaStat(StatType stat) {
    	/**
    	 * 
    	*/
    	
    	switch (stat) {
    		case StatType.HP: 
    			return data.hp();
    		case StatType.MP:
    			return data.mp();
    		case StatType.SP: 
    			return data.sp();
    		case StatType.PHYSDEF:
    			return data.physDef();
    		case StatType.MAGDEF:
    			return data.magDef();
    		case StatType.CRIT:
    			return data.crit();
    		case StatType.SPEED:
    			return data.speed();
    		default:
    			return data.hp();
    	}
    }
    
    public ArenaAttr attr(Attribute attr) {
    	/**
    	 * 
    	*/
    	
    	switch (attr) {
    		case Attribute.VIGOR:
    			return data.vigor();
    		case Attribute.WILLPOWER:
    			return data.wp();
    		case Attribute.INTELLIGENCE:
    			return data.intel();
    		case Attribute.DEXTERITY:
    			return data.dex();
    		default:
    			return data.vigor();
    	}
    }
    
//ACT-ON-DATA----------------------------------------------------------------------
    
    public void hurt(int attackerID) {
    	/*
    	 * 
    	*/
    	
    	ArenaPerson attacker = otherPersons.get(attackerID);
    	
    	arenaStat(StatType.HP).dmg(attacker.weapon().getAttkDmg());
    	
    	if (!isAlive()) {
    		attacker.lvl().incXp(20 * lvl().getLvl(), false);
    		isDead.set(getID());
    	}
    	
    }
}
