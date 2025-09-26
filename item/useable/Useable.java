package item.useable;

/**
 * Program Name:    ClassTemplate.java
 *<p>
 * Purpose:         The purpose of this program is to
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
import arenaCharacter.ArenaCharacter;

public abstract class Useable extends Item {
	/**
	 * 
	*/
	
	private int amnt;
	private ArenaCharacter self;
	
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
	
	public void setAmnt(int amnt) {
		/**
		 * 
		*/
		
		this.amnt = amnt;
	}
	
	public void setSelf(ArenaCharacter self) {
		/**
		 * 
		*/
		
		this.self = self;
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public int getAmnt() {
		/**
		 * 
		*/
		
		return amnt;
	}
	
	public ArenaCharacter getSelf() {
		/**
		 * 
		*/
		
		return self;
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
