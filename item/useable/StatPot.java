package item.useable;

/**
 * Program Name:    StatPot.java
 *<p>
 * Purpose:         The purpose of this program is to create potions that can 
 * 					effect the ArenaCharacters HP, SP, or MP.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 01, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.Stat.StatType;

public class StatPot extends Useable {
	/**
	 * Should only be used for HP MP SP
	*/
	
	private StatType potType;
	private double healAmnt;
	
	public StatPot() {
		/**
		 * 
		*/
		
		super();
		healAmnt = 0.0;
		
	}
	
	public StatPot(int amnt, StatType potType, double healAmnt) {
		/**
		 * 
		*/
		
		super(amnt);
		this.potType = potType;
		this.healAmnt = healAmnt;
		
		// Set name according to its StatType.
		if (getPotType().equals(StatType.HP)) {
			setName("Potion of Health");
		}
		else if (getPotType().equals(StatType.MP)) {
			setName("Potion of Mana");
		}
		else if (getPotType().equals(StatType.SP)) {
			setName("Potion of Stamina");
		}
	}
	
//SETTERS--------------------------------------------------------------------------
	
//GETTERS--------------------------------------------------------------------------
	
	public StatType getPotType() {
		/**
		 * 
		*/
		
		return potType;
	}
	
	public double getHealAmnt() {
		/**
		 * 
		*/
		
		return healAmnt;
	}
	
//USE------------------------------------------------------------------------------
	
	public void use() {
		/**
		 * Should only be used if StatPot is in a Backpack that belongs to an
		 * ArenaCharacter.
		*/
		
		getSelf().stat(potType).heal(getHealAmnt());
		
		getSelf().bp().removeItem(this);
		
	}

}
