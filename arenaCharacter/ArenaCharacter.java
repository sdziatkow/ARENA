package arenaCharacter;

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
import item.weapon.Weapon;
import movement.Movement;
import sprite.charSprite.CharacterSprite;
import window.Controller;
import worldStage.WorldStage;
import animate.Animate;
import arenaCharacter.CharAttr.Attribute;
import arenaCharacter.Stat.StatType;

public abstract class ArenaCharacter{
    /**
     *
    */
	
	public enum CharType {
		/**
		 * @const PLAYER: playable character.
		 * @const NPC:    non-playable character.  
		*/
		
		PLAYER,
		NPC;
	}
	
	public enum CharClass {
		/**
		 * @const BRUTE: 
		 * @const BARBARIAN:
		 * @const DUELIST:
		 * @const RANGER:
		 * @const MONK:
		 * @const MAGE:
		*/
		
		BRUTE,
		BARBARIAN,
		DUELIST,
		RANGER,
		MONK,
		MAGE;
	}
	
	public enum State {
		/*
		 * 
		*/
		
		REST,
		MOVE,
		ATTK;
	}
	

	// Player or NPC
	private CharType charType;
    
    // String describing the class of the character; MONK, DUELIST, etc..
    private CharClass charClass;
    
    // String describing the state of the character.
    private State charState;

    // Name of character in-game
    private String name;
    
    // All character stats and attributes.
    private CharState state;

    // Object of class Backpack; used to store items.
    private Backpack backpack;

    // Object of class EquipSlots; used to equip items from backpack so they can
    // be used by character. Also equipped items can then be used to modify
    // character's stats and do various actions such as attacking.
    private EquipSlots equipSlot;
    
    private WorldStage stage;
    private CharacterSprite charSprite;
    private Movement movement;
    private Animate animate;
    
    protected ArenaCharacter() {
        /**
         *
        */
    	
    	this.charType = CharType.NPC;
    	this.charClass = CharClass.BRUTE;
    	this.charState = State.MOVE;
    	
    	this.state = new CharState(this);
        this.backpack = new Backpack(this);
        this.equipSlot = new EquipSlots(this, backpack);
        
        this.state.setDefaultVals(CharClass.BRUTE);
    }
    
    protected ArenaCharacter(CharType charType, CharClass charClass) {
        /**
         *
        */
    	
    	this.charType = charType;
    	this.charClass = charClass;
    	this.charState = State.MOVE;
    	
        this.state = new CharState(this);
        this.backpack = new Backpack(this);
        this.equipSlot = new EquipSlots(this, backpack);
        
        this.state.setDefaultVals(charClass);
    }
    
//SETTERS--------------------------------------------------------------------------
    
