package arenaPerson;

import java.util.ArrayList;

import arenaEnum.personStats.Attribute;

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

public class ArenaAttr {
	/**
	 * Class for character attributes which determine stat levels.
	*/
	
	public final int TOTAL_ATTR = 4;
	private Attribute attribute;
	private int minVal;
	private int maxVal;
	private int val;
	
	ArrayList<String> info;
	
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public ArenaAttr() {
		/**
		 * 
		*/
		
		attribute = Attribute.VIGOR;
		minVal = 0;
		maxVal = 100;
		val = 0;
		
		info = new ArrayList<String>();
		setInfo();
	}
	
	public ArenaAttr(Attribute attribute) {
		/**
		 * 
		*/
		
		this.attribute = attribute;
		this.minVal = 0;
		this.maxVal = 100;
		this.val = 0;
		
		info = new ArrayList<String>();
		setInfo();
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
		setInfo();
	}
	
	public void setMinVal(int val) {
		/*
		 * 
		*/
		
		minVal = val;
	}
	
	public void setInfo() {
		/*
		 * 
		*/
		
		getInfo().clear();
		getInfo().add(attribute.toString() + ":");
		getInfo().add(String.valueOf(getVal()));
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public String getName() {
		/*
		 * 
		*/
		
		return attribute.toString();
	}
	
	public int getVal() {
		/**
		 * Getter for field: val
		*/
		
		return val;
	}
	
	public int getMinVal() {
		/**
		 * Getter for field: minVal
		*/
		
		return minVal;
	}

	public int getMaxVal() {
		/**
		 * Getter for field: maxVal
		*/
		
		return maxVal;
	}
	
	public ArrayList<String> getInfo() {
		/*
		 * 
		*/
		
		return info;
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
			incAttr();
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
			decAttr();
		}
	}
	
//DISPLAY--------------------------------------------------------------------------
	
	
	
}
