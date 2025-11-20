package item.useable;

/**
 * Program Name:    Useable.java
 *<p>
 * Purpose:         The purpose of this program is to have a base class that all
 * 					non-armor and non-weapon usable items can use.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 01, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import item.*;

public abstract class Useable extends Item {
	/**
	 * 
	*/
	
	private int amnt;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public Useable() {
		/**
		 * 
		*/
		
		super(ItemType.USEABLE);
		this.amnt = 0;
	}
	
	public Useable(int amnt) {
		/**
		 * 
		*/
		
		super(ItemType.USEABLE);
		this.amnt = amnt;
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setInfo() {
		/*
		 * 
		*/
		
    	getInfo().clear();
		getInfo().add(getName());
		getInfo().add("AMOUNT:");
		getInfo().add(String.valueOf(getAmnt()));
	}
	
	public void setAmnt(int amnt) {
		/**
		 * 
		*/
		
		this.amnt = amnt;
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public int getAmnt() {
		/**
		 * 
		*/
		
		return amnt;
	}
	
//USING----------------------------------------------------------------------------
	
	public void use() {
		/**
		 * Should be defined in subClasses.
		*/
		
		decAmnt();
	}
	
	public void incAmnt() {
		/**
		 * Increase amnt by 1
		*/
		
		setAmnt(getAmnt() + 1);
	}

	public void incAmnt(int amnt) {
		/**
		 * Increase amnt by amnt
		*/
		
		setAmnt(getAmnt() + amnt);
	}
	
	public void decAmnt() {
		/**
		 * Decrease amnt by 1
		*/
		
		setAmnt(getAmnt() - 1);
	}

	public void decAmnt(int amnt) {
		/**
		 * Decrease amnt by amnt
		*/
		
		setAmnt(getAmnt() - amnt);
	}

}