    public void setCharState(State charState) {
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
    
    public void setStage(WorldStage stage) {
    	/**
    	 * Setter for field: stage
    	*/
    	
    	this.stage = stage;
    }
    
    public void setSprite(CharacterSprite charSprite) {
    	/**
    	 * Setter for field: charSprite
    	*/
    	
    	this.charSprite = charSprite;
    }
    
    public void setMvmnt(Movement movement) {
    	/**
    	 * Setter for field: movement
    	*/
    	
    	this.movement = movement;
    }

    public void setAnim(Animate animate) {
    	/**
    	 * Setter for field: animate
    	*/
    	
    	this.animate = animate;
    }
    
//GETTERS--------------------------------------------------------------------------
    
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
    
    public State getCharState() {
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
    
    public WorldStage getStage() {
    	/**
    	 * Setter for field: stage
    	*/
    	
    	return stage;
    }

    public CharState state() {
        /**
         * Getter for field: health
        */

        return state;
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
    	
    	return equipSlot().getWeapon();
    }
    
    public CharacterSprite getSprite() {
    	/**
    	 * Getter for field: charSprite
    	*/
    	
    	return charSprite;
    }
    
    public Movement getMvmnt() {
    	/**
    	 * Getter for field: movement
    	*/
    	
    	return movement;
    }

    public Animate getAnim() {
    	/**
    	 * Setter for field: animate
    	*/
    	
    	return animate;
    }
    
    public abstract Controller getCntrl();
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
    	
    	alive = stat(StatType.HP).getVal() > stat(StatType.HP).getMinVal();
    	
    	return alive;
    }
    
//ACCESS-STATS---------------------------------------------------------------------
    
    public Stat stat(StatType stat) {
    	/**
    	 * 
    	*/
    	
    	switch (stat) {
    		case StatType.HP: 
    			return state().hp();
    		case StatType.MP:
    			return state().mp();
    		case StatType.SP: 
    			return state().sp();
    		case StatType.PHYSDEF:
    			return state().physDef();
    		case StatType.MAGDEF:
    			return state().magDef();
    		case StatType.CRIT:
    			return state().crit();
    		case StatType.SPEED:
    			return state().speed();
    		default:
    			return state().hp();
    	}
    }
    
    public CharAttr attr(Attribute attr) {
    	/**
    	 * 
    	*/
    	
    	switch (attr) {
    		case Attribute.VIGOR:
    			return state().vigor();
    		case Attribute.WILLPOWER:
    			return state().wp();
    		case Attribute.INTELLIGENCE:
    			return state().intel();
    		case Attribute.DEXTERITY:
    			return state().dex();
    		default:
    			return state().vigor();
    	}
    }

//DISPLAY--------------------------------------------------------------------------
    
    public String allInfoString() {
    	/**
    	 * 
    	*/
    	
    	String infoString;
    	
    	infoString =  ("--------------------v-NAME/CLASS-v-------\n");
    	infoString += String.format("%5s: %s\n", "NAME", getName());
    	infoString += String.format("%5s: %s\n", "CLASS", getCharClass());
    	
    	infoString += ("--------------------v-ATTRIBUTES-v-------\n");
    	infoString += String.format(
    			"%12s: %2d\n", 
    			"VIGOR",
    			attr(Attribute.VIGOR).getVal()
    			)
    	;
    	infoString += String.format(
    			"%12s: %2d\n", 
    			"WILLPOWER",
    			attr(Attribute.WILLPOWER).getVal()
    			)
    	;
    	infoString += String.format(
    			"%12s: %2d\n", 
    			"INTELLIGENCE",
    			attr(Attribute.INTELLIGENCE).getVal()
    			)
    	;
    	infoString += String.format(
    			"%12s: %2d\n",
    			"DEXTERITY",
    			attr(Attribute.DEXTERITY).getVal()
    			)
    	;
    	
    	infoString += ("---------------------v-HP/MP/SP-v-------\n");
    	infoString += String.format("HP: [ %.2f / %.2f ]\n", 
    			stat(StatType.HP).getVal(), stat(StatType.HP).getMaxVal()
    			)
    	;
    	infoString += String.format("MP: [ %.2f / %.2f ]\n", 
    			stat(StatType.MP).getVal(), stat(StatType.MP).getMaxVal()
    			)
    	;
    	infoString += String.format("SP: [ %.2f / %.2f ]\n", 
    			stat(StatType.SP).getVal(), stat(StatType.SP).getMaxVal()
    			)
    	;
    	
    	infoString += ("---------------------------v-DEF-v-------\n");
    	infoString += String.format("PHYS-DEF: %.2f\n", 
    			stat(StatType.PHYSDEF).getVal()
    			)
    	;
    	infoString += String.format("MAG-DEF: %.2f\n", 
    			stat(StatType.MAGDEF).getVal()
    			)
    	;
    	
    	infoString += ("---------------------v-CRIT/SPEED-v-------\n");
    	infoString += String.format("CRIT: %.2f%%\n", 
    			(stat(StatType.CRIT).getVal() * 100.0)
    			)
    	;
    	infoString += String.format("SPEED: %.2f\n", 
    			stat(StatType.SPEED).getVal()
    			)
    	;
    	
    	infoString += ("-----------------------v-BACKPACK-v-------\n");
    	infoString += (bp().toString());
    	
    	infoString += (equipSlot().toString());
    	

    	return infoString;
    }
    
    public String generalInfoString() {
    	/**
    	 * 
    	*/
    	
    	String infoString;
    	
    	infoString =  ("--------------------v-NAME/CLASS-v-------\n");
    	infoString +=  String.format("%5s: %s\n", "NAME", getName());
    	infoString += String.format("%5s: %s\n", "CLASS", getCharClass());
    	
    	return infoString;
    }
    
    public String attrString() {
    	/**
    	 * String containing character's Attribute values
    	*/
    	
    	String infoString;
    	
    	infoString = ("--------------------v-ATTRIBUTES-v-------\n");
    	infoString += String.format(
    			"%12s: %3d\n", 
    			"VIGOR",
    			attr(Attribute.VIGOR).getVal()
    			)
    	;
    	infoString += String.format(
    			"%12s: %3d\n", 
    			"WILLPOWER",
    			attr(Attribute.WILLPOWER).getVal()
    			)
    	;
    	infoString += String.format(
    			"%12s: %3d\n", 
    			"INTELLIGENCE",
    			attr(Attribute.INTELLIGENCE).getVal()
    			)
    	;
    	infoString += String.format(
    			"%12s: %3d\n",
    			"DEXTERITY",
    			attr(Attribute.DEXTERITY).getVal()
    			)
    	;
    	
    	return infoString;
    }
    
    public String hpMpSpString() {
    	/**
    	 * String containing character's HP, MP, and SP vals / maxVals
    	*/
    	
    	String infoString;
    	
    	infoString = ("-----------------------v-HP/MP/SP-v-------\n");
    	infoString += String.format("HP: [ %.2f / %.2f ]\n", 
    			stat(StatType.HP).getVal(), stat(StatType.HP).getMaxVal()
    			)
    	;
    	infoString += String.format("MP: [ %.2f / %.2f ]\n", 
    			stat(StatType.MP).getVal(), stat(StatType.MP).getMaxVal()
    			)
    	;
    	infoString += String.format("SP: [ %.2f / %.2f ]\n", 
    			stat(StatType.SP).getVal(), stat(StatType.SP).getMaxVal()
    			)
    	;
    	
    	return infoString;
    }

}
