package arenaCharacter;

/**
 * Program Name:    CharAttr.java
 *<p>
 * Purpose:         The purpose of this program is to create attributes for 
 * 					ArenaCharacters that will affect their stats.
 *<p>
 * @version         0.0
 *<p>
 * Created:         April 30, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

public class CharAttr {
	/**
	 * Class for character attributes which determine stat levels.
	*/
	
	public enum Attribute {
		/**
		 * @const LEVEL: Level of character.
		 * @const VIGOR: Determines max HP.
		 * @const WILLPOWER: Determins HP and SP.
		 * @const INTELLIGENCE: Determins MP.
		 * @const DEXTERITY: Determines SP, CRIT and SPEED 
		*/
		
		VIGOR,
		WILLPOWER,
		INTELLIGENCE,
		DEXTERITY;
	}
	
	private Attribute attribute;
	private int minVal;
	private int maxVal;
	private int val;
	
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public CharAttr() {
		/**
		 * 
		*/
		
		attribute = Attribute.VIGOR;
		minVal = 0;
		maxVal = 100;
		val = 0;
	}
	
	public CharAttr(Attribute attribute) {
		/**
		 * 
		*/
		
		this.attribute = attribute;
		this.minVal = 0;
		this.maxVal = 100;
		this.val = 0;
	}
	
//SETTERS--------------------------------------------------------------------------
	
	private void setVal(int val) {
		/**
		 * Set this.val to param val. 
		 * Must be in the range of 0 - 100 inclusive.
		 * Otherwise will be set to min or max depending on val.
		*/
		
		if (val < getMinVal()) {
			this.val = getMinVal();
		}
		else if (val > getMaxVal()) {
			this.val = getMaxVal();
		}
		else if (val >= getMinVal() && val <= getMaxVal()) {
			this.val = val;
		}
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public int getVal() {
		/**
		 * Getter for field: val
		*/
		
		return val;
	}
	
	private int getMinVal() {
		/**
		 * Getter for field: minVal
		*/
		
		return minVal;
	}

	private int getMaxVal() {
		/**
		 * Getter for field: maxVal
		*/
		
		return maxVal;
	}

//MODIFY---------------------------------------------------------------------------
	
	public void incAttr() {
		/**
		 * Increase attribute by one point.
		*/
		
		setVal(getVal() + 1);
	}

	public void incAttr(int amnt) {
		/**
		 * Increaste attribute by amnt.
		*/
		
		for (int num = 0; num < amnt; ++num) {
			setVal(getVal() + 1);
		}
	}
	
	public void decAttr() {
		/**
		 * Decrease attribute by one point.
		*/
		
		setVal(getVal() - 1);
	}

	public void decAttr(int amnt) {
		/**
		 * Decrease attribute by amnt.
		*/
		
		for (int num = 0; num < amnt; ++num) {
			setVal(getVal() - 1);
		}
	}
	
//DISPLAY--------------------------------------------------------------------------
	
	
	
}
